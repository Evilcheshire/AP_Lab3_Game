package battle.effects;

import battle.game_objects.droids.Droid;

public class DoT extends Effect{
    private final int damagePerTurn;

    public DoT(String name, int duration, int damagePerTurn) {
        super(name, duration, " takes " + damagePerTurn + " damage from " + name + "!", "");
        this.damagePerTurn = damagePerTurn;
    }

    // deals damage over time if applied
    public void apply(Droid target) {
        target.takeDamage(damagePerTurn);
    }

    // has no effect on expired
    public void onExpired(Droid droid) {}
}
