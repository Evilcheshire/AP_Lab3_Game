package droids.abilities;

import droids.Droid;

public class EnterTheShroud extends Ability {

    public EnterTheShroud() {
        super("Enter the Shroud", 4, AbilityType.SELF);
    }

    public void use(Droid caster, Droid target) {
        caster.setDisabled(2);
        caster.setAvoidance(100);
        caster.setShield(caster.getMaxShield());
        setCurrCd();
    }
}
