package droids;

import utils.logs.BattleLogger;
import utils.Gr;
import droids.abilities.Ability;
import java.util.*;

public class Droid {
    private final String name;
    private int health;
    private final int max_health;
    private int damage;
    private int shield;
    private final int max_shield;
    private int avoidance;
    private final int base_avoidance;
    private int disabled = 0;
    private boolean shield_status = true;
    private boolean chosen = false;
    private int x;
    private int y;
    private final int eff_range;
    private int shield_cd = 0;
    private List<Ability> abilities = new ArrayList<>();
    private BattleLogger logger;

    private boolean logEnabled = false; // a flag that indicates if logging is enabled

    private final Random rand = new Random(); // used to calculate the avoidance

    public Droid(String name, int health, int damage, int shield, int avoidance, int eff_range) {
        this.name = name;
        this.health = health;
        this.max_health = health;
        this.damage = damage;
        this.shield = shield;
        this.max_shield = shield;
        this.avoidance = avoidance;
        this.base_avoidance = avoidance;
        this.eff_range = eff_range;
    }

    // getters

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return max_health; }
    public int getDamage() { return damage; }
    public int getShield() { return shield; }
    public int getMaxShield() { return max_shield; }
    public int getAvoidance() { return avoidance; }
    public int getBaseAvoidance() { return base_avoidance; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getEffRange() { return eff_range; }
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

    // method to use an ability, common for every type of droids
    public void useAbility(int index, Droid target) {
        Ability ability = abilities.get(index);
        if (ability.isAvailable()) {
            System.out.println("\t" + this.getName() + " has used " + ability.getName() + " on " + target.getName());
            if(logEnabled) logger.log("\t" + this.getName() + " has used " + ability.getName() + " on " + target.getName());
            ability.use(this, target);
        } else {
            System.out.println("\t" + ability.getName() + " is not available yet. Cooldown remaining: " + ability.getCurrCd());
            if(logEnabled) logger.log("\t" + ability.getName() + " is not available yet. Cooldown remaining: " + ability.getCurrCd());
        }
    }

    // method that handles the shield renewal mechanic
    public void updateShield(){
        if (hasShield()) {
            shield_cd--; // updating cooldown
            if (shield_cd == 0 && this.isAlive()){
                System.out.println("\t\t" + getName() + Gr.B_CYAN + " has regenerated shield!" + Gr.RESET);
                if(logEnabled) logger.log("\t\t" + getName() + Gr.B_CYAN + " has regenerated shield!" + Gr.RESET);
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
                System.out.println("\t\t" + this.getName() + Gr.B_GREEN + " is no longer disabled!" + Gr.RESET);
                if(logEnabled) logger.log("\t\t" + this.getName() + Gr.B_GREEN + " is no longer disabled!" + Gr.RESET);
            }
        }
    }

    // calculates if the droid has avoided the attack
    public boolean Avoided(){
        int chance = rand.nextInt(100);
        return chance <= avoidance;
    }

    // method for attack
    public void attack(Droid target) {
        if (target.Avoided()) {
            System.out.println("\t\t" + target.getName() + Gr.B_MAGENTA + " has avoided the attack from "+ Gr.RESET + this.getName());
            if(logEnabled) logger.log("\t\t" + target.getName() + Gr.B_MAGENTA + " has avoided the attack from "+ Gr.RESET + this.getName());
            return;
        }

        // calculation of the damage
        int damageToDeal = this.getDamage();
        int deltaX = this.getX() - target.getX();
        int deltaY = this.getY() - target.getY();
        int range = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (range > eff_range) damageToDeal = damage * (int)(eff_range/range);

        System.out.println("\t" + this.getName() + " deals " + Gr.B_RED + damageToDeal + Gr.RESET + " damage!");
        if(logEnabled) logger.log("\t" + this.getName() + " deals " + Gr.B_RED + damageToDeal + Gr.RESET + " damage!");

        if (target.shield > 0) {
            int remainingShield = target.shield - damageToDeal;
            if (remainingShield < 0) {
                target.shield = 0;
                target.health += remainingShield;
                double health_left = (double) target.getHealth() / target.getMaxHealth();
                if (health_left < 0.75 && target.hasShield()) { // to destroy the shield means that it can be no longer regenerated
                    System.out.println("\t\t" + this.getName() + Gr.YELLOW + " has destroyed the shield of "+ Gr.RESET + target.getName());
                    if(logEnabled) logger.log("\t\t" + this.getName() + Gr.YELLOW + " has destroyed the shield of "+ Gr.RESET + target.getName());
                    target.setShieldStatus(false);
                }
            } else
                target.shield = remainingShield;
        } else
            target.health -= damageToDeal;

        // setting cooldown for the shield if it hasn't been destroyed
        if (target.hasShield() && target.getShield() != target.getMaxShield()) target.setShieldCD(4);

        if(!target.isAlive()){
            System.out.println("\t" + this.getName() + " has killed " + target.getName() + "!");
            if(logEnabled) logger.log("\t" + this.getName() + " has killed " + target.getName() + "!");
        }

    }

    // displays current stats of the droid(could have used toString method though)
    public void showStats() {
        if (this.isAlive()) {
            System.out.println(" " + this.getName() + "'s stats: " + Gr.GREEN + "Health: " + this.getHealth() + "/" + this.getMaxHealth() + ";"
                    + Gr.RED + " Damage: " + this.getDamage() + ";"
                    + Gr.CYAN + " Shield: " + this.getShield() + Gr.RESET + "/" + Gr.CYAN + this.getMaxShield() + ";"
                    + Gr.MAGENTA + " Avoidance: " + this.getAvoidance() + ";" + Gr.RESET
                    + Gr.BLUE + " Range: " + this.getEffRange() + ";" + Gr.RESET);
            if(logEnabled) logger.log(" " + this.getName() + "'s stats: " + Gr.GREEN + "Health: " + this.getHealth() + "/" + this.getMaxHealth() + ";"
                            + Gr.RED + " Damage: " + this.getDamage() + ";"
                            + Gr.CYAN + " Shield: " + this.getShield() + Gr.RESET + "/" + Gr.CYAN + this.getMaxShield() + ";"
                            + Gr.MAGENTA + " Avoidance: " + this.getAvoidance() + ";" + Gr.RESET
                            + Gr.BLUE + " Range: " + this.getEffRange() + ";" + Gr.RESET);
            if (this.isDisabled()){
                System.out.println(" Status: " + Gr.B_RED + "disabled;" + Gr.RESET);
                if(logEnabled) logger.log(" Status: " + Gr.B_RED + "disabled;" + Gr.RESET);
            }
        } else {
            System.out.println(" " + this.getName() + Gr.RED + " is dead!" + Gr.RESET);
            if(logEnabled) logger.log(" " + this.getName() + Gr.RED + " is dead!" + Gr.RESET);
        }
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