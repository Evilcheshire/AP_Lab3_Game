package battle;

import java.util.*;
import java.util.stream.Collectors;

import battle.arenas.Arena;
import battle.events.ArenaEvent;
import battle.game_objects.GameObject;
import battle.game_objects.droids.abilities.Ability;
import utils.*;
import battle.game_objects.droids.*;
import utils.logs.BattleLogger;


public class Battle {
    private final List<Droid> team1;
    private final List<Droid> team2;
    private final Arena arena;
    private byte turn = 0;
    private final boolean isDuel;
    private static Scanner sc = new Scanner(System.in);
    private static InputValidator inputValidator = new InputValidator(sc);
    private static BattleLogger logger;

    public byte getTurn() {return turn;}
    public void setNextTurn() { turn++; }

    public Battle(List<Droid> team1, List<Droid> team2, Arena arena, boolean logEnabled) {
        this.team1 = team1;
        this.team2 = team2;
        this.arena = arena;
        this.isDuel = false;
        this.logger = new BattleLogger(logEnabled);
    }

    public Battle(Droid droid1, Droid droid2, Arena arena, boolean logEnabled) {
        this.team1 = Collections.singletonList(droid1);
        this.team2 = Collections.singletonList(droid2);
        this.arena = arena;
        this.isDuel = true;
        this.logger = new BattleLogger(logEnabled);
    }

    // the only difference between the duel and team battle is the size of the arena and the number of droids fighting
    public void start() {
        String team1_name = team1.get(0).getName();
        String team2_name = team2.get(0).getName();

        if (!isDuel) {
            team1_name = Gr.BLUE + "Team 1" + Gr.RESET;
            team2_name = Gr.RED + "Team 2" + Gr.RESET;
        }

        // logging has to be enabled for every droid and arena to write actions correctly
        for (Droid droid : team1) droid.enableLog(logger);
        for (Droid droid : team2) droid.enableLog(logger);
        arena.enableLog(logger);
        logger.log("\nThe battle has started between " + team1_name + " and " + team2_name + "!" + "\n");

        // starting positions of the droids
        placeDroids(team1, 0, 0, 'r');
        placeDroids(team2, arena.getWIDTH() - 1, arena.getHEIGHT() - 1, 'l');

        arena.showArena();

        // main cycle of the battle
        while (teamIsAlive(team1) && teamIsAlive(team2)) {
            logger.log("\n\t\t\tTurn " + (getTurn() + 1) + "\n");

            if (turn >= 4) {
                ArenaEvent event = arena.getEvent();
                if (event != null) {
                    event.apply(team1, team2);
                    logger.log(event.getMessage());
                }
            }

            refreshInterface(team1, team1_name);
            refreshInterface(team2, team2_name);

            updateCooldowns(team1, team2);
            if (teamIsAlive(team1) && teamIsAlive(team2)) teamTurn(team1, team2, team1_name, team2_name);
            if (teamIsAlive(team1) && teamIsAlive(team2)) teamTurn(team2, team1, team2_name, team1_name);
            setNextTurn();
        }

        resetStats(team1, team2);
        logger.close();
    }

    public void teamTurn(List<Droid> team1, List<Droid> team2, String team1_name, String team2_name) {
        for (Droid droid : team1) {
            if (droid.isAlive()) {
                logger.log("\n " + droid.getName() + "'s turn:" + "\n");
                playerTurn(droid, team2, team1);
                // this check is required to finish the battle correctly
                if (teamIsAlive(team1) && teamIsAlive(team2)) {
                    refreshInterface(team1, team1_name);
                    refreshInterface(team2, team2_name);
                }
                // displaying the winner
                if (!teamIsAlive(team2)) {
                    logger.log("\n\t\t" + team1_name + " won!" + "\n");
                    return;
                }
                arena.showArena();
            }
        }
    }

