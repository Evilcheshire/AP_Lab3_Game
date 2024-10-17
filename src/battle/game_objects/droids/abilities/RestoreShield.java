package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;

public class RestoreShield extends Ability {

    public RestoreShield() {
        super("Restore Shield", 2, AbilityTypes.ALLY);
    }

    /*
    Fully restores the shield if the target, it can now be regenerated
    */
    public void use(Droid caster, Droid target) {
        target.setShield(target.getMaxShield());
        target.setShieldStatus(true);
        target.setShieldCD(0);
        setCurrCd();
    }
}
