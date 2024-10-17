package utils;

import java.util.List;
import battle.game_objects.droids.Droid;

public class Gr {
    // sequences to modify the color of displayed text
    public static final String RESET = "\033[0m";

    public static final String RED = "\033[38;5;1m";
    public static final String GREEN = "\033[38;5;2m";
    public static final String YELLOW = "\033[38;5;3m";
    public static final String BLUE = "\033[38;5;4m";
    public static final String MAGENTA = "\033[38;5;5m";
    public static final String CYAN = "\033[38;5;6m";
    public static final String BLUE_GRAY = "\033[38;5;67m";

    public static final String B_RED = "\033[38;5;9m";
    public static final String B_GREEN = "\033[38;5;10m";
    public static final String B_YELLOW = "\033[38;5;11m";
    public static final String B_BLUE = "\033[38;5;12m";
    public static final String B_MAGENTA = "\033[38;5;13m";
    public static final String B_CYAN = "\033[38;5;14m";

    public static final String BG_FG_WHITE = "\033[48;5;15m" + "\033[38;5;15m";
    public static final String BG_B_ORANGE = "\033[48;5;208m";

    // sequences to modify the style of the text
    public static final String T_BOLD = "\033[1m";

    // displays the starting screen
    public static void displayStartingScreen() {
        System.out.println("\n\n                    " + BG_FG_WHITE + "--" + RESET + "      " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "---" + RESET + "     " + BG_FG_WHITE + "----" + RESET + "\n" +
                "                    " + BG_FG_WHITE + "--" + RESET + "      " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + " " + BG_FG_WHITE + "--" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
                "                    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "-----" + RESET + "    " + BG_FG_WHITE + "----" + RESET + "\n" +
                "                      " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
                "                      " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "     " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
                "\n" +
                "    " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "    " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "------" + RESET + "\n" +
                "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "        " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
                "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "\n" +
                "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "        " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "      " + BG_FG_WHITE + "--" + RESET +"\n" +
                "    " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "        " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "------" + RESET +"\n\n");
        System.out.println("\t Press ENTER to start");
    }

    // displays menu options
    public static void displayMenu(){
        System.out.print(B_BLUE + T_BOLD + "\n\t\t\t\\-Menu-/" + RESET+
                "\n\t1. Enter hangar"+
                "\n\t2. Start a duel"+
                "\n\t3. Start a team battle"+
                "\n\t4. View recorded battles"+
                "\n\t0. Exit"+
                B_BLUE + "\n\t\t-> Choose an option: " + RESET);
    }

    // displays options available in the hangar
    public static void displayHangar(){
        System.out.print(B_BLUE + T_BOLD + "\n\t\t\t\\-Hangar-/" + RESET +
                "\n\t1. Create Droid" +
                "\n\t2. Delete Droid" +
                "\n\t3. View Droids" +
                "\n\t0. Back to menu" +
                B_BLUE + "\n\t\t-> Choose an option: " + RESET);
    }

    // displays the information about the available classes of droids
    public static void displayInfo(){
        System.out.print(B_BLUE + "\t\n Choose droid type:" + RESET +
                B_YELLOW + T_BOLD + "\n\t1. Engineer:"+ RESET +
                "\n\t\tStats:" + GREEN + " HP: 100;" + RED + " Damage: 35;" + CYAN + " Shield: 70;" + MAGENTA + " Avoidance: 10;" + BLUE + " Range: 6" + RESET +
                "\n\t\tAbilities:\n\t\t\tRestore Shield: fully regenerates ally's shield;" +
                "\n\t\t\tOverload Shield: destroys enemy's shield and disables it for a turn;" +
                B_RED + T_BOLD + "\n\t2. Juggernaut:" + RESET +
                "\n\t\tStats:" + GREEN + " HP: 150;" + RED + " Damage: 45;" + CYAN + " Shield: 65;" + MAGENTA + " Avoidance: 5;" + BLUE + " Range: 4" + RESET +
                "\n\t\tAbilities:\n\t\t\tLaser canon: doubles damage but the target's avoidance gets significantly higher;" +
                "\n\t\t\tDisable: targets gets disables for 2 turns;" +
                B_CYAN + T_BOLD + "\n\t3. Psi-Runner:" + RESET +
                "\n\t\tStats:" + GREEN + " HP: 80;" + RED + " Damage: 60;" + CYAN + " Shield: 50;" + MAGENTA + " Avoidance: 25;" + BLUE + " Range: 5" + RESET +
                "\n\t\tAbilities:\n\t\t\tEnter Shroud: fully regenerates shield, ignores damage from all attacks for a turn;" +
                "\n\t\t\tPsi-Shot: attacks an enemy through it's shield;" +
            B_BLUE + "\n\t\t-> Choose an option: " + RESET);
    }

    public static void showArenas() {
        System.out.print("\n\t\tArena types:" +
                "\n\t1. " + BLUE + "Outer space"+ RESET + ": classic map with asteroids;\n" +
                "\t2. "+ RED + "Primordial planet" + RESET + ":\n" +
                "\t\t\tWARNING! Extreme heat does significant damage. High acid rain chance."+
                B_BLUE + "\n\t\t-> Choose an option: " + RESET);
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

