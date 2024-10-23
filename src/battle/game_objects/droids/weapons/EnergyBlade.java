package battle.game_objects.droids.weapons;

import battle.game_objects.droids.abilities.EnergyCharge;

public class EnergyBlade extends Weapon{
    public EnergyBlade () {
        super("Energy Blade", 60, 1, new EnergyCharge());
    }
}
