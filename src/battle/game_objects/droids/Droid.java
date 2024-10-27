package battle.game_objects.droids;

import battle.effects.ShieldRegeneration;
import battle.enums.GameObjectTypes;
import battle.enums.HealthTypes;
import battle.game_objects.GameObject;
import battle.game_objects.droids.weapons.Weapon;
import utils.logs.BattleLogger;
import utils.Gr;
import battle.game_objects.droids.abilities.Ability;
import battle.effects.Effect;
import java.util.*;

public class Droid extends GameObject {
    private int health;
    private final int MAX_HEALTH;
    private int shield;
    private final int MAX_SHIELD;
    private int avoidance;
    private final int BASE_AVOIDANCE;
    private boolean shield_status = true;
    private boolean chosen = false;
    private Weapon weapon;
    private List<Ability> abilities = new ArrayList<>();
    private List<Effect> activeEffects = new ArrayList<>();
    private BattleLogger logger;

    private final Random rand = new Random(); // used to calculate the avoidance

    public Droid(String name, int health, int shield, int avoidance, Weapon weapon, String FGAppearance, String BGAppearance) {
        super(name, GameObjectTypes.UNPASSABLE, FGAppearance, BGAppearance);
        this.health = health;
        this.MAX_HEALTH = health;
        this.weapon = weapon;
        this.shield = shield;
        this.MAX_SHIELD = shield;
        this.avoidance = avoidance;
        this.BASE_AVOIDANCE = avoidance;
    }

    // getters

    public int getHealth() { return health; }
    public int getMaxHealth() { return MAX_HEALTH; }
    public int getShield() { return shield; }
    public int getMaxShield() { return MAX_SHIELD; }
    public int getAvoidance() { return avoidance; }
    public int getBaseAvoidance() { return BASE_AVOIDANCE; }
    public HealthTypes getCurrHealthType() { return shield > 0 ? HealthTypes.SHIELD : HealthTypes.HEALTH; }
    public Weapon getWeapon() { return weapon; }
    public List<Ability> getAbilities() { return abilities; }
    public BattleLogger getLogger() { return logger; }

    // field status checkers

    public boolean hasShield() { return shield_status; }
    public boolean isAlive() { return health > 0; }
    public boolean isChosen() { return chosen; }
    public boolean hasEffect(String effectName) {
        for (Effect effect : activeEffects) {
            if (effect.getName().equals(effectName))
                return true;
        }
        return false;
    }

    // setters

    public void setHealth(int health) { this.health = health; }
    public void setShield(int shield) { this.shield = shield; }
    public void setAvoidance(int avoidance) { this.avoidance = avoidance; }
    public void setShieldStatus(boolean status) { this.shield_status = status; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }
    public void setChosen(boolean chosen) { this.chosen = chosen; }
    public void setAbilities(List<Ability> abilities) { this.abilities = abilities; }

    // method to enable logging
    public void enableLog(BattleLogger logger) {
        this.logger = logger;
    }

    // method to take damage
    public void takeDamage(int damage){
        if (this.getShield() > 0) {
            int remainingShield = this.getShield() - damage;
            if (remainingShield < 0) {
                this.setShield(0);
                this.setHealth(this.getHealth() + remainingShield);
                double per_health_left = (double) this.getHealth() / this.getMaxHealth();
                if (per_health_left < 0.75 && this.hasShield()) // to destroy the shield means that it can be no longer regenerated
                    this.setShieldStatus(false);
            } else
                this.setShield(remainingShield);
        } else
            this.setHealth(this.getHealth() - damage);

        // setting cooldown for the shield if it hasn't been destroyed
        if (this.hasShield() && this.getShield() != this.getMaxShield()) addEffect(new ShieldRegeneration(3));
    }

    // calculates if the droid has avoided the attack
    public boolean Avoided(){
        int chance = rand.nextInt(100);
        return chance <= avoidance;
    }

    // displays current stats of the droid(could have used toString method though)
    public void showStats() {
        if (this.isAlive()) {
            logger.log(" " + this.getName() + "'s stats: " + Gr.GREEN + " Health: " + this.getHealth() + "/" + this.getMaxHealth() + ";"
                            + Gr.RED + " Damage: " + this.weapon.getBaseDamage() + ";"
                            + Gr.CYAN + " Shield: " + this.getShield() + Gr.RESET + "/" + Gr.CYAN + this.getMaxShield() + ";"
                            + Gr.MAGENTA + " Avoidance: " + this.getAvoidance() + ";" + Gr.RESET
                            + Gr.BLUE + " Range: " + this.weapon.getRange() + ";" + Gr.RESET
                            + Gr.YELLOW + " Position: X: " + (this.getX() + 1) +  "; Y: " + (this. getY() + 1) + ";" + Gr.RESET + "\n");
            if (!activeEffects.isEmpty()){
                logger.log(" Status: " + Gr.B_MAGENTA);
                for (Effect e: activeEffects)
                    logger.log(e.getName() + "; ");
                logger.log("\n" + Gr.RESET);
            }
        } else
            logger.log(" " + this.getName() + Gr.RED + " is dead!" + Gr.RESET + "\n");
    }

    public void onCollision(Droid droid) {}

    // a refresh of the stats is required after every battle
    public void resetStats() {
        setChosen(false);
        setHealth(getMaxHealth());
        setShield(getMaxShield());
        setShieldStatus(true);
        setAvoidance(getBaseAvoidance());
        for (Ability a: abilities) a.resetCurrCd();
        activeEffects.clear();
        clearEffects();
    }

    // updating the stats is required after every turn
    public void updateStats() {
        for (Ability a: abilities) a.updateCurrCd();
        updateActiveEffects();
    }

    // adds effect by its name
    public void addEffect(Effect effect) {
        if(!hasEffect(effect.getName())){
            activeEffects.add(effect);
            effect.apply(this);
            if(!effect.getOnApplyMessage().isBlank())
                logger.log(" " + this.getName() + Gr.B_BLUE + effect.getOnApplyMessage() + Gr.RESET + "\n");
        }
    }

    // clears all active effects
    public void clearEffects(){
        activeEffects.clear();
    }

    public void removeEffect(String effectName) {
        Iterator<Effect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            if (effect.getName().equals(effectName)) {
                iterator.remove();  // Видаляємо ефект, якщо назва збігається
                break;  // Припиняємо пошук після видалення
            }
        }
    }

    // updates active effects
    private void updateActiveEffects() {
        Iterator<Effect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            effect.reduceDuration();
            if (effect.isExpired()) {
                effect.onExpired(this);
                if(!effect.getOnExpiredMessage().isBlank())
                    logger.log(" " + this.getName() + Gr.B_MAGENTA + effect.getOnExpiredMessage() + Gr.RESET + "\n");
                iterator.remove();
            } else {
                effect.apply(this);
                if(!effect.getOnApplyMessage().isBlank())
                    logger.log(" " + this.getName() + Gr.B_BLUE + effect.getOnApplyMessage() + Gr.RESET + "\n");
            }
        }
    }
}