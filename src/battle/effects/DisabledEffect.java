package battle.effects;

import battle.game_objects.droids.Droid;

public class DisabledEffect extends Effect{
    public DisabledEffect(int duration) {
        super("Disabled", duration);
    }

    public void apply(Droid droid) {}
    public void onExpired(Droid droid) {}
}
