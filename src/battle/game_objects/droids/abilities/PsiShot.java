package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;

public class PsiShot extends Ability {

    public PsiShot() {
        super("Psi-Shot", 5, AbilityTypes.ENEMY);
    }

    /*
    Deals damage to the target ignoring its shield
    */
    public void use(Droid caster, Droid target){
        target.setHealth(target.getHealth() - caster.getDamage());
        setCurrCd();
    }
}
