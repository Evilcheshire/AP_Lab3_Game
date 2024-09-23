package droids.abilities;

import droids.Droid;

public class RestoreShield extends Ability {

    public RestoreShield() {
        super("Restore Shield", 2, AbilityType.ALLY);
    }

    public void use(Droid caster, Droid target) {
        target.setShield(target.getMaxShield());
        target.setShieldStatus(true);
        target.setShieldCD(0);
        setCurrCd();
    }
}
