package droids.abilities;

import droids.Droid;

public class OverloadShield extends Ability {

    public OverloadShield() {
        super("Overload Shield", 4, AbilityType.ENEMY);
    }

    public void use(Droid caster, Droid target) {
        target.setShield(0);
        target.setShieldStatus(false);
        setCurrCd();
    }
}
