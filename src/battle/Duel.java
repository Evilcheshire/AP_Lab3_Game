package battle;

import droids.*;
import battle.arenas.Arena;
import graphics.Gr;

import java.util.*;

// rework required !!!

public class Duel implements Battle {
    private final Droid droid1;
    private final Droid droid2;
    private final Arena arena;
    private static Scanner scanner;


    public Duel(Droid droid1, Droid droid2, int arenaWidth, int arenaHeight) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.arena = new Arena(arenaWidth, arenaHeight);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("\n\t\tThe battle starts between " + droid1.getName() + " and " + droid2.getName());

        int turn = 1;

        arena.placeDroid(0, 0, droid1);
        arena.placeDroid(arena.getWidth() - 1, arena.getHeight() - 1, droid2);

        arena.showArena();

        while (droid1.isAlive() && droid2.isAlive()) {
            System.out.println("\t\t\tTurn " + turn);

            playerTurn(droid1, droid2);

            refreshInterface();

            if (!droid2.isAlive()) {
                System.out.println("\n\t\t" + droid1.getName() + Gr.B_YELLOW + " won!" + Gr.RESET);
                break;
            }

            playerTurn(droid2, droid1);

            refreshInterface();

            if (!droid1.isAlive()) {
                System.out.println("\n\t\t" + droid2.getName() + Gr.B_YELLOW + " won!" + Gr.RESET);
                break;
            }

            droid1.updateStats();
            droid2.updateStats();
            turn++;
        }

        droid1.resetStats();
        droid2.resetStats();
    }

    private void playerTurn(Droid attacker, Droid target) {
        if (attacker.isDisabled()) {
            System.out.println("\t" + attacker.getName() + " skips the turn!");
            return;
        }
        System.out.println(" " + attacker.getName() + "'s turn:");
        System.out.println("\t\tChoose an action of " + attacker.getName() + ":");
        System.out.println("\t1. Attack");
        System.out.println("\t2. Use special ability");
        System.out.println("\t3. Move droid");
        System.out.print("\t\t-> ");

        int action = scanner.nextInt();

        switch (action) {
            case 1:
                System.out.println(" " + attacker.getName() + " attacks " + target.getName());
                attacker.attack(target);
                break;

            case 2:
                useSpecialAbility(attacker, target);
                break;

            case 3:
                moveDroid(attacker);
                break;

            default:
                System.out.println(" Wrong action! You have lost your turn.");
                break;
        }
    }

    private void moveDroid(Droid droid) {
        System.out.print("\n\tEnter new X coordinate:\n\t\t-> ");
        int x = scanner.nextInt() - 1;
        System.out.print("\n\tEnter new Y coordinate:\n\t\t-> ");
        int y = scanner.nextInt() - 1;

        arena.moveDroid(x, y, droid);
    }

    public static void useSpecialAbility(Droid attacker, Droid target) {
        List<String> abilities = attacker.getSpecialAbilities();
        System.out.println("\n\t\tChoose special ability for " + attacker.getName() + ":");

        for (int i = 0; i < abilities.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + abilities.get(i));
        }
        System.out.print("\t\t-> ");

        int ability = scanner.nextInt();

        if (ability > 0 && ability <= abilities.size()) {
            String selectedAbility = abilities.get(ability - 1);
            switch (selectedAbility) {
                case "Restore shield":
                    if (attacker.canUseAbility1()) {
                        attacker.useAbility1(attacker);
                        System.out.println(" " + attacker.getName() + " fully restored shield.");
                    } else
                        System.out.println(" " + attacker.getName() + " cannot use \"" + selectedAbility + "\" ability yet. Cooldown: " + attacker.getCd1() + " turns.");
                    break;
                case "Overload shield":
                    if (attacker.canUseAbility2()) {
                        attacker.useAbility2(target);
                        System.out.println(" " + attacker.getName() + " overloaded shield of " + target.getName());
                    } else
                        System.out.println(" " + attacker.getName() + " cannot use \"" + selectedAbility + "\" ability yet. Cooldown: " + attacker.getCd2());
                    break;
                case "Activate laser canon":
                    if (attacker.canUseAbility1()) {
                        attacker.useAbility1(target);
                        System.out.println(" " + attacker.getName() + " activated laser canon VS " + target.getName());
                    } else
                        System.out.println(" " + attacker.getName() + " cannot use \"" + selectedAbility + "\" ability yet. Cooldown: " + attacker.getCd1());
                    break;
                case "Disable target":
                    if (attacker.canUseAbility2()) {
                        attacker.useAbility2(target);
                        System.out.println(" " + attacker.getName() + " disabled " + target.getName());
                    } else
                        System.out.println(" " + attacker.getName() + " cannot use \"" + selectedAbility + "\" ability yet. Cooldown: " + attacker.getCd2());
                    break;
                case "Enter the Shroud":
                    if (attacker.canUseAbility1()) {
                        attacker.useAbility1(target);
                        System.out.println(" " + attacker.getName() + " entered the Shroud " + target.getName());
                    } else
                        System.out.println(" " + attacker.getName() + " cannot use \"" + selectedAbility + "\" ability yet. Cooldown: " + attacker.getCd1());
                    break;
                case "Psi-shot":
                    if (attacker.canUseAbility2()) {
                        attacker.useAbility2(target);
                        System.out.println(" " + attacker.getName() + " used psi-shot VS " + target.getName());
                    } else
                        System.out.println(" " + attacker.getName() + " cannot use \"" + selectedAbility + "\" ability yet. Cooldown: " + attacker.getCd2());
                    break;
                default:
                    System.out.println(" Invalid ability.");
                    break;
            }
        } else {
            System.out.println(" Invalid choice.");
        }
    }

    public void refreshInterface(){
        droid1.showStats();
        droid2.showStats();
        arena.showArena();
    }
}
