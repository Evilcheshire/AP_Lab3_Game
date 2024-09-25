package droids.abilities;

import droids.Droid;

public class LaserCanon extends Ability {

    public LaserCanon() {
        super("Laser canon", 4, AbilityType.ENEMY);
    }

    /*
    The attacker deals double damage to the target but this target has 50% more chance of avoiding the attack
    */

    public void use(Droid caster, Droid target){
        caster.setDamage(caster.getDamage()*2);
        target.setAvoidance((int)(target.getAvoidance()*1.5));
        caster.attack(target);
        caster.setDamage(caster.getDamage()/2);
        target.setAvoidance(target.getBaseAvoidance());
        setCurrCd();
    }
}
