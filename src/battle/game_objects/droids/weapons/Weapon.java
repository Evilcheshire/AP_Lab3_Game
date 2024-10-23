package battle.game_objects.droids.weapons;

import battle.game_objects.droids.abilities.Ability;

public class Weapon {
    private final String name;
    private final int damage;
    private final int range;
    private final Ability ability;

    public Weapon(String name, int damage, int range, Ability ability) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.ability = ability;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getRange() { return range; }
    public Ability getAbility() {return ability; }
}
