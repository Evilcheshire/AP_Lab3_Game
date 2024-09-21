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
    private int xp = 0;
    private boolean disabled = false;
    private boolean wounded = false;
    private boolean chosen = false;
    private int x;
    private int y;

    private final Random rand = new Random();

    public Droid(String name, int health, int damage, int shield, int avoidance) {
        this.name = name;
        this.health = health;
        this.max_health = health;
        this.damage = damage;
        this.shield = shield;
        this.max_shield = shield;
        this.avoidance = avoidance;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMax_health() { return max_health; }
    public int getDamage() { return damage; }
    public int getShield() { return shield; }
    public int getMax_shield() { return max_shield; }
    public int getAvoidance() { return avoidance; }
    public int getXp() { return xp; }
    public int getX() { return x; }
    public int getY() { return y; }


    public boolean isDisabled() { return disabled; }
    public boolean isWounded() { return wounded; }
    public boolean isAlive() {
        return health > 0;
    }
    public boolean isChosen() { return chosen; }

    public void setXp(int xp) { this.xp = xp; }
    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setShield(int shield) { this.shield = shield; }
    public void setAvoidance(int avoidance) { this.avoidance = avoidance; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    public void setDisabled(boolean disabled) { this.disabled = disabled; }
    public void setWounded(boolean wounded) { this.wounded = wounded; }
    public void setChosen(boolean chosen) { this.chosen = chosen; }


    public boolean Avoided(){
        int chance = rand.nextInt(100) + 1;
        return chance <= avoidance;
    }

    public void attack(Droid target) {
        int damageToDeal = this.damage;

        if (target.Avoided()) {
            System.out.println(" " + target.getName() + Gr.B_MAGENTA + " avoided the attack from "+ Gr.RESET + this.getName());
            return;
        }


        if (target.shield > 0) {
            int remainingShield = target.shield - damageToDeal;
            if (remainingShield < 0) {
                target.shield = 0;
                target.health += remainingShield;
            } else {
                target.shield = remainingShield;
            }
        } else {
            target.health -= damageToDeal;
        }
    }

    public static void showStats(Droid droid) {
        System.out.print(" " + droid.getName()+ "'s stats: " + Gr.GREEN + "HP: " + droid.getHealth() + "/" + droid.getMax_health() + ";");
        System.out.print(Gr.RED+" Damage: " + droid.getDamage() + ";");
        System.out.print(Gr.CYAN+" Shield: " + droid.getShield() + Gr.RESET+ "/"+ Gr.CYAN+ droid.getMax_shield() + ";");
        System.out.println(Gr.MAGENTA+" Avoidance: " + droid.getAvoidance() + ";" + Gr.RESET);
        if (droid.isDisabled())
            System.out.println(" Status: " + Gr.B_RED + "disabled;" + Gr.RESET);
    }
}




