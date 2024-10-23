package battle.game_objects.droids.weapons;

import battle.enums.HealthTypes;
import battle.game_objects.droids.abilities.EmpBlast;

public class IonCannon extends Weapon {
    public IonCannon() {
        super("Ion Cannon", 45, 10, HealthTypes.SHIELD, 4, new EmpBlast());
    }
}
