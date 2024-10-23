package battle.game_objects.droids.weapons;

import battle.enums.HealthTypes;
import battle.game_objects.droids.abilities.Ability;

public class Weapon {
    private final String name;
    private int damage;
    private final int BASE_DMG;
    private final int additionalDamage;
    private int range;
    private final int BASE_RANGE;
    private final HealthTypes additionalDamageType;
    private final Ability ability;

    public Weapon(String name, int damage,int addDamage, HealthTypes healthType, int range, Ability ability) {
        this.name = name;
        this.damage = damage;
        this.BASE_DMG = damage;
        this.additionalDamage = addDamage;
        this.additionalDamageType = healthType;
        this.range = range;
        this.BASE_RANGE = range;
        this.ability = ability;
    }

    public String getName() { return name; }
    public int getBaseDamage() { return BASE_DMG; }
    public int getDamage() { return damage; }
    public int getAdditionalDamage() { return additionalDamage; }
    public HealthTypes getAdditionalDamageType() { return additionalDamageType; }
    public int getRange() { return range; }
    public int getBaseRange() { return BASE_RANGE; }
    public Ability getAbility() { return ability; }

    public void setDamage(int damage) { this.damage = damage; }
    public void setRange(int range) { this.range = range; }
}
