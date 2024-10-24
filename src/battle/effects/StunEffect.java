package battle.effects;

import battle.game_objects.droids.Droid;

public class StunEffect extends Effect{
    public StunEffect(int duration) {
        super("Disabled", duration, "", " is no longer disabled!");
    }

    // has no apply effects
    public void apply(Droid droid) {}

    // restores droids base avoidance if expired
    public void onExpired(Droid droid) {
        droid.setAvoidance(droid.getBaseAvoidance());
    }
}
