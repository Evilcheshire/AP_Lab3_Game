package battle.effects;

import battle.game_objects.droids.Droid;

public class DoT extends Effect{
    private final int damagePerTurn;

    public DoT(String name, int duration, int damagePerTurn) {
        super(name, duration);
        this.damagePerTurn = damagePerTurn;
    }

    public void apply(Droid target) {
        target.takeDamage(damagePerTurn);
        System.out.println(target.getName() + " takes " + damagePerTurn + " damage from " + getName());
        reduceDuration();
    }

    public void onExpired(Droid droid) {}
}
