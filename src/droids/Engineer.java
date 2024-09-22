package droids;

import graphics.Gr;
import java.util.*;

public class Engineer extends Droid {

    public Engineer(String name) {
        super(name, 100, 35, 70, 10, 6);
    }

    public String getName() {
        return Gr.B_YELLOW + super.getName() + Gr.RESET;
    }

    public List<String> getSpecialAbilities() {
        return Arrays.asList("Restore shield", "Overload shield");
    }

    public void useAbility1(Droid target) {
        target.setShield(target.getMaxShield());
        this.setCd1(2);
    }

    public void useAbility2(Droid target) {
        System.out.println(" " + this.getName() + Gr.YELLOW + " destroyed the shield of "+ Gr.RESET + target.getName());
        target.setShieldStatus(false);
        target.setShield(0);
        this.setCd2(4);
    }
}