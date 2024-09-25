package droids.abilities;

import droids.Droid;

public class RestoreShield extends Ability {

    public RestoreShield() {
        super("Restore Shield", 2, AbilityType.ALLY);
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
