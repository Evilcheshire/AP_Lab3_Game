package battle.game_objects.droids.weapons;

import battle.effects.DoT;
import battle.enums.HealthTypes;
import battle.game_objects.droids.abilities.OverloadShield;

public class NanoInjector extends Weapon {
    public NanoInjector() {
        super("Nano Injector", 40, 5, HealthTypes.HEALTH,6, new OverloadShield(), new DoT("Nano-bots", 5,7));
    }
}