    // method that handles the actions of the player
    public void playerTurn(Droid attacker, List<Droid> enemyTeam, List<Droid> allyTeam) {
        if (attacker.isDisabled()) {
            logger.log("\t" + attacker.getName() + " skips the turn!" + "\n");
            return;
        }

        boolean actionAvailable = true;

        while (actionAvailable) {
            System.out.print("\t\tChoose an action for " + attacker.getName() + ":" +
                    "\n\t1. Attack" +
                    "\n\t2. Use special ability" +
                    "\n\t3. Move droid (max range - 4)" +
                    "\n\t0. Skip the turn" +
                    "\n\t\t-> ");

            int action = inputValidator.getValidIntInRange(0, 3);

            switch (action) {
                case 1:
                    Droid target = chooseTarget(enemyTeam);
                    logger.log(" " + attacker.getName() + " attacks " + target.getName() + "\n");
                    attack(attacker, target);
                    actionAvailable = false;
                    break;
                case 2:
                    boolean abilityUsed = useSpecialAbility(attacker, enemyTeam, allyTeam);
                    if (abilityUsed)
                        actionAvailable = false;
                    else
                        System.out.println(" All abilities are on cooldown!");
                    break;
                case 3:
                    moveDroid(attacker);
                    actionAvailable = false;
                    break;
                case 0:
                    actionAvailable = false;
                    break;
                default:
                    System.out.println(" Wrong action! You have lost your turn.");
                    actionAvailable = false;
                    break;
            }
        }
    }

    public void placeDroids(List<Droid> team, int startX, int startY, char align) {
        int x = startX;
        int y = startY;

        for (Droid droid : team) {
            if (x < arena.getWIDTH() && y < arena.getHEIGHT()) {
                arena.placeObject(y, x, droid);
                if (align == 'r')
                    x += 2;
                else if (align == 'l') x -= 2;
            } else {
                logger.log(" Droid " + droid.getName() + " cannot be placed out of bounds." + "\n");
            }
        }
    }

    // choice of the target depends on the type of the target that the ability requires
    public Droid chooseTarget(List<Droid> team) {
        List<Droid> aliveDroids = team.stream().filter(Droid::isAlive).collect(Collectors.toList());

        if (aliveDroids.size() == 1) return aliveDroids.get(0);

        System.out.println("\t Choose a target:");

        for (int i = 0; i < aliveDroids.size(); i++) {
            Droid droid = aliveDroids.get(i);
            System.out.println("\t" + (i + 1) + ". " + droid.getName());
        }

        System.out.print("\t\t-> ");
        int choice = inputValidator.getValidIntInRange(1, aliveDroids.size()) - 1;

        if (choice >= 0 && choice < aliveDroids.size())
            return aliveDroids.get(choice);
        else {
            System.out.println(" Invalid choice. Try again.");
            return chooseTarget(team);
        }
    }

    public void moveDroid(Droid droid) {
        boolean moved = false;
        while (!moved){
            System.out.print("\n\tEnter new X coordinate:\n\t\t-> ");
            int x = inputValidator.getValidIntInRange(1, arena.getWIDTH()) - 1;
            System.out.print("\n\tEnter new Y coordinate:\n\t\t-> ");
            int y = inputValidator.getValidIntInRange(1, arena.getWIDTH()) - 1;

            moved = arena.moveObject(x, y, droid);
            if (droid.isAlive())
                logger.log(" " + droid.getName() + " has moved to (" + (x + 1) + ";" + (y + 1) + ")" + "\n");
            if (!moved)
                System.out.println(" Invalid or occupied position! Try again.");
        }

    }

