package battle.game_objects.droids.abilities;

import battle.effects.StunEffect;
import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;

public class EmpBlast extends Ability {
    public EmpBlast() {
        super("EMP Blast", 3, AbilityTypes.ENEMY);
    }

    /*
    Disables the target and set its avoidance to 0
    * */

    public void use(Droid caster, Droid target) {
        target.setAvoidance(0);
        target.addEffect(new StunEffect(2));
    }
}
