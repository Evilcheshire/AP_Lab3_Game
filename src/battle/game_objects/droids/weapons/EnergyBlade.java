package battle.game_objects.droids.weapons;

import battle.enums.HealthTypes;
import battle.game_objects.droids.abilities.EnergyCharge;

public class EnergyBlade extends Weapon{
    public EnergyBlade () {
        super("Energy Blade", 50, 10, HealthTypes.SHIELD,1, new EnergyCharge(), null);
    }
}
