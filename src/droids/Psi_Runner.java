package droids;

public class Psi_Runner extends Droid {

    public Psi_Runner(String name, int health, int damage, int shield, int avoidance) {
        super(name, health, damage, shield, avoidance);
    }

    public void enterShroud(){
        this.setAvoidance(100);
    }

    public void psiShot(Droid target){
        target.setHealth(this.getDamage());
    }
}
