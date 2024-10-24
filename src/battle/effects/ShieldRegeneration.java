package battle.effects;

import battle.game_objects.droids.Droid;

public class ShieldRegeneration extends Effect{
    public ShieldRegeneration(int duration) {
        super("Shield Regeneration", duration, "", " has regenerated its shield!");
    }

    // has no effects if applied
    public void apply(Droid droid) {}
    // if expired, restores droids shield
    public void onExpired(Droid droid) {
        droid.setShield(droid.getMaxShield());
    }
}