    // method handles the choice of the ability and return if it has been used successfully
    public boolean useSpecialAbility(Droid attacker, List<Droid> enemyTeam, List<Droid> allyTeam) {
        List<Ability> abilities = attacker.getAbilities();
        List<Ability> availableAbilities = abilities.stream().filter(Ability::isAvailable).collect(Collectors.toList());

        if(availableAbilities.isEmpty()) return false;

        System.out.println("\n\t\tChoose special ability for " + attacker.getName() + ":");

        for (int i = 0; i < abilities.size(); i++) {
            String cooldownStatus = (!abilities.get(i).isAvailable()) ? " (Cooldown: " + abilities.get(i).getCurrCd() + ")" : "";
            System.out.println("\t" + (i + 1) + ". " + abilities.get(i).getName() + cooldownStatus);
        }

        System.out.print("\t\t-> ");
        int abilityIndex = inputValidator.getValidIntInRange(1, abilities.size());

        Ability selectedAbility = abilities.get(abilityIndex - 1);
        if (!selectedAbility.isAvailable()) {
            System.out.println("\tThis ability is on cooldown. Choose another action.");
            return false;
        }

        // defining the tye of the target
        Droid target = defineTargetForAbility(selectedAbility, attacker, enemyTeam, allyTeam);
        useAbility(abilityIndex - 1, attacker, target);
        return true;
    }

    // method defines the target for the ability given
    public Droid defineTargetForAbility(Ability ability, Droid caster, List<Droid> enemyTeam, List<Droid> allyTeam) {
        switch (ability.getType()) {
            case ALLY: return chooseTarget(allyTeam);
            case ENEMY: return chooseTarget(enemyTeam);
            case SELF: return caster;
            default:
                System.out.println(" Invalid ability type.");
                return null;
        }
    }

    public boolean teamIsAlive(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive())
                return true;
        }
        return false;
    }

    // method for attack
    public static void attack(Droid attacker, Droid target) {
        if (target.Avoided()) {
            logger.log("\t\t" + target.getName() + Gr.B_MAGENTA + " has avoided the attack from "+ Gr.RESET + attacker.getName() + "\n");
            return;
        }

        // calculation of the damage
        int damageToDeal = getDamageToDeal(attacker, target);

        target.takeDamage(damageToDeal);

        logger.log("\t" + attacker.getName() + " deals " + Gr.B_RED + damageToDeal + Gr.RESET + " damage!" + "\n");

        if (!target.hasShield())
            logger.log("\t\t" + attacker.getName() + Gr.YELLOW + " has destroyed the shield of "+ Gr.RESET + target.getName() + "\n");

        if(!target.isAlive())
            logger.log("\t" + attacker.getName() + " has killed " + target.getName() + "!" + "\n");
    }

    private static int getDamageToDeal(Droid attacker, Droid target) {
        int damageToDeal = attacker.getWeapon().getDamage();
        if (attacker.getWeapon().getAdditionalDamageType() != null
                && target.getCurrHealthType() == attacker.getWeapon().getAdditionalDamageType())
            damageToDeal += attacker.getWeapon().getAdditionalDamage();

        int deltaX = attacker.getX() - target.getX();
        int deltaY = attacker.getY() - target.getY();
        int range = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (range > attacker.getWeapon().getRange())
            damageToDeal = attacker.getWeapon().getDamage() * (int)(attacker.getWeapon().getRange() /range);
        return damageToDeal;
    }

    // method to use an ability, common for every type of droids
    public void useAbility(int index, Droid caster, Droid target) {
        Ability ability = caster.getAbilities().get(index);
        if (ability.isAvailable()) {
            logger.log("\t" + caster.getName() + " has used " + ability.getName() + " on " + target.getName() + "\n");
            ability.use(caster, target);
        } else
            logger.log("\t" + ability.getName() + " is not available yet. Cooldown remaining: " + ability.getCurrCd() + "\n");
    }

    public void refreshInterface(List<Droid> team, String prompt){
        if (!isDuel)
            logger.log("\t\t" + prompt + " status: + \"\\n\"");
        for (Droid droid : team)
            droid.showStats();
    }

    public void updateCooldowns(List<Droid> team1, List<Droid> team2) {
        for (Droid droid : team1) droid.updateStats();
        for (Droid droid : team2) droid.updateStats();
    }

    public void resetStats(List<Droid> team1, List<Droid> team2) {
        for (Droid droid : team1) droid.resetStats();
        for (Droid droid : team2) droid.resetStats();
    }
}