package battle.game_objects.droids.weapons;

import battle.game_objects.droids.abilities.OverloadShield;

public class NanoInjector extends Weapon {
    public NanoInjector() {
        super("Nano Injector", 40, 6, new OverloadShield());
    }
}
