package menu;

import droids.*;
import battle.*;
import utils.FileHandler;
import graphics.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            Graphics.displayMenu();
            System.out.print(Graphics.BLUE+"\t\t -> Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    duel();
                    break;
                case 4:
                    teamBattle();
                    break;
                case 5:
                    FileHandler.writeToFile("battle_log.txt", "Бій записано.");
                    break;
                case 6:
                    FileHandler.readFromFile("battle_log.txt");
                    break;
                case 0:
                    running = false;
                    break;
            }
        }
    }



    public void createDroid() {
        Graphics.displayInfo();

        int choice = scanner.nextInt();
        System.out.print(" Enter a name for your droid:\n\t\t -> ");
        String name = scanner.next();

        Droid droid;
        switch (choice) {
            case 1:
                droid = new Engineer(name);
                break;
            case 2:
                droid = new Juggernaut(name);
                break;
            case 3:
                droid = new Psi_Runner(name);
                break;
            default:
                System.out.println(" Wrong decision. Try again.");
                return;
        }

        droids.add(droid);
    }



    private Droid chooseDroid() {
        System.out.println(" Choose a droid:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName());
        }
        int choice = scanner.nextInt() - 1;
        return droids.get(choice);
    }

    public void showDroids() {}

    public void duel() {
        if (droids.size() < 2) {
            System.out.println(" Not enough droids for a duel. At least 2 should be in the hangar.");
            return;
        }

        System.out.println(" Choose first droid:");
        Droid droid1 = droids.get(scanner.nextInt() - 1);
        System.out.println(" Choose second droid:");
        Droid droid2 = droids.get(scanner.nextInt() - 1);

        Battle battle = new Duel(droid1, droid2);
        battle.start();
    }


    public void teamBattle() {
        if (droids.size() < 4) {
            System.out.println(" Not enough droids for a team battle. At least 4 should be in the hangar.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("Оберіть дроїдів для команди 1:");
        for (int i = 0; i < 2; i++) {
            Droid droid = chooseDroid();
            team1.add(droid);
        }

        System.out.println("Оберіть дроїдів для команди 2:");
        for (int i = 0; i < 2; i++) {
            Droid droid = chooseDroid();
            team2.add(droid);
        }

        Battle battle = new TeamVsTeam(team1, team2);
        battle.start();
    }

}