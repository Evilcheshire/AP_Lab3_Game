package droids.abilities;

import droids.Droid;

public class Disable extends Ability {

    public Disable() {
        super("Disable", 5, AbilityType.ENEMY);
    }

    public void use (Droid caster, Droid target){
        target.setAvoidance(0);
        target.setDisabled(3);
        caster.attack(target);
        setCurrCd();
    }
}
