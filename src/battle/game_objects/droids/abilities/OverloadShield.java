package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;
import utils.Gr;

public class OverloadShield extends Ability {

    public OverloadShield() {
        super("Overload Shield", 4, AbilityTypes.ENEMY);
    }

    /*
    Destroys the shield of the target, it can't be regenerated, unless the "Restore Shield" is used
    */

    public void use(Droid caster, Droid target) {
        target.setShield(0);
        target.setShieldStatus(false);
        if(caster.getLogEnabled()) caster.getLogger().log(" " + caster.getName() + Gr.YELLOW + " destroyed the shield of "+ Gr.RESET + target.getName());
        setCurrCd();
    }
}