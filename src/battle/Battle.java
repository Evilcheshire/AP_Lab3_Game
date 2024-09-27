package battle;

import java.util.*;
import java.util.stream.Collectors;

import droids.abilities.Ability;
import utils.*;
import droids.*;
import battle.arenas.Arena;
import utils.logs.BattleLogger;


public class Battle {
    private final List<Droid> team1;
    private final List<Droid> team2;
    private final Arena arena;
    private final boolean isDuel;
    private static Scanner sc = new Scanner(System.in);;
    private static InputValidator inputValidator = new InputValidator(sc);
    private boolean logEnabled;
    private BattleLogger logger;

    public Battle(List<Droid> team1, List<Droid> team2, Arena arena, boolean logEnabled) {
        this.team1 = team1;
        this.team2 = team2;
        this.arena = arena;
        this.isDuel = false;
        this.logEnabled = logEnabled;
        if (logEnabled) this.logger = new BattleLogger();
    }

    public Battle(Droid droid1, Droid droid2, Arena arena, boolean logEnabled) {
        this.team1 = Collections.singletonList(droid1);
        this.team2 = Collections.singletonList(droid2);
        this.arena = arena;
        this.isDuel = true;
        this.logEnabled = logEnabled;
        if (logEnabled) this.logger = new BattleLogger();
    }

    // the only difference between the duel and team battle is the size of the arena and the number of droids fighting
    public void start() {
        String team1_name = team1.get(0).getName();
        String team2_name = team2.get(0).getName();

        if (!isDuel) {
            team1_name = Gr.BLUE + "Team 1" + Gr.RESET;
            team2_name = Gr.RED + "Team 2" + Gr.RESET;
        }

        System.out.println("\nThe battle has started between " + team1_name + " and " + team2_name + "!");
        // logging has to be enabled for every droid to write actions correctly
        if (logEnabled){
            for (Droid droid : team1) droid.enableLog(true, logger);
            for (Droid droid : team2) droid.enableLog(true, logger);
            logger.log("\nThe battle has started between " + team1_name + " and " + team2_name + "!");
        }

        // starting positions of the droids
        placeDroids(team1, 0, 0, 'r');
        placeDroids(team2, arena.getWIDTH() - 1, arena.getHEIGHT() - 1, 'l');

        arena.showArena();

        int turn = 1;

        // main cycle of the battle
        while (teamIsAlive(team1) && teamIsAlive(team2)) {
            System.out.println("\n\t\t\tTurn " + turn);
            if(logEnabled) logger.log("\n\t\t\tTurn " + turn);
            updateCooldowns(team1);
            updateCooldowns(team2);
            teamTurn(team1, team2, team1_name, team2_name);
            teamTurn(team2, team1, team2_name, team1_name);
            turn++;
        }

        resetStats(team1, team2);
        if (logEnabled) logger.close();
    }

    public void teamTurn(List<Droid> team1, List<Droid> team2, String team1_name, String team2_name) {
        for (Droid droid : team1) {
            if (droid.isAlive()) {
                System.out.println("\n " + droid.getName() + "'s turn:");
                if(logEnabled) logger.log("\n " + droid.getName() + "'s turn:");
                playerTurn(droid, team2, team1);
                // this check is required to finish the battle correctly
                if (teamIsAlive(team1) && teamIsAlive(team2)) {
                    refreshInterface(team1, team1_name);
                    refreshInterface(team2, team2_name);
                }
                // displaying the winner
                if (!teamIsAlive(team2)) {
                    System.out.println("\n\t\t" + team1_name + " won!");
                    if(logEnabled) logger.log("\n\t\t" + team1_name + " won!");
                    return;
                }
                arena.showArena();
            }
        }
    }

    // method that handles the actions of the player
    public void playerTurn(Droid attacker, List<Droid> enemyTeam, List<Droid> allyTeam) {
        if (attacker.isDisabled()) {
            System.out.println("\t" + attacker.getName() + " skips the turn!");
            if(logEnabled) logger.log("\t" + attacker.getName() + " skips the turn!");
            return;
        }

        boolean actionAvailable = true;

        while (actionAvailable) {
            System.out.println("\t\tChoose an action for " + attacker.getName() + ":");
            System.out.println("\t1. Attack");
            System.out.println("\t2. Use special ability");
            System.out.println("\t3. Move droid (max range - 4)");
            System.out.println("\t0. Skip the turn");
            System.out.print("\t\t-> ");

            int action = inputValidator.getValidIntInRange(1, 3);

            switch (action) {
                case 1:
                    Droid target = chooseTarget(enemyTeam);
                    System.out.println(" " + attacker.getName() + " attacks " + target.getName());
                    if(logEnabled) logger.log(" " + attacker.getName() + " attacks " + target.getName());
                    attacker.attack(target);
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
                arena.placeDroid(y, x, droid);
                if (align == 'r')
                    x += 2;
                else if (align == 'l') x -= 2;
            } else {
                System.out.println(" Droid " + droid.getName() + " cannot be placed out of bounds.");
                if (logEnabled) logger.log(" Droid " + droid.getName() + " cannot be placed out of bounds.");
            }
        }
    }

    // choice of the target depends on the type of the target that the ability requires
    public static Droid chooseTarget(List<Droid> team) {
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

            moved = arena.moveDroid(x, y, droid);
            System.out.println(" " + droid.getName() + " has moved to (" + (x + 1) + "; " + (y + 1) + ")");
            if (logEnabled) logger.log(" " + droid.getName() + " has moved to (" + (x + 1) + ";" + (y + 1) + ")");
            if (!moved)
                System.out.println(" Invalid or occupied position! Try again.");
        }

    }

    // method handles the choice of the ability and return if it has been used successfully
    public static boolean useSpecialAbility(Droid attacker, List<Droid> enemyTeam, List<Droid> allyTeam) {
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
        attacker.useAbility(abilityIndex - 1, target);
        return true;
    }

    // method defines the target for the ability given
    public static Droid defineTargetForAbility(Ability ability, Droid caster, List<Droid> enemyTeam, List<Droid> allyTeam) {
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

    public void refreshInterface(List<Droid> team, String prompt){
        if (!isDuel){
            System.out.println("\t\t" + prompt + " status:");
            if (logEnabled) logger.log("\t\t" + prompt + " status:");
        }
        for (Droid droid : team)
            droid.showStats();
    }

    public void updateCooldowns(List<Droid> team) {
        for (Droid droid : team)
            droid.updateStats();
    }

    public void resetStats(List<Droid> team1, List<Droid> team2) {
        for (Droid droid : team1) droid.resetStats();
        for (Droid droid : team2) droid.resetStats();
    }
}
