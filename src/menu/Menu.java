package menu;

import battle.arenas.Arena;
import droids.*;
import battle.*;
import utils.logs.BattleLogSelector;
import utils.logs.BattleLogger;
import utils.Gr;
import utils.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public List<Droid> droids = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private InputValidator inputValidator = new InputValidator(sc);

    public void start() {
        boolean running = true;
        while (running) {
            Gr.displayMenu();
            int choice = inputValidator.getValidIntInRange(0, 5);
            switch (choice) {
                case 1:
                    if(droids.size() == 10) {
                        System.out.println(" You have created the maximum number of droids!");
                        readEnter();
                        break;
                    }
                    createDroid();
                    break;
                case 2:
                    Gr.showDroids(droids, " Your droids in hangar:");
                    readEnter();
                    break;
                case 3:
                    duel();
                    readEnter();
                    break;
                case 4:
                    teamBattle();
                    readEnter();
                    break;
                case 5:
                    viewBattles();
                    readEnter();
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

        int choice = sc.nextInt();
        System.out.print(" Enter a name for your droid:\n\t\t-> ");
        String name = sc.next();

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
            int choice = inputValidator.getValidIntInRange(1, droids.size()) - 1;
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

        Battle battle = new Battle (droid1, droid2, new Arena(7, 7), getToLog());
        battle.start();
    }

    public void teamBattle() {
        if (droids.size() < 4) {
            System.out.println(" Not enough droids for a team battle. At least 4 should be in the hangar.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        int size = getTeamSize();

        System.out.println(" Choose droids for " + Gr.BLUE + "Team 1:" + Gr.RESET);
        for (int i = 0; i < size; i++) {
            Droid droid = chooseDroid();
            team1.add(droid);
        }

        System.out.println(" Choose droids for " + Gr.RED + "Team 2:" + Gr.RESET);
        for (int i = 0; i < size; i++) {
            Droid droid = chooseDroid();
            team2.add(droid);
        }

        Battle battle = new Battle(team1, team2, new Arena(10, 10), getToLog());
        battle.start();
    }

    public boolean getToLog(){
        boolean toLog = false;
        String choice = "";
        System.out.print("\n Would you like to write this battle to a file(y/n)?\n\t\t->");
        choice = inputValidator.getYesOrNo();
        if (choice.equals("y")) toLog = true;
        return toLog;
    }

    public int getTeamSize() {
        int max = (int) droids.size()/2;
        System.out.print("\n Enter the size of teams (max " + max + "):\n\t\t-> ");
        return inputValidator.getValidIntInRange(0, max);
    }

    public void viewBattles() {
        BattleLogger logger = new BattleLogger(selectLog());
        logger.readLog();
    }

    public String selectLog(){
        List<String> logs = BattleLogSelector.getAvailableLogs();
        int choice = 0;

        if (logs.isEmpty()){
            System.out.println(" No battle logs found.");
            return null;
        } else {
            System.out.println("\t Available logs: ");
            for (int i = 0; i < logs.size(); i++)
                System.out.println("\t-> " + i + ". " + logs.get(i));
            System.out.print("\t\t-> ");
            choice = inputValidator.getValidIntInRange(1, logs.size());
        }

        return logs.get(choice -1);
    }

    public void readEnter(){
        sc.nextLine();
        sc.nextLine();
    }

}