package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;
import battle.Battle;

public class EnergyCharge extends Ability{
    public EnergyCharge() {
        super ("Energy Charge", 4, AbilityTypes.ENEMY);
    }

    public void use(Droid caster, Droid target) {
        caster.getWeapon().setRange(999);
        Battle.attack(caster, target);
        setCurrCd();
        caster.getWeapon().setRange(caster.getWeapon().getBaseRange());
    }
}
