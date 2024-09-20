package droids;

public class Juggernaut extends Droid {

    public Juggernaut(String name) {
        super(name, 150, 45, 65, 5);
    }

    public void ActivateLaserCanon(Droid target){
        this.setDamage(this.getDamage()*2);
        target.setAvoidance((int)(target.getAvoidance()*1.5));
        this.attack(target);
    }

    public void Disable(Droid target){
        target.setAvoidance(0);
        target.setDisabled(true);
        this.attack(target);
    }
}
