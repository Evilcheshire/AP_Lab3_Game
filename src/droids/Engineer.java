package droids;

public class Engineer extends Droid {

    public Engineer(String name) {
        super(name, 100, 35, 70, 10);
    }

    // abilities

    public void restoreShield(Droid target){
        target.setShield(target.getMax_shield());
    }

    public void overloadShield(Droid target){
        target.setShield(0);
    }
}