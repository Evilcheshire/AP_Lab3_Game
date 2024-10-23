package utils;

import battle.game_objects.droids.weapons.*;

import java.util.*;

public class GameConfig {
    private static final Map<String, List<Weapon>> CLASS_WEAPONS = new HashMap<>();

    private static final Map<String, String> ARENAS_DESCRIPTIONS = new HashMap<>();

    private static final Map<String, String> DROID_CLASS_DESCRIPTIONS = new HashMap<>();

    private static final Map<String, String> WEAPON_DESCRIPTIONS = new HashMap<>();

    private static final Map<String, String> ABILITY_DESCRIPTIONS = new HashMap<>();

    static {
        CLASS_WEAPONS.put(Gr.B_YELLOW + Gr.T_BOLD + "Engineer" + Gr.RESET, Arrays.asList(new NanoInjector()));
        CLASS_WEAPONS.put(Gr.B_RED + Gr.T_BOLD + "Juggernaut" + Gr.RESET, Arrays.asList(new LaserCannon()));
        CLASS_WEAPONS.put(Gr.B_CYAN + Gr.T_BOLD + "Psi Runner" + Gr.RESET, Arrays.asList(new PsiBlade()));
        CLASS_WEAPONS.put(Gr.T_BOLD + "Common" + Gr.RESET, Arrays.asList(new IonCannon(), new EnergyBlade()));

        DROID_CLASS_DESCRIPTIONS.put(Gr.B_YELLOW + Gr.T_BOLD + "Engineer" + Gr.RESET,
                Gr.GREEN + "HP: 100, " + Gr.CYAN + "Shield: 65, " + Gr.MAGENTA + "Avoidance: 15\n" + Gr.RESET +
                        "Abilities: Restore Shield (fully regenerates ally's shield).");
        DROID_CLASS_DESCRIPTIONS.put(Gr.B_RED + Gr.T_BOLD + "Juggernaut" + Gr.RESET,
                Gr.GREEN + "HP: 140, " + Gr.CYAN + "Shield: 40, " + Gr.MAGENTA + "Avoidance: 10\n" + Gr.RESET +
                        "Abilities: Disable (disables a target for 2 turns).");
        DROID_CLASS_DESCRIPTIONS.put(Gr.B_CYAN + Gr.T_BOLD + "Psi Runner" + Gr.RESET,
                Gr.GREEN + "HP: 80, " + Gr.CYAN + "Shield: 50, " + Gr.MAGENTA + "Avoidance: 20\n" + Gr.RESET +
                        "Abilities: Enter the Shroud (ignores attacks for 1 turn, restores the shield).");

        WEAPON_DESCRIPTIONS.put("Nano Injector",
                Gr.RED + "Base damage: 40; " + Gr.RESET + " +7 bonus damage on health;" +
                        "\n\tSpecial: droids gains ability 'Overload Shield';" +
                        " Sends nano-bots, that deal 7 damage over time for 3 turns;");
        WEAPON_DESCRIPTIONS.put("Laser Cannon",
                Gr.RED + "Base damage: 55; " + Gr.RESET + " No bonus damage;" +
                        "\n\tSpecial: droids gains ability 'Activate Laser Cannon';");
        WEAPON_DESCRIPTIONS.put("Psi Blade",
                Gr.RED + "Base damage: 60; " + Gr.RESET + " No bonus damage;" +
                        "\n\tSpecial: droids gains ability 'Psi Shot';");
        WEAPON_DESCRIPTIONS.put("Ion Cannon",
                Gr.RED + "Base damage: 45; " + Gr.RESET + " +10 bonus damage on shield;" +
                        "\n\tSpecial: droids gains ability 'EMP Blast';");
        WEAPON_DESCRIPTIONS.put("Energy Blade",
                Gr.RED + "Base damage: 50; " + Gr.RESET + " +10 bonus damage on shield;" +
                        "\n\tSpecial: droids gains ability 'Energy Charge';");

        ABILITY_DESCRIPTIONS.put("Activate Laser Cannon",
                "Deals double damage, is significantly less accurate.");
        ABILITY_DESCRIPTIONS.put("Disable",
                "Disables the target for 2 turns, preventing any action.");
        ABILITY_DESCRIPTIONS.put("EMP Blast",
                "Resets the target’s avoidance and disables it for 1 turn.");
        ABILITY_DESCRIPTIONS.put("Energy Charge",
                "Sends energy charge, no matter how far the target is.");
        ABILITY_DESCRIPTIONS.put("Enter the Shroud",
                "Restores shield and avoids all attacks for 1 turn.");
        ABILITY_DESCRIPTIONS.put("Overload Shield",
                "Destroys target’s shield, preventing regeneration.");
        ABILITY_DESCRIPTIONS.put("Psi Shot",
                "Deals damage ignoring the enemy's shield on any range.");
        ABILITY_DESCRIPTIONS.put("Restore Shield",
                "Fully restores an ally's shield and enables regeneration.");

        ARENAS_DESCRIPTIONS.put(Gr.BLUE + "Outer space" + Gr.RESET, "classic map with asteroids");
        ARENAS_DESCRIPTIONS.put(Gr.RED + "Primordial planet" + Gr.RESET,
                "WARNING! Extreme heat does significant damage. High acid rain chance.");
    }

