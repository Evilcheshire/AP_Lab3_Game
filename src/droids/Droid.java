package droids;

public class Droid {
    private String name;
    private int health;
    private int max_health;
    private int damage;
    private int shield;
    private int max_shield;
    private int avoidance;
    private int xp = 0;
    private boolean disabled = false;
    private boolean wounded = false;

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
    public boolean isDisabled() { return disabled; }
    public boolean isWounded() { return wounded; }

    public void setXp(int xp) { this.xp = xp; }
    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setShield(int shield) { this.shield = shield; }
    public void setAvoidance(int avoidance) { this.avoidance = avoidance; }
    public void setDisabled(boolean disabled) { this.disabled = disabled; }
    public void setWounded(boolean wounded) { this.wounded = wounded; }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Droid target) {
        int damageToDeal = this.damage;

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
}




