package menu;

import droids.*;
import battle.*;
import utils.FileHandler;
import graphics.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public List<Droid> droids = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            Gr.displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if(droids.size() == 10) {
                        System.out.println(" You have created the maximum number of droids!");
                        scanner.nextLine();
                        scanner.nextLine();
                        break;
                    }
                    createDroid();
                    break;
                case 2:
                    Gr.showDroids(droids, " Your droids in hangar:");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 3:
                    duel();
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 4:
                    teamBattle();
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 5:
                    FileHandler.writeToFile("battle_log.txt", "Battle saved successfully.");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 6:
                    FileHandler.readFromFile("battle_log.txt");
                    break;
                case 0:
                    running = false;
                    break;
            }
            Gr.clearScreen();
        }
    }

    public void createDroid() {
        Gr.displayInfo();

        int choice = scanner.nextInt();
        System.out.print(" Enter a name for your droid:\n\t\t-> ");
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
        while (true) {
            Gr.showDroids(droids, " Choose a droid:");
            System.out.print(Gr.B_BLUE + "\t\t-> Choose an option: " + Gr.RESET);
            int choice = scanner.nextInt() - 1;
            if (droids.get(choice).isChosen()){
                System.out.println("\t" + droids.get(choice).getName() + " is already chosen.");
            } else{
                droids.get(choice).setChosen(true);
                return droids.get(choice);
            }
        }
    }

    public void duel() {
        if (droids.size() < 2) {
            System.out.println(" Not enough droids for a duel. At least 2 should be in the hangar.");
            return;
        }

        Droid droid1 = chooseDroid();
        Droid droid2 = chooseDroid();

        Battle battle = new Duel(droid1, droid2, 7,7);
        battle.start();
    }

    public void teamBattle() {
        if (droids.size() < 4) {
            System.out.println(" Not enough droids for a team battle. At least 4 should be in the hangar.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println(" Choose droids for " + Gr.BLUE + "team 1:" + Gr.RESET);
        for (int i = 0; i < 2; i++) {
            Droid droid = chooseDroid();
            team1.add(droid);
        }

        System.out.println(" Choose droids for " + Gr.RED + "team 2:" + Gr.RESET);
        for (int i = 0; i < 2; i++) {
            Droid droid = chooseDroid();
            team2.add(droid);
        }

        Battle battle = new TeamBattle(team1, team2, 12, 12);
        battle.start();
    }

}