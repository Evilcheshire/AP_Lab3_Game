package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;

public class EmpBlast extends Ability {
    public EmpBlast() {
        super("EMP Blast", 3, AbilityTypes.ENEMY);
    }

    public void use(Droid caster, Droid target) {
        target.setAvoidance(0);
        target.setDisabled(1);
    }
}
