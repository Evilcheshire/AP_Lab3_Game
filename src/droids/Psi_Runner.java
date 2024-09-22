package droids;

import graphics.Gr;
import java.util.*;

public class Psi_Runner extends Droid {

    public Psi_Runner(String name) {
        super(name, 80,60,50,25,5);
    }

    public String getName() {
        return Gr.B_CYAN + super.getName() + Gr.RESET;
    }

    public List<String> getSpecialAbilities() {
        return Arrays.asList("Enter the Shroud", "Psi-shot");
    }

    public void useAbility1(Droid target){
        this.setDisabled(2);
        this.setAvoidance(100);
        this.setShield(getMaxShield());
        this.setCd1(4);
    }

    public void useAbility2(Droid target){
        target.setHealth(target.getHealth() - this.getDamage());
        this.setCd2(4);
    }
}
