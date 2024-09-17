package droids;

public class Engineer extends Droid {

    public Engineer(String name, int health, int damage, int shield, int avoidance) {
        super(name, health, damage, shield, avoidance);
    }

    // abilities

    public void restoreShield(Droid target){
        target.setShield(target.getMax_shield());
    }

    public void overloadShield(Droid target){
        target.setShield(0);
    }
}