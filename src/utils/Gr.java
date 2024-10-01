package utils;

import java.util.List;
import droids.Droid;

public class Gr {
    // sequences to modify the color of displayed text
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

    public static final String BG_WHITE = "\u001B[47m";

    // displays the starting screen
    public static void displayStartingScreen() {
        System.out.println("\n\n                    " + BG_WHITE + "--" + RESET + "      " + BG_WHITE + "--" + RESET + "   " + BG_WHITE + "---" + RESET + "     " + BG_WHITE + "----" + RESET + "\n" +
                "                    " + BG_WHITE + "--" + RESET + "      " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + " " + BG_WHITE + "--" + RESET + "    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "\n" +
                "                    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "-----" + RESET + "    " + BG_WHITE + "----" + RESET + "\n" +
                "                      " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "   " + BG_WHITE + "--" + RESET + "   " + BG_WHITE + "--" + RESET + "   " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "\n" +
                "                      " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "     " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "\n" +
                "\n" +
                "    " + BG_WHITE + "------" + RESET + "  " + BG_WHITE + "------" + RESET + "    " + BG_WHITE + "----" + RESET + "    " + BG_WHITE + "----" + RESET + "    " + BG_WHITE + "------" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "----" + RESET + "    " + BG_WHITE + "------" + RESET + "\n" +
                "    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "        " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "\n" +
                "    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "------" + RESET + "    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "----" + RESET + "    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "------" + RESET + "\n" +
                "    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "        " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "      " + BG_WHITE + "--" + RESET +"\n" +
                "    " + BG_WHITE + "------" + RESET + "  " + BG_WHITE + "--" + RESET + "        " + BG_WHITE + "----" + RESET + "    " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "------" + RESET + "  " + BG_WHITE + "--" + RESET + "  " + BG_WHITE + "----" + RESET + "    " + BG_WHITE + "------" + RESET +"\n\n");
        System.out.println("\t Press ENTER to start");
    }

    // displays menu options
    public static void displayMenu(){
        System.out.print(B_BLUE + "\n\t\t\t\\-Menu-/" + RESET+
                "\t1. Enter hangar"+
                "\t2. Start a duel"+
                "\t3. Start a team battle"+
                "\t4. View recorded battles"+
                "\t0. Exit"+
                B_BLUE + "\t\t-> Choose an option: " + RESET);
    }

    // displays options available in the hangar
    public static void displayHangar(){
        System.out.print(B_BLUE + "\n\t\t\t\\-Hangar-/" + RESET +
                "\t1. Create Droid" +
                "\t2. Delete Droid" +
                "\t3. View Droids" +
                "\t0. Back to menu" +
                Gr.B_BLUE + "\t\t-> Choose an option: " + Gr.RESET);
    }

    // displays the information about the available classes of droids
    public static void displayInfo(){
        System.out.print(B_BLUE + "\t\n Choose droid type:" + RESET +
                B_YELLOW + "\t1. Engineer:"+ RESET +
                "\t\tStats:" + GREEN + "HP: 100;" + RED + " Damage: 35;" + CYAN + " Shield: 70;" + MAGENTA + " Avoidance: 10;" + BLUE + " Range: 6" + RESET +
                "\t\tAbilities:\n\t\t\tRestore Shield: fully regenerates ally's shield;" +
                "\t\t\tOverload Shield: destroys enemy's shield and disables it for a turn;" +
                B_RED + "\t2. Juggernaut:" + RESET +
                "\t\tStats:" + GREEN + "HP: 150;" + RED + " Damage: 45;" + CYAN + " Shield: 65;" + MAGENTA + " Avoidance: 5;" + BLUE + " Range: 4" + RESET +
                "\t\tAbilities:\n\t\t\tLaser canon: doubles damage but the target's avoidance gets significantly higher;" +
                "\t\t\tDisable: targets gets disables for 2 turns;" +
                B_CYAN + "\t3. Psi-Runner:" + RESET +
                "\t\tStats:" + GREEN + "HP: 80;" + RED + " Damage: 60;" + CYAN + " Shield: 50;" + MAGENTA + " Avoidance: 25;" + BLUE + " Range: 5" + RESET +
                "\t\tAbilities:\n\t\t\tEnter Shroud: fully regenerates shield, ignores damage from all attacks for a turn;" +
                "\t\t\tPsi-Shot: attacks an enemy through it's shield;" +
            B_BLUE + "\t\t-> Choose an option: " + RESET);
    }

    // displays the created droids
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

    // clears(slides) the screen(works on terminal out of the IDE)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

