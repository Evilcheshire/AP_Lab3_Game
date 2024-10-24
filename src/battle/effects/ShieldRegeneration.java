package battle.effects;

import battle.game_objects.droids.Droid;

public class ShieldRegeneration extends Effect{
    public ShieldRegeneration(int duration) {
        super("Shield Regeneration", duration, "", " has regenerated its shield!");
    }

    public void apply(Droid droid) {}
    public void onExpired(Droid droid) {
        droid.setShield(droid.getShield());
    }
}
