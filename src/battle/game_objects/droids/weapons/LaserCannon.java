package battle.game_objects.droids.weapons;

import battle.game_objects.droids.abilities.ActivateLaserCannon;

public class LaserCannon extends Weapon {
    public LaserCannon() {
        super("Laser Cannon", 55, 0,null ,5, new ActivateLaserCannon(), null);
    }
}
