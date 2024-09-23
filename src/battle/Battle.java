package battle;

import java.util.*;
import java.util.stream.Collectors;

import droids.abilities.Ability;
import utils.Gr;
import droids.*;
import battle.arenas.Arena;

public class Battle {
    private final List<Droid> team1;
    private final List<Droid> team2;
    private final Arena arena;
    private final boolean isDuel;
    private static Scanner sc = new Scanner(System.in);

    public Battle(List<Droid> team1, List<Droid> team2, Arena arena) {
        this.team1 = team1;
        this.team2 = team2;
        this.arena = arena;
        this.isDuel = false;
    }

    public Battle(Droid droid1, Droid droid2, Arena arena) {
        this.team1 = Collections.singletonList(droid1);
        this.team2 = Collections.singletonList(droid2);
        this.arena = arena;
        this.isDuel = true;
    }

    public void start() {
        String team1_name = team1.get(0).getName();
        String team2_name = team2.get(0).getName();
        if (!isDuel) {
            team1_name = Gr.BLUE + "Team 1" + Gr.RESET;
            team2_name = Gr.RED + "Team 2" + Gr.RESET;
        }
        System.out.println("\nThe battle starts between " + team1_name + " and " + team2_name + "!");

        placeDroids(team1, 0, 0, 'r');
        placeDroids(team2, arena.getWidth() - 1, arena.getHeight() - 1, 'l');

        arena.showArena();

        int turn = 1;

        while (teamIsAlive(team1) && teamIsAlive(team2)) {
            System.out.println("\n\t\t\tTurn " + turn);

            teamTurn(team1, team2, team1_name, team2_name);
            teamTurn(team2, team1, team2_name, team1_name);

            turn++;
        }
    }

    public void teamTurn(List<Droid> team1, List<Droid> team2, String team1_name, String team2_name) {
        for (Droid droid : team1) {
            if (droid.isAlive()) {
                System.out.println("\n " + droid.getName() + "'s turn:");
                playerTurn(droid, team2, team1);
                refreshInterface(team1, team1_name);
                refreshInterface(team2, team2_name);
                if (!teamIsAlive(team2)) {
                    System.out.println("\n\t\t" + team1_name + " won!");
                    return;
                }
                updateCooldowns(team1);
                updateCooldowns(team2);
                arena.showArena();
            }
        }
    }

    public void placeDroids(List<Droid> team, int startX, int startY, char align) {
        int x = startX;
        int y = startY;

        for (Droid droid : team) {
            if (x < arena.getWidth() && y < arena.getHeight()) {
                arena.placeDroid(x, y, droid);
                if (align == 'r')
                    x += 2;
                else if (align == 'l') x -= 2;
            } else
                System.out.println(" Droid " + droid.getName() + " cannot be placed out of bounds.");
        }
    }

    public boolean teamIsAlive(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive())
                return true;
        }
        return false;
    }

    public void playerTurn(Droid attacker, List<Droid> enemyTeam, List<Droid> allyTeam) {
        if (attacker.isDisabled()) {
            System.out.println("\t" + attacker.getName() + " skips the turn!");
            return;
        }

        System.out.println("\t\tChoose an action for " + attacker.getName() + ":");
        System.out.println("\t1. Attack");
        System.out.println("\t2. Use special ability");
        System.out.println("\t3. Move droid (max range - 4)");
        System.out.print("\t\t-> ");

        int action = sc.nextInt();

        switch (action) {
            case 1:
                Droid target = chooseTarget(enemyTeam);
                System.out.println(" " + attacker.getName() + " attacks " + target.getName());
                attacker.attack(target);
                break;
            case 2:
                useSpecialAbility(attacker, enemyTeam, allyTeam);
                break;
            case 3:
                moveDroid(attacker);
                break;
            default:
                System.out.println(" Wrong action! You have lost your turn.");
                break;
        }
    }

    public static Droid chooseTarget(List<Droid> team) {
        List<Droid> aliveDroids = team.stream().filter(Droid::isAlive).collect(Collectors.toList());
        if (aliveDroids.isEmpty()) return null;

        if (aliveDroids.size() == 1) return aliveDroids.get(0);

        System.out.println("\t Choose a target:");

        for (int i = 0; i < aliveDroids.size(); i++) {
            Droid droid = aliveDroids.get(i);
            System.out.println("\t" + (i + 1) + ". " + droid.getName());
        }
        System.out.print("\t\t-> ");
        int choice = sc.nextInt() - 1;

        if (choice >= 0 && choice < aliveDroids.size())
            return aliveDroids.get(choice);
        else {
            System.out.println("Invalid choice. Try again.");
            return chooseTarget(team);
        }
    }

    public void moveDroid(Droid droid) {
        System.out.print("\n\tEnter new X coordinate:\n\t\t-> ");
        int x = sc.nextInt() - 1;
        System.out.print("\n\tEnter new Y coordinate:\n\t\t-> ");
        int y = sc.nextInt() - 1;

        arena.moveDroid(x, y, droid);
    }

    public static void useSpecialAbility(Droid attacker, List<Droid> enemyTeam, List<Droid> allyTeam) {
        List<Ability> abilities = attacker.getAbilities();
        System.out.println("\n\t\tChoose special ability for " + attacker.getName() + ":");

        for (int i = 0; i < abilities.size(); i++)
            System.out.println("\t" + (i + 1) + ". " + abilities.get(i).getName());

        System.out.print("\t\t-> ");

        int ability_index = sc.nextInt();

        if (ability_index > 0 && ability_index <= abilities.size()) {
            Ability selectedAbility = abilities.get(ability_index - 1);
            Droid target = defineTargetForAbility(selectedAbility, attacker, enemyTeam, allyTeam);
            attacker.useAbility(ability_index -1, target);
        } else
            System.out.println(" Invalid choice.");
    }

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

    public void refreshInterface(List<Droid> team, String prompt){
        if (!isDuel) System.out.println("\t\t" + prompt + " status:");
        for (Droid droid : team)
            droid.showStats();
    }

    public void updateCooldowns(List<Droid> team) {
        for (Droid droid : team)
            droid.updateStats();
    }
}
