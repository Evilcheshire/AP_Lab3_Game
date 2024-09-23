package droids.abilities;

import droids.Droid;
import utils.Gr;

public class OverloadShield extends Ability {

    public OverloadShield() {
        super("Overload Shield", 4, AbilityType.ENEMY);
    }

    public void use(Droid caster, Droid target) {
        target.setShield(0);
        target.setShieldStatus(false);
        System.out.println(" " + caster.getName() + Gr.YELLOW + " destroyed the shield of "+ Gr.RESET + target.getName());
        setCurrCd();
    }
}
