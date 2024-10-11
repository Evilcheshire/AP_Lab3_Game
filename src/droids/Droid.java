package droids;

import utils.logs.BattleLogger;
import utils.Gr;
import droids.abilities.Ability;
import java.util.*;

public class Droid {
    private final String name;
    private int health;
    private final int MAX_HEALTH;
    private int damage;
    private int shield;
    private final int MAX_SHIELD;
    private int avoidance;
    private final int BASE_AVOIDANCE;
    private int disabled = 0;
    private boolean shield_status = true;
    private boolean chosen = false;
    private int x;
    private int y;
    private final int EFFECTIVE_RANGE;
    private int shield_cd = 0;
    private List<Ability> abilities = new ArrayList<>();
    private BattleLogger logger;

    private boolean logEnabled = false; // a flag that indicates if logging is enabled

    private final Random rand = new Random(); // used to calculate the avoidance

    public Droid(String name, int health, int damage, int shield, int avoidance, int EFFECTIVE_RANGE) {
        this.name = name;
        this.health = health;
        this.MAX_HEALTH = health;
        this.damage = damage;
        this.shield = shield;
        this.MAX_SHIELD = shield;
        this.avoidance = avoidance;
        this.BASE_AVOIDANCE = avoidance;
        this.EFFECTIVE_RANGE = EFFECTIVE_RANGE;
    }

    // getters

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return MAX_HEALTH; }
    public int getDamage() { return damage; }
    public int getShield() { return shield; }
    public int getMaxShield() { return MAX_SHIELD; }
    public int getAvoidance() { return avoidance; }
    public int getBaseAvoidance() { return BASE_AVOIDANCE; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getEffRange() { return EFFECTIVE_RANGE; }
    public List<Ability> getAbilities() { return abilities; }
    public boolean getLogEnabled() { return logEnabled; }
    public BattleLogger getLogger() { return logger; }

    // field status checkers

    public boolean hasShield() { return shield_status; }
    public boolean isDisabled() { return disabled != 0; }
    public boolean isAlive() { return health > 0; }
    public boolean isChosen() { return chosen; }

    // setters

    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setShield(int shield) { this.shield = shield; }
    public void setAvoidance(int avoidance) { this.avoidance = avoidance; }
    public void setPosition(int y, int x) { this.x = x; this.y = y; }
    public void setDisabled(int disabled) { this.disabled = disabled; }
    public void setShieldStatus(boolean status) { this.shield_status = status; }
    public void setChosen(boolean chosen) { this.chosen = chosen; }
    public void setShieldCD(int cd) { this.shield_cd = cd; }
    public void setAbilities(List<Ability> abilities) { this.abilities = abilities; }

    // method to enable logging
    public void enableLog(boolean logEnabled, BattleLogger logger) {
        this.logger = logger;
        this.logEnabled = logEnabled;
    }

    // method that handles the shield renewal mechanic
    public void updateShield(){
        if (hasShield()) {
            shield_cd--; // updating cooldown
            if (shield_cd == 0 && this.isAlive()){
                logger.log("\t\t" + getName() + Gr.B_CYAN + " has regenerated shield!" + Gr.RESET + "\n");
                setShield(getMaxShield());
            }
        }
    }

    // method that handles the stun mechanic
    public void updateDisabled() {
        if (disabled > 0) {
            disabled--;
            if (disabled == 0 && this.isAlive()) {
                setAvoidance(this.getBaseAvoidance());
                logger.log("\t\t" + this.getName() + Gr.B_GREEN + " is no longer disabled!" + Gr.RESET + "\n");
            }
        }
    }

    // calculates if the droid has avoided the attack
    public boolean Avoided(){
        int chance = rand.nextInt(100);
        return chance <= avoidance;
    }

    // displays current stats of the droid(could have used toString method though)
    public void showStats() {
        if (this.isAlive()) {
            logger.log(" " + this.getName() + "'s stats: " + Gr.GREEN + "Health: " + this.getHealth() + "/" + this.getMaxHealth() + ";"
                            + Gr.RED + " Damage: " + this.getDamage() + ";"
                            + Gr.CYAN + " Shield: " + this.getShield() + Gr.RESET + "/" + Gr.CYAN + this.getMaxShield() + ";"
                            + Gr.MAGENTA + " Avoidance: " + this.getAvoidance() + ";" + Gr.RESET
                            + Gr.BLUE + " Range: " + this.getEffRange() + ";" + Gr.RESET + "\n");
            if (this.isDisabled())
                if(logEnabled) logger.log(" Status: " + Gr.B_RED + "disabled;" + Gr.RESET + "\n");
        } else
            if(logEnabled) logger.log(" " + this.getName() + Gr.RED + " is dead!" + Gr.RESET+ "\n");
    }

    // a refresh of the stats is required after every battle
    public void resetStats() {
        setChosen(false);
        setHealth(getMaxHealth());
        setShield(getMaxShield());
        setShieldStatus(true);
        setAvoidance(getBaseAvoidance());
        setDisabled(0);
        for (Ability a: abilities) a.resetCurrCd();
    }

    // updating the stats is required after every turn
    public void updateStats() {
        for (Ability a: abilities) a.updateCurrCd();
        updateDisabled();
        updateShield();
    }
}