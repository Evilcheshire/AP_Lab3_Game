package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.enums.MaxStats;
import battle.game_objects.droids.Droid;
import battle.Battle;

public class EnergyCharge extends Ability{
    public EnergyCharge() {
        super ("Energy Charge", 4, AbilityTypes.ENEMY);
    }

    /*
    * Is basically a ranged attack
    * */

    public void use(Droid caster, Droid target) {
        caster.getWeapon().setRange(MaxStats.MAX_RANGE.MAX);
        Battle.attack(caster, target);
        setCurrCd();
        caster.getWeapon().setRange(caster.getWeapon().getBaseRange());
    }
}
