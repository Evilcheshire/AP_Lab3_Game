package battle;

import droids.*;
import battle.arenas.Arena;

import java.util.*;

// rework required !!!

public class TeamBattle implements Battle {
    private final List<Droid> team1;
    private final List<Droid> team2;
    private final Arena arena;
    private static Scanner scanner;

    public TeamBattle(List<Droid> team1, List<Droid> team2, int arenaWidth, int arenaHeight) {
        this.team1 = team1;
        this.team2 = team2;
        this.arena = new Arena(arenaWidth, arenaHeight);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println(" The battle starts between Team 1 and Team 2!");

        placeDroids(team1, 0, 0, 'r');
        placeDroids(team2, arena.getWidth() - 1, arena.getHeight() - 1, 'l');

        arena.showArena();

        int turn = 1;

        while (teamIsAlive(team1) && teamIsAlive(team2)) {
            System.out.println("\t\t\tTurn " + turn);

            for (Droid droid : team1) {
                if (droid.isAlive()) {
                    System.out.println(" " + droid.getName() + "'s turn:");
                    playerTurn(droid, team2);
                    if (!teamIsAlive(team2)) {
                        System.out.println("\n\t\tTeam 1 won!");
                        return;
                    }
                }
            }

            for (Droid droid : team2) {
                if (droid.isAlive()) {
                    System.out.println(" " + droid.getName() + "'s turn:");
                    playerTurn(droid, team1);
                    if (!teamIsAlive(team1)) {
                        System.out.println("\n\t\tTeam 2 won!");
                        return;
                    }
                }
            }

            updateCooldowns(team1);
            updateCooldowns(team2);
            arena.showArena();
            turn++;
        }
    }

    private void placeDroids(List<Droid> team, int startX, int startY, char align) {
        int x = startX;
        int y = startY;

        for (Droid droid : team) {
            if (x < arena.getWidth() && y < arena.getHeight()) {
                arena.placeDroid(x, y, droid);
                if (align == 'r')
                    x += 2;
                else if (align == 'l') x -= 2;
            }
        }
    }

    private boolean teamIsAlive(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private void playerTurn(Droid attacker, List<Droid> targetTeam) {
        if (attacker.isDisabled()) {
            System.out.println("\t" + attacker.getName() + " skips the turn!");
            return;
        }

        System.out.println("\t\tChoose an action for " + attacker.getName() + ":");
        System.out.println("\t1. Attack");
        System.out.println("\t2. Use special ability");
        System.out.println("\t3. Move droid");
        System.out.print("\t\t-> ");

        int action = scanner.nextInt();

        switch (action) {
            case 1:
                Droid target = chooseTarget(targetTeam);
                System.out.println(" " + attacker.getName() + " attacks " + target.getName());
                attacker.attack(target);
                break;

            case 2:
                target = chooseTarget(targetTeam);
                Duel.useSpecialAbility(attacker, target);
                break;

            case 3:
                moveDroid(attacker);
                break;

            default:
                System.out.println(" Wrong action! You have lost your turn.");
                break;
        }
    }

    private Droid chooseTarget(List<Droid> team) {
        System.out.println("\n\t\tChoose a target:");
        for (int i = 0; i < team.size(); i++) {
            Droid droid = team.get(i);
            if (droid.isAlive()) {
                System.out.println("\t" + (i + 1) + ". " + droid.getName());
            }
        }
        System.out.print("\t\t-> ");
        int choice = scanner.nextInt() - 1;
        return team.get(choice);
    }

    private void moveDroid(Droid droid) {
        System.out.print("\n\tEnter new X coordinate:\n\t\t-> ");
        int x = scanner.nextInt() - 1;
        System.out.print("\n\tEnter new Y coordinate:\n\t\t-> ");
        int y = scanner.nextInt() - 1;

        arena.moveDroid(x, y, droid);
    }

    private void updateCooldowns(List<Droid> team) {
        for (Droid droid : team) {
            droid.updateCooldown1();
            droid.updateCooldown2();
            droid.updateDisabled();
            droid.updateShield();
        }
    }
}