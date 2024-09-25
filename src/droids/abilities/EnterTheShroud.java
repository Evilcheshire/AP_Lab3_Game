package droids.abilities;

import droids.Droid;

public class EnterTheShroud extends Ability {

    public EnterTheShroud() {
        super("Enter the Shroud", 4, AbilityType.SELF);
    }

    /*
    While in the Shroud the droid can't move nor use abilities nor attack and skips the turn
    The droid also is unreachable for any attack for this time
    the shield is also regenerated but if it has been destroyed before this status remains and the droid is incapable of regenerating it
    */

    public void use(Droid caster, Droid target) {
        caster.setDisabled(2);
        caster.setAvoidance(100);
        caster.setShield(caster.getMaxShield());
        setCurrCd();
    }
}
