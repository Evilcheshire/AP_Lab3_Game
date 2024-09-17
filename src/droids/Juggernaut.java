package droids;

public class Juggernaut extends Droid {

    public Juggernaut(String name, int health, int damage, int shield, int avoidance) {
        super(name, health, damage, shield, avoidance);
    }

    public void ActivateLaserCanon(Droid target){
        this.setDamage(this.getDamage()*2);
        target.setAvoidance((int)(target.getAvoidance()*1.5));
        this.attack(target);
    }

    public void Disable(Droid target){
        target.setAvoidance(0);
        target.setDisabled(true);
    }
}
