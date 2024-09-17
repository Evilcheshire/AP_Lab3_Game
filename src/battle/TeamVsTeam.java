package battle;

import droids.*;
import java.util.List;
import java.util.Scanner;

public class TeamVsTeam implements Battle {
    private List<Droid> team1;
    private List<Droid> team2;
    private Scanner scanner;

    public TeamVsTeam(List<Droid> team1, List<Droid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        System.out.println("Командний покроковий бій починається!");

        while (isTeamAlive(team1) && isTeamAlive(team2)) {
            // Ходи команди 1
            System.out.println("Хід команди 1:");
            for (Droid droid : team1) {
                if (droid.isAlive()) {
                    playerTurn(droid, team2);
                    if (!isTeamAlive(team2)) {
                        System.out.println("Команда 1 перемогла!");
                        return;
                    }
                }
            }

            // Ходи команди 2
            System.out.println("Хід команди 2:");
            for (Droid droid : team2) {
                if (droid.isAlive()) {
                    playerTurn(droid, team1);
                    if (!isTeamAlive(team1)) {
                        System.out.println("Команда 2 перемогла!");
                        return;
                    }
                }
            }
        }
    }

    private boolean isTeamAlive(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private void playerTurn(Droid attackingDroid, List<Droid> enemyTeam) {
        System.out.println(attackingDroid.getName() + " обирає дію:");
        System.out.println("1. Атакувати");
        System.out.println("2. Використати спеціальну здатність");

        int action = scanner.nextInt();

        switch (action) {
            case 1:
                Droid target = chooseTarget(enemyTeam);
                attackingDroid.attack(target);
                System.out.println(attackingDroid.getName() + " атакує " + target.getName());
                System.out.println(target.getName() + " має " + target.getHealth() + " здоров'я.");
                break;

            case 2:
                Droid specialTarget = chooseTarget(enemyTeam);
                useSpecialAbility(attackingDroid, specialTarget);
                break;

            default:
                System.out.println("Невірна дія! Ви втратили хід.");
                break;
        }
    }

    private Droid chooseTarget(List<Droid> enemyTeam) {
        System.out.println("Оберіть ціль для атаки:");
        for (int i = 0; i < enemyTeam.size(); i++) {
            if (enemyTeam.get(i).isAlive()) {
                System.out.println((i + 1) + ". " + enemyTeam.get(i).getName());
            }
        }
        int targetIndex = scanner.nextInt() - 1;
        return enemyTeam.get(targetIndex);
    }

    private void useSpecialAbility(Droid attackingDroid, Droid targetDroid) {
        if (attackingDroid instanceof Engineer) {
            System.out.println("Оберіть спеціальну здатність для " + attackingDroid.getName() + ":");
            System.out.println("1. Відновити щит");
            System.out.println("2. Перевантажити щит");

            int ability = scanner.nextInt();
            Engineer engineer = (Engineer) attackingDroid;

            if (ability == 1) {
                engineer.restoreShield(targetDroid);
                System.out.println(targetDroid.getName() + " щит відновлено до максимуму.");
            } else if (ability == 2) {
                engineer.overloadShield(targetDroid);
                System.out.println(targetDroid.getName() + " щит перевантажено.");
            }

        } else if (attackingDroid instanceof Juggernaut) {
            System.out.println("Оберіть спеціальну здатність для " + attackingDroid.getName() + ":");
            System.out.println("1. Активувати лазерну гармату");
            System.out.println("2. Дезактивувати ціль");

            int ability = scanner.nextInt();
            Juggernaut juggernaut = (Juggernaut) attackingDroid;

            if (ability == 1) {
                juggernaut.ActivateLaserCanon(targetDroid);
                System.out.println(attackingDroid.getName() + " активував лазерну гармату проти " + targetDroid.getName());
            } else if (ability == 2) {
                juggernaut.Disable(targetDroid);
                System.out.println(attackingDroid.getName() + " дезактивував " + targetDroid.getName());
            }

        } else if (attackingDroid instanceof Psi_Runner) {
            System.out.println("Оберіть спеціальну здатність для " + attackingDroid.getName() + ":");
            System.out.println("1. Ввійти у тінь");
            System.out.println("2. Псі-удар");

            int ability = scanner.nextInt();
            Psi_Runner psiRunner = (Psi_Runner) attackingDroid;

            if (ability == 1) {
                psiRunner.enterShroud();
                System.out.println(attackingDroid.getName() + " увійшов у тінь.");
            } else if (ability == 2) {
                psiRunner.psiShot(targetDroid);
                System.out.println(attackingDroid.getName() + " наніс псі-удар " + targetDroid.getName());
            }
        }
    }
}
