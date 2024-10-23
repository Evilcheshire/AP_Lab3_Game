package utils;

import java.util.List;
import battle.game_objects.droids.Droid;
import battle.game_objects.droids.weapons.Weapon;

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

    private static final String startingScreen =
            "\n\n                    " + BG_FG_WHITE + "--" + RESET + "      " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "---" + RESET + "     " + BG_FG_WHITE + "----" + RESET + "\n" +
            "                    " + BG_FG_WHITE + "--" + RESET + "      " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + " " + BG_FG_WHITE + "--" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
            "                    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "-----" + RESET + "    " + BG_FG_WHITE + "----" + RESET + "\n" +
            "                      " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "--" + RESET + "   " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
            "                      " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "     " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
            "\n" +
            "    " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "    " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "------" + RESET + "\n" +
            "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "        " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "\n" +
            "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "\n" +
            "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "        " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "      " + BG_FG_WHITE + "--" + RESET +"\n" +
            "    " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "        " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "------" + RESET + "  " + BG_FG_WHITE + "--" + RESET + "  " + BG_FG_WHITE + "----" + RESET + "    " + BG_FG_WHITE + "------" + RESET +"\n\n";

    // displays the starting screen
    public static void displayStartingScreen() {
        System.out.println(startingScreen);
        System.out.println("\t Press ENTER to start");
    }

    // displays menu options
    public static void displayMenu(){
        System.out.print(B_BLUE + T_BOLD + "\n\t\t\t\\-Menu-/" + RESET+
                "\n\t1. Enter hangar" +
                "\n\t2. Start a duel" +
                "\n\t3. Start a team battle" +
                "\n\t4. View recorded battles" +
                "\n\t5. Game manual" +
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

