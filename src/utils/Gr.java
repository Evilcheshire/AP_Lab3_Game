package utils;

import java.util.List;
import droids.Droid;

public class Gr {
    public static final String RESET = "\u001B[0m";

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static final String B_RED = "\u001B[91m";
    public static final String B_GREEN = "\u001B[92m";
    public static final String B_YELLOW = "\u001B[93m";
    public static final String B_BLUE = "\u001B[94m";
    public static final String B_MAGENTA = "\u001B[95m";
    public static final String B_CYAN = "\u001B[96m";

    public static void displayMenu(){
        System.out.println(B_BLUE + "\n\t\t\t\\-Menu-/" + RESET);
        System.out.println("\t1. Create Droid");
        System.out.println("\t2. Enter hangar");
        System.out.println("\t3. Start a duel");
        System.out.println("\t4. Start a team battle");
        System.out.println("\t5. View recorded battles");
        System.out.println("\t0. Exit");
        System.out.print(B_BLUE + "\t\t-> Choose an option: " + RESET);
    }

    public static void displayInfo(){
        System.out.println(B_BLUE + "\t\n Choose droid type:" + RESET);
        System.out.println(B_YELLOW + "\t1. Engineer:"+RESET);
        System.out.println("\t\tStats:" + GREEN + "HP: 100;" + RED + " Damage: 35;" + CYAN + " Shield: 70;" + MAGENTA + " Avoidance: 10;" + BLUE + " Range: 6" + RESET);
        System.out.println("\t\tAbilities:\n\t\t\tRestore Shield: fully regenerates ally's shield;");
        System.out.println("\t\t\tOverload Shield: destroys enemy's shield and disables it for a turn;");
        System.out.println(B_RED + "\t2. Juggernaut:" + RESET);
        System.out.println("\t\tStats:" + GREEN + "HP: 150;" + RED + " Damage: 45;" + CYAN + " Shield: 65;" + MAGENTA + " Avoidance: 5;" + BLUE + " Range: 4" + RESET);
        System.out.println("\t\tAbilities:\n\t\t\tLaser canon: doubles damage but the target's avoidance gets significantly higher;");
        System.out.println("\t\t\tDisable: targets gets disables for 2 turns;");
        System.out.println(B_CYAN + "\t3. Psi-Runner:" + RESET);
        System.out.println("\t\tStats:" + GREEN + "HP: 80;" + RED + " Damage: 60;" + CYAN + " Shield: 50;" + MAGENTA + " Avoidance: 25;" + BLUE + " Range: 5" + RESET);
        System.out.println("\t\tAbilities:\n\t\t\tEnter Shroud: fully regenerates shield, ignores damage from all attacks for a turn;");
        System.out.println("\t\t\tPsi-Shot: attacks an enemy through it's shield;");
        System.out.print(B_BLUE + "\t\t-> Choose an option: " + RESET);

    }

    public static void showDroids(List<Droid> droids, String prompt) {
        if (droids.isEmpty()){
            System.out.println(" Hangar is empty!");
            return;
        }

        System.out.println(prompt);
        for (int i = 0; i < droids.size(); i++) {
            if (droids.get(i).isChosen())
                System.out.println("\t\t-> " + (i + 1) + ". " + droids.get(i).getName() + " - is already chosen");
            else
                System.out.println("\t\t-> " + (i + 1) + ". " + droids.get(i).getName());
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