    public static List<Weapon> getWeaponsForClass(String droidClass) {
        List<Weapon> weapons = new ArrayList<>(CLASS_WEAPONS.getOrDefault(droidClass, new ArrayList<>()));
        weapons.addAll(CLASS_WEAPONS.get(Gr.T_BOLD + "Common" + Gr.RESET));
        return weapons;
    }

    public static String getDroidClassDescription(String droidClass) {
        return DROID_CLASS_DESCRIPTIONS.getOrDefault(droidClass, "No description available.");
    }

    public static String getWeaponDescription(String weaponName) {
        return WEAPON_DESCRIPTIONS.getOrDefault(weaponName, "No description available.");
    }

    public static String getAbilityDescription(String abilityName) {
        return ABILITY_DESCRIPTIONS.getOrDefault(abilityName, "No description available.");
    }

    public static String getArenaDescription(String arenaName) {
        return ARENAS_DESCRIPTIONS.getOrDefault(arenaName, "No description available.");
    }

    public static List<String> getDroidClasses() {
        return new ArrayList<>(DROID_CLASS_DESCRIPTIONS.keySet());
    }

    public static List<String> getAllAbilities() {
        return new ArrayList<>(ABILITY_DESCRIPTIONS.keySet());
    }

    public static List<String> getArenas() {
        return new ArrayList<>(ARENAS_DESCRIPTIONS.keySet());
    }

    public static void viewDroidClassDescriptions() {
        int i = 0;
        System.out.println("\n\tAvailable Droid Classes:");
        for (String droidClass : GameConfig.getDroidClasses()) {
            System.out.println("\t\t" + (i + 1) + ". " + droidClass);
            System.out.println(GameConfig.getDroidClassDescription(droidClass));
            System.out.println();
            i++;
        }
    }

    public static void viewWeaponDescriptions() {
        int i = 0;
        System.out.println("\n\tAvailable Weapons:");
        for (String droidClass : GameConfig.getDroidClasses()) {
            for (Weapon weapon : GameConfig.getWeaponsForClass(droidClass)) {
                System.out.println("\t\t" + (i + 1) + ". " +  Gr.T_BOLD + weapon.getName() + Gr.RESET);
                System.out.println(GameConfig.getWeaponDescription(weapon.getName()));
                System.out.println();
                i++;
            }
        }
    }

    public static void viewAbilityDescriptions() {
        int i = 0;
        System.out.println("\n\tAvailable Abilities:");
        for (String ability : GameConfig.getAllAbilities()) {
            System.out.println("\t\t" + (i + 1) + ". " +  Gr.T_BOLD + ability + Gr.RESET);
            System.out.println(GameConfig.getAbilityDescription(ability));
            System.out.println();
            i++;
        }
    }

    public static void viewArenaDescriptions() {
        int i = 0;
        System.out.println("\n\tAvailable Arenas:");
        for (String arena : getArenas()) {
            System.out.println("\t\t" + (i + 1) + ". " +  Gr.T_BOLD + arena + Gr.RESET);
            System.out.println(getArenaDescription(arena));
            System.out.println();
            i++;
        }
    }
}

