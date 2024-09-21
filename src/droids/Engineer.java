package droids;

import graphics.Gr;

public class Engineer extends Droid {

    public Engineer(String name) {
        super(name, 100, 35, 70, 10);
    }

    public String getName() {
        return Gr.B_YELLOW + super.getName() + Gr.RESET;
    }

    public void restoreShield(Droid target){
        target.setShield(target.getMax_shield());
    }

    public void overloadShield(Droid target){
        target.setShield(0);
    }
}