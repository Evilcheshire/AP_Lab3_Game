package menu;

import battle.arenas.Arena;
import battle.arenas.OuterSpace;
import battle.arenas.PrimordialWorld;
import battle.game_objects.droids.*;
import battle.game_objects.droids.weapons.*;
import battle.*;
import utils.GameConfig;
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

    // main method of the menu, executes while the user hasn't pressed 0
    public void start() {
        boolean running = true;

        Gr.clearScreen();
        Gr.displayStartingScreen();
        sc.nextLine();
        Gr.clearScreen();
        while (running) {
            Gr.displayMenu();
            int choice = inputValidator.getValidIntInRange(0, 5);
            switch (choice) {
                case 1:
                    enterHangar();
                    break;
                case 2:
                    duel();
                    readEnter();
                    break;
                case 3:
                    teamBattle();
                    readEnter();
                    break;
                case 4:
                    viewBattles();
                    readEnter();
                    break;
                case 5:
                    viewDescriptions();
                    readEnter();
                    break;
                case 0:
                    running = false;
                    break;
            }
            Gr.clearScreen();
        }
    }

    public void enterHangar() {
        boolean running = true;

        while (running) {
            Gr.displayHangar();
            int choice = inputValidator.getValidIntInRange(0, 3);
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
                    if (!droids.isEmpty()) deleteDroid();
                    else System.out.println(" Hangar is empty!");
                    readEnter();
                    break;
                case 3:
                    Gr.showDroids(droids, " Your droids in hangar:");
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
        GameConfig.viewDroidClassDescriptions();
        System.out.print(Gr.B_BLUE + "\n\t\t-> Choose an option: " + Gr.RESET);
        int choice = inputValidator.getValidIntInRange(1,3);
        System.out.print(" Enter a name for your droid:\n\t\t-> ");
        String name = sc.next();

        Droid droid;
        switch (choice) {
            case 1:droid = new PsiRunner(name, selectWeapon(Gr.B_CYAN + Gr.T_BOLD + "Psi Runner" + Gr.RESET));
                break;
            case 2:
                droid = new Engineer(name, selectWeapon(Gr.B_YELLOW + Gr.T_BOLD + "Engineer" + Gr.RESET));
                break;
            case 3:
                droid = new Juggernaut(name, selectWeapon(Gr.B_RED + Gr.T_BOLD + "Juggernaut" + Gr.RESET));
                break;
            default:
                System.out.println(" Wrong decision. Try again.");
                return;
        }

        droids.add(droid);
    }

    public Weapon selectWeapon(String droidClass) {
        List<Weapon> availableWeapons = GameConfig.getWeaponsForClass(droidClass);

        if (availableWeapons.isEmpty()) {
            System.out.println(" No weapons available for this droid class.");
            return null;
        }

        System.out.println("\tAvailable weapons for " + droidClass + ":");
        for (int i = 0; i < availableWeapons.size(); i++)
            System.out.println("\t\t" + (i + 1) + ". " + availableWeapons.get(i).getName());

        System.out.print(Gr.B_BLUE + "\n\t\t-> Choose an option: " + Gr.RESET);
        int choice = inputValidator.getValidIntInRange(1, availableWeapons.size());;
        return availableWeapons.get(choice - 1);
    }

    public void deleteDroid(){
        Gr.showDroids(droids, " Which droid would you like to destroy?");
        System.out.print(Gr.B_BLUE + "\t\t-> Choose an option: " + Gr.RESET);

        int choice = inputValidator.getValidIntInRange(1, droids.size()) - 1;
        System.out.print("\t" + droids.get(choice).getName() + " destroyed successfully.");
        droids.remove(choice);
    }

    public Droid chooseDroid() {
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

    public Arena chooseArena(int width, int height) {
        System.out.println("\tSelect the type of arena:");
        GameConfig.viewArenaDescriptions();
        System.out.print(Gr.B_BLUE + "\n\t\t-> Choose an option: " + Gr.RESET);
        int choice = inputValidator.getValidIntInRange(1, 2);

        switch (choice) {
            case 1:
                return new OuterSpace(width, height);
            case 2:
                return new PrimordialWorld(width, height);
            default:
                System.out.println(" Wrong decision. Try again.");
                break;
        }
        return null;
    }

    public void duel() {
        if (droids.size() < 2) {
            System.out.println(" Not enough droids for a duel. At least 2 should be in the hangar.");
            return;
        }

        Droid droid1 = chooseDroid();
        Droid droid2 = chooseDroid();
        Arena arena = chooseArena(10,10);

        Battle battle = new Battle (droid1, droid2, arena, getToLog());
        battle.start();
    }

    public void teamBattle() {
        if (droids.size() < 4) {
            System.out.println(" Not enough droids for a team battle. At least 4 should be in the hangar.");
            return;
        }

        // teams are stored in the lists
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

        Arena arena = chooseArena(13,13);
        Battle battle = new Battle(team1, team2, arena, getToLog());
        battle.start();
    }


    // method checks if the next battle is to be logged
    public boolean getToLog(){
        boolean toLog = false;
        System.out.print("\n Would you like to write this battle to a file(y/n)?\n\t\t-> ");
        String choice = inputValidator.getYesOrNo();
        if (choice.equals("y")) toLog = true;
        return toLog;
    }

    public int getTeamSize() {
        int max = (int) droids.size()/2;
        System.out.print("\n Enter the size of teams (max " + max + "):\n\t\t-> ");
        return inputValidator.getValidIntInRange(0, max);
    }

    // method reads a battle from the file
    public void viewBattles() {
        String fileName = selectLog();
        if (fileName != null) {
            BattleLogger logger = new BattleLogger(fileName);
            logger.readLog();
        }
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
                System.out.println("\t-> " + (i + 1) + ". " + logs.get(i));
            System.out.print("\t\t-> ");
            choice = inputValidator.getValidIntInRange(1, logs.size());
        }

        return logs.get(choice -1);
    }

    public void viewDescriptions() {
        System.out.println(" Choose what to view:");
        System.out.println(" 1. Droid Classes");
        System.out.println(" 2. Weapons");
        System.out.println(" 3. Abilities");
        System.out.print("\t\t-> ");

        int choice = inputValidator.getValidIntInRange(1, 3);
        switch (choice) {
            case 1:
                GameConfig.viewDroidClassDescriptions();
                break;
            case 2:
                GameConfig.viewWeaponDescriptions();
                break;
            case 3:
                GameConfig.viewAbilityDescriptions();
                break;
        }
    }

    // method to read enter (interacts only with the interface, could be placed in the graphics)
    public void readEnter(){
        sc.nextLine();
        sc.nextLine();
    }
}