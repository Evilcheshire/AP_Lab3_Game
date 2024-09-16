package Droids;

public class Droids {
    private String name;
    private int health;
    private int damage;
    private int shield;
    private int avoidance;
    private int xp = 0;
    private boolean disabled = false;
    private boolean wounded = false;

    public Droids(String name, int health, int damage, int shield, int avoidance) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.shield = shield;
        this.avoidance = avoidance;
    }

    // Геттери
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public int getShield() { return shield; }
    public int getAvoidance() { return avoidance; }
    public int getXp() { return xp; }
    public boolean isDisabled() { return disabled; }
    public boolean isWounded() { return wounded; }

    // Сеттери
    public void setXp(int xp) { this.xp = xp; }
    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setShield(int shield) { this.shield = shield; }
    public void setAvoidance(int avoidance) { this.avoidance = avoidance; }
    public void setDisabled(boolean disabled) { this.disabled = disabled; }
    public void setWounded(boolean wounded) { this.wounded = wounded; }

    // Метод атаки
    public void attack(Droids target) {
        int damageToDeal = this.damage;

        if (target.shield > 0) {
            int remainingShield = target.shield - damageToDeal;
            if (remainingShield < 0) {
                target.shield = 0;
                target.health += remainingShield; // залишковий негативний щит зменшує здоров'я
            } else {
                target.shield = remainingShield;
            }
        } else {
            target.health -= damageToDeal;
        }
    }
}




