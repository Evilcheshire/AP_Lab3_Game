package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.Battle;
import battle.game_objects.droids.Droid;

public class LaserCannonActivation extends Ability {

    public LaserCannonActivation() {
        super("Laser canon", 4, AbilityTypes.ENEMY);
    }

    /*
    The attacker deals double damage to the target but this target has 50% more chance of avoiding the attack
    */
    public void use(Droid caster, Droid target){
        caster.getWeapon().setDamage(caster.getWeapon().getDamage()*2);
        target.setAvoidance((int)(target.getAvoidance()*1.5));
        Battle.attack(caster, target);
        caster.getWeapon().setDamage(caster.getWeapon().getBaseDamage());
        target.setAvoidance(target.getBaseAvoidance());
        setCurrCd();
    }
}
