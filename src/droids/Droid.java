package droids;

import graphics.*;
import java.util.*;

public class Droid {
    private final String name;
    private int health;
    private final int max_health;
    private int damage;
    private int shield;
    private final int max_shield;
    private int avoidance;
    private int base_avoidance;
    private int disabled = 0;
    private boolean shield_status = true;
    private boolean chosen = false;
    private int x;
    private int y;
    private int eff_range;
    private int cd1 = 0;
    private int cd2 = 0;
    private int shield_cd = 0;

    private final Random rand = new Random();

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
    public int getCd1() { return cd1; }
    public int getCd2() { return cd2; }

    public boolean hasShield() { return shield_status; }
    public boolean isDisabled() { return disabled != 0; }
    public boolean isAlive() { return health > 0; }
    public boolean isChosen() { return chosen; }

    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setShield(int shield) { this.shield = shield; }
    public void setAvoidance(int avoidance) { this.avoidance = avoidance; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    public void setDisabled(int disabled) { this.disabled = disabled; }
    public void setShieldStatus(boolean status) { this.shield_status = status; }
    public void setChosen(boolean chosen) { this.chosen = chosen; }
    public void setCd1(int cd) { this.cd1 = cd; }
    public void setCd2(int cd) { this.cd2 = cd; }
    public void setShieldCD(int cd) { this.shield_cd = cd; }

    public boolean canUseAbility1() { return cd1 == 0; }
    public boolean canUseAbility2() { return cd2 == 0; }
    public void useAbility1(Droid target) { return; }
    public void useAbility2(Droid target) { return; }

    public void updateShield(){
        if (hasShield() && (getShield() > 0)) {
            shield_cd--;
            if (shield_cd == 0){
                System.out.println("\t\t" + getName() + Gr.B_CYAN + " regenerated shield!" + Gr.RESET);
                setShield(getMaxShield());
            }
        }
    }

    public void updateCooldown1() {
        if (cd1 > 0)
            cd1--;
    }

    public void updateCooldown2() {
        if (cd2 > 0)
            cd2--;
    }

    public void updateDisabled() {
        if (disabled > 0) {
            disabled--;
            if (disabled == 0) {
                setAvoidance(this.getBaseAvoidance());
                System.out.println("\t\t" + this.getName() + Gr.B_GREEN + " is no longer disabled!" + Gr.RESET);
            }
        }
    }

    public List<String> getSpecialAbilities() { return Collections.emptyList(); }

    public boolean Avoided(){
        int chance = rand.nextInt(100) + 1;
        return chance <= avoidance;
    }

    public void attack(Droid target) {
        if (target.Avoided()) {
            System.out.println(" " + target.getName() + Gr.B_MAGENTA + " avoided the attack from "+ Gr.RESET + this.getName());
            return;
        }

        int damageToDeal = this.getDamage();
        int deltaX = this.getX() - target.getX();
        int deltaY = this.getY() - target.getY();
        int range = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (range > eff_range) damageToDeal = damage * (int)(eff_range/range);

        if (target.shield > 0) {
            int remainingShield = target.shield - damageToDeal;
            if (remainingShield < 0) {
                target.shield = 0;
                target.health += remainingShield;
                double health_left = (double) target.getHealth() / target.getMaxHealth();
                if (health_left < 0.75 && target.hasShield()) {
                    System.out.println(" " + this.getName() + Gr.YELLOW + " destroyed the shield of "+ Gr.RESET + target.getName());
                    target.setShieldStatus(false);
                }
            } else
                target.shield = remainingShield;
        } else
            target.health -= damageToDeal;

        if (target.hasShield() && target.getShield() != target.getMaxShield()) target.setShieldCD(4);

        System.out.println("\t" + this.getName() + " deals " + Gr.B_RED + damageToDeal + Gr.RESET + " damage!");
    }

    public void showStats() {
        System.out.print(" " + this.getName()+ "'s stats: " + Gr.GREEN + "HP: " + this.getHealth() + "/" + this.getMaxHealth() + ";");
        System.out.print(Gr.RED + " Damage: " + this.getDamage() + ";");
        System.out.print(Gr.CYAN + " Shield: " + this.getShield() + Gr.RESET+ "/" + Gr.CYAN + this.getMaxShield() + ";");
        System.out.println(Gr.MAGENTA + " Avoidance: " + this.getAvoidance() + ";" + Gr.RESET);
        System.out.println(Gr.BLUE + " Range: " + this.getEffRange() + ";" + Gr.RESET);
        if (this.isDisabled())
            System.out.println(" Status: " + Gr.B_RED + "disabled;" + Gr.RESET);
    }
}