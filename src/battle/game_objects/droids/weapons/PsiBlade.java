package battle.game_objects.droids.weapons;

import battle.game_objects.droids.abilities.PsiShot;

public class PsiBlade extends Weapon {
    public PsiBlade() {
        super("Psi Blade", 70,1, new PsiShot());
    }
}