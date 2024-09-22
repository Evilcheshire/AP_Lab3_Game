package droids;

import graphics.Gr;

import java.util.Arrays;
import java.util.List;

public class Juggernaut extends Droid {

    public Juggernaut(String name) {
        super(name, 150, 45, 65, 5,4);
    }

    public String getName() {
        return Gr.B_RED + super.getName() + Gr.RESET;
    }

    public List<String> getSpecialAbilities() {
        return Arrays.asList("Activate laser canon", "Disable target");
    }

    public void useAbility1(Droid target){
        this.setDamage(this.getDamage()*2);
        target.setAvoidance((int)(target.getAvoidance()*1.5));
        this.attack(target);
        this.setDamage(this.getDamage()/2);
        target.setAvoidance((int)(target.getAvoidance()/1.5));
        this.setCd1(4);
    }

    public void useAbility2(Droid target){
        target.setAvoidance(0);
        target.setDisabled(3);
        this.attack(target);
        this.setCd2(5);
    }
}
