package droids;

public class Psi_Runner extends Droid {

    public Psi_Runner(String name) {
        super(name, 80,60,50,25);
    }

    public void enterShroud(){
        this.setAvoidance(100);
        this.setShield(getMax_shield());
    }

    public void psiShot(Droid target){
        target.setHealth(this.getDamage());
    }
}
