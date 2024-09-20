package battle;

import graphics.*;
import droids.*;
import java.util.Scanner;

public class Duel implements Battle {
    private Droid droid1;
    private Droid droid2;
    private Scanner scanner;

    public Duel(Droid droid1, Droid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        System.out.println("The battle starts between " + droid1.getName() + " and " + droid2.getName());

        while (droid1.isAlive() && droid2.isAlive()) {
            // Хід дроїда 1
            System.out.println(droid1.getName() + "'s turn:");
            playerTurn(droid1, droid2);

            if (!droid2.isAlive()) {
                System.out.println(droid1.getName() + " won!");
                break;
            }

            // Хід дроїда 2
            System.out.println(droid2.getName() + "'s turn:");;
            playerTurn(droid2, droid1);

            if (!droid1.isAlive()) {
                System.out.println(droid2.getName() + " won!");
                break;
            }
        }
    }

    private void playerTurn(Droid attackingDroid, Droid targetDroid) {
        System.out.println(" Choose an action of" + attackingDroid.getName() + ":");
        System.out.println(" 1. Attack");
        System.out.println(" 2. Use special ability");

        int action = scanner.nextInt();

        switch (action) {
            case 1:
                attackingDroid.attack(targetDroid);
                System.out.println(attackingDroid.getName() + " attacks " + targetDroid.getName());
                break;

            case 2:
                useSpecialAbility(attackingDroid, targetDroid);
                break;

            default:
                System.out.println(" Wrong action! You have lost your turn.");
                break;
        }
    }

    private void useSpecialAbility(Droid attackingDroid, Droid targetDroid) {
        if (attackingDroid instanceof Engineer) {
            System.out.println(" Choose special ability for " + Graphics.B_YELLOW + attackingDroid.getName() + Graphics.RESET + ":");
            System.out.println("1. Restore shield");
            System.out.println("2. Overload shield");

            int ability = scanner.nextInt();
            Engineer engineer = (Engineer) attackingDroid;

            if (ability == 1) {
                engineer.restoreShield(attackingDroid);
                System.out.println(attackingDroid.getName() + " fully restored shield.");
            } else if (ability == 2) {
                engineer.overloadShield(targetDroid);
                System.out.println(attackingDroid.getName() + " перевантажив щит " + targetDroid.getName());
            }
        } else if (attackingDroid instanceof Juggernaut) {
            System.out.println(" Choose special ability for " +Graphics.B_RED+ attackingDroid.getName() + Graphics.RESET + ":");
            System.out.println("1.Activate laser canon");
            System.out.println("2. Disable target");

            int ability = scanner.nextInt();
            Juggernaut juggernaut = (Juggernaut) attackingDroid;

            if (ability == 1) {
                juggernaut.ActivateLaserCanon(targetDroid);
                System.out.println(attackingDroid.getName() + " has activated laser canon vs " + targetDroid.getName());
            } else if (ability == 2) {
                juggernaut.Disable(targetDroid);
                System.out.println(attackingDroid.getName() + " disabled " + targetDroid.getName());
            }
        } else if (attackingDroid instanceof Psi_Runner) {
            System.out.println(" Choose special ability for " + Graphics.B_CYAN + attackingDroid.getName() + Graphics.RESET + ":");
            System.out.println("1. Enter shroud");
            System.out.println("2. Psi-shot");

            int ability = scanner.nextInt();
            Psi_Runner psiRunner = (Psi_Runner) attackingDroid;

            if (ability == 1) {
                psiRunner.enterShroud();
                System.out.println(attackingDroid.getName() + " has entered shroud.");
            } else if (ability == 2) {
                psiRunner.psiShot(targetDroid);
                System.out.println(attackingDroid.getName() + " fired a psi-shot vs " + targetDroid.getName());
            }
        }
    }
}
