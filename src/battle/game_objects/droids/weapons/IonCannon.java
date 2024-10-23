package battle.game_objects.droids.weapons;

import battle.game_objects.droids.abilities.EmpBlast;

public class IonCannon extends Weapon {
    public IonCannon() {
        super("Ion Cannon", 40, 4, new EmpBlast());
    }
}
