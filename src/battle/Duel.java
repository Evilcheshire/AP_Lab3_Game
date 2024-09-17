package battle;

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
        System.out.println("Покроковий бій починається між " + droid1.getName() + " та " + droid2.getName());

        while (droid1.isAlive() && droid2.isAlive()) {
            // Хід дроїда 1
            System.out.println("Хід " + droid1.getName() + ":");
            playerTurn(droid1, droid2);

            if (!droid2.isAlive()) {
                System.out.println(droid1.getName() + " переміг!");
                break;
            }

            // Хід дроїда 2
            System.out.println("Хід " + droid2.getName() + ":");
            playerTurn(droid2, droid1);

            if (!droid1.isAlive()) {
                System.out.println(droid2.getName() + " переміг!");
                break;
            }
        }
    }

    private void playerTurn(Droid attackingDroid, Droid targetDroid) {
        System.out.println("Оберіть дію для " + attackingDroid.getName() + ":");
        System.out.println("1. Атакувати");
        System.out.println("2. Використати спеціальну здатність");

        int action = scanner.nextInt();

        switch (action) {
            case 1:
                attackingDroid.attack(targetDroid);
                System.out.println(attackingDroid.getName() + " атакує " + targetDroid.getName());
                System.out.println("У " + targetDroid.getName() + " залишилося " + targetDroid.getHealth() + " здоров'я.");
                break;

            case 2:
                useSpecialAbility(attackingDroid, targetDroid);
                break;

            default:
                System.out.println("Невірна дія! Ви втратили хід.");
                break;
        }
    }

    private void useSpecialAbility(Droid attackingDroid, Droid targetDroid) {
        if (attackingDroid instanceof Engineer) {
            System.out.println("Оберіть спеціальну здатність для " + attackingDroid.getName() + ":");
            System.out.println("1. Відновити щит");
            System.out.println("2. Перевантажити щит");

            int ability = scanner.nextInt();
            Engineer engineer = (Engineer) attackingDroid;

            if (ability == 1) {
                engineer.restoreShield(attackingDroid); // Відновлює щит
                System.out.println(attackingDroid.getName() + " відновив щит до максимуму.");
            } else if (ability == 2) {
                engineer.overloadShield(targetDroid); // Перевантажує щит
                System.out.println(attackingDroid.getName() + " перевантажив щит " + targetDroid.getName());
            }
        } else if (attackingDroid instanceof Juggernaut) {
            System.out.println("Оберіть спеціальну здатність для " + attackingDroid.getName() + ":");
            System.out.println("1. Активувати лазерну гармату");
            System.out.println("2. Дезактивувати ціль");

            int ability = scanner.nextInt();
            Juggernaut juggernaut = (Juggernaut) attackingDroid;

            if (ability == 1) {
                juggernaut.ActivateLaserCanon(targetDroid); // Лазерна гармата
                System.out.println(attackingDroid.getName() + " активував лазерну гармату проти " + targetDroid.getName());
            } else if (ability == 2) {
                juggernaut.Disable(targetDroid); // Дезактивація
                System.out.println(attackingDroid.getName() + " дезактивував " + targetDroid.getName());
            }
        } else if (attackingDroid instanceof Psi_Runner) {
            System.out.println("Оберіть спеціальну здатність для " + attackingDroid.getName() + ":");
            System.out.println("1. Ввійти у тінь");
            System.out.println("2. Псі-удар");

            int ability = scanner.nextInt();
            Psi_Runner psiRunner = (Psi_Runner) attackingDroid;

            if (ability == 1) {
                psiRunner.enterShroud(); // Ввійти у тінь
                System.out.println(attackingDroid.getName() + " увійшов у тінь.");
            } else if (ability == 2) {
                psiRunner.psiShot(targetDroid); // Псі-удар
                System.out.println(attackingDroid.getName() + " наніс псі-удар " + targetDroid.getName());
            }
        }
    }
}
