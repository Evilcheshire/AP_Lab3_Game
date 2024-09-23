package droids.abilities;

import droids.Droid;

public class PsiShot extends Ability {

    public PsiShot() {
        super("Psi-Shot", 5, AbilityType.ENEMY);
    }

    public void use(Droid caster, Droid target){
        target.setHealth(target.getHealth() - caster.getDamage());
        setCurrCd();
    }
}
