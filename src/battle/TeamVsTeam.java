package battle;

import droids.*;
import battle.Duel;
import java.util.List;
import java.util.Scanner;

public class TeamVsTeam implements Battle {
    private final List<Droid> team1;
    private final List<Droid> team2;
    private final Scanner scanner;

    public TeamVsTeam(List<Droid> team1, List<Droid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Team battle started!");

        while (isTeamAlive(team1) && isTeamAlive(team2)) {
            // Ходи команди 1
            System.out.println("Team 1:");
            for (Droid droid : team1) {
                if (droid.isAlive()) {
                    playerTurn(droid, team2);
                    if (!isTeamAlive(team2)) {
                        System.out.println("Team 1 won!");
                        return;
                    }
                }
            }

            // Ходи команди 2
            System.out.println("Team 2:");
            for (Droid droid : team2) {
                if (droid.isAlive()) {
                    playerTurn(droid, team1);
                    if (!isTeamAlive(team1)) {
                        System.out.println("Team 2 won!");
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
        System.out.println(attackingDroid.getName() + "'s turn:");
        System.out.println("1. Attack");
        System.out.println("2. Use special ability");

        int action = scanner.nextInt();

        switch (action) {
            case 1:
                Droid target = chooseTarget(enemyTeam);
                attackingDroid.attack(target);
                System.out.println(attackingDroid.getName() + " attacks " + target.getName());
                break;

            case 2:
                Droid specialTarget = chooseTarget(enemyTeam);
                Duel.useSpecialAbility(attackingDroid, specialTarget);
                break;

            default:
                System.out.println(" Wrong action! You have lost your turn.");
                break;
        }
    }

    private Droid chooseTarget(List<Droid> enemyTeam) {
        System.out.println(" Choose your target:");
        for (int i = 0; i < enemyTeam.size(); i++) {
            if (enemyTeam.get(i).isAlive()) {
                System.out.println((i + 1) + ". " + enemyTeam.get(i).getName());
            }
        }
        int targetIndex = scanner.nextInt() - 1;
        return enemyTeam.get(targetIndex);
    }
}
