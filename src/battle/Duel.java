package battle;

import droids.*;
import java.util.Scanner;

public class Duel implements Battle {
    private final Droid droid1;
    private final Droid droid2;
    private static Scanner scanner;

    public Duel(Droid droid1, Droid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println(" The battle starts between " + droid1.getName() + " and " + droid2.getName());

        while (droid1.isAlive() && droid2.isAlive()) {
            System.out.println(" " + droid1.getName() + "'s turn:");
            playerTurn(droid1, droid2);
            Droid.showStats(droid1);
            Droid.showStats(droid2);

            if (!droid2.isAlive()) {
                System.out.println(" " + droid1.getName() + " won!");
                break;
            }

            System.out.println(" " + droid2.getName() + "'s turn:");
            playerTurn(droid2, droid1);
            Droid.showStats(droid1);
            Droid.showStats(droid2);

            if (!droid1.isAlive()) {
                System.out.println(" " + droid2.getName() + " won!");
                break;
            }
        }
    }

    private void playerTurn(Droid attackingDroid, Droid targetDroid) {
        if (attackingDroid.isDisabled()) {
            System.out.println("\t" + attackingDroid.getName() + " skips the turn!");
            return;
        }

        System.out.println("\t\tChoose an action of " + attackingDroid.getName() + ":");
        System.out.println("\t1. Attack");
        System.out.println("\t2. Use special ability");
        System.out.print("\t\t-> ");

        int action = scanner.nextInt();

        switch (action) {
            case 1:
                System.out.println(" " + attackingDroid.getName() + " attacks " + targetDroid.getName());
                attackingDroid.attack(targetDroid);
                break;

            case 2:
                useSpecialAbility(attackingDroid, targetDroid);
                break;

            default:
                System.out.println(" Wrong action! You have lost your turn.");
                break;
        }
    }

    public static void useSpecialAbility(Droid attackingDroid, Droid targetDroid) {
        if (attackingDroid instanceof Engineer) {
            System.out.println("\n\t\tChoose special ability for " + attackingDroid.getName() + ":");
            System.out.println("\t1. Restore shield");
            System.out.println("\t2. Overload shield");
            System.out.print("\t\t-> ");

            int ability = scanner.nextInt();
            Engineer engineer = (Engineer) attackingDroid;

            if (ability == 1) {
                engineer.restoreShield(attackingDroid);
                System.out.println(" " + attackingDroid.getName() + " fully restored shield.");
            } else if (ability == 2) {
                engineer.overloadShield(targetDroid);
                System.out.println(" " + attackingDroid.getName() + " overloaded " + targetDroid.getName()+ "'s shield" );
            }
        } else if (attackingDroid instanceof Juggernaut) {
            System.out.println("\n\t\tChoose special ability for " + attackingDroid.getName() + ":");
            System.out.println("\t1. Activate laser canon");
            System.out.println("\t2. Disable target");
            System.out.print("\t\t-> ");

            int ability = scanner.nextInt();
            Juggernaut juggernaut = (Juggernaut) attackingDroid;

            if (ability == 1) {
                juggernaut.ActivateLaserCanon(targetDroid);
                System.out.println(" " + attackingDroid.getName() + " has activated laser canon vs " + targetDroid.getName());
            } else if (ability == 2) {
                juggernaut.Disable(targetDroid);
                System.out.println(" " + attackingDroid.getName() + " disabled " + targetDroid.getName());
            }
        } else if (attackingDroid instanceof Psi_Runner) {
            System.out.println("\n\t\tChoose special ability for " + attackingDroid.getName() + ":");
            System.out.println("\t1. Enter shroud");
            System.out.println("\t2. Psi-shot");
            System.out.print("\t\t-> ");

            int ability = scanner.nextInt();
            Psi_Runner psiRunner = (Psi_Runner) attackingDroid;

            if (ability == 1) {
                psiRunner.enterShroud();
                System.out.println(" " + attackingDroid.getName() + " has entered shroud.");
            } else if (ability == 2) {
                psiRunner.psiShot(targetDroid);
                System.out.println(" " + attackingDroid.getName() + " fired a psi-shot vs " + targetDroid.getName());
            }
        }
    }

    public static void resetStatus(){}
}
