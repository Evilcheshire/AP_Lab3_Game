package battle.game_objects.droids.abilities;

import battle.effects.DisabledEffect;
import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;

public class Disable extends Ability {

    public Disable() {
        super("Disable", 5, AbilityTypes.ENEMY);
    }

    /*
    The disabled target can't avoid the attack nor move or use abilities
    The attacker also deals damage to the target when the ability is used
    * */

    public void use (Droid caster, Droid target){
        target.setAvoidance(0);
        target.addEffect(new DisabledEffect(3));
        setCurrCd();
    }
}
