package droids;

import graphics.Gr;

public class Juggernaut extends Droid {

    public Juggernaut(String name) {
        super(name, 150, 45, 65, 5);
    }

    public String getName() {
        return Gr.B_RED + super.getName() + Gr.RESET;
    }

    public void ActivateLaserCanon(Droid target){
        this.setDamage(this.getDamage()*2);
        target.setAvoidance((int)(target.getAvoidance()*1.5));
        this.attack(target);
        this.setDamage(this.getDamage()/2);
        target.setAvoidance((int)(target.getAvoidance()/1.5));
    }

    public void Disable(Droid target){
        target.setAvoidance(0);
        target.setDisabled(true);
        this.attack(target);
    }
}
