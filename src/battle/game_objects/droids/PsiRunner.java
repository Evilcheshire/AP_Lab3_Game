package battle.game_objects.droids;

import battle.game_objects.droids.abilities.*;
import battle.game_objects.droids.weapons.Weapon;
import utils.Gr;
import java.util.*;

public class PsiRunner extends Droid {

    public PsiRunner(String name, Weapon weapon) {
        super(name, 80, 50, 20, weapon, Gr.B_CYAN, "");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new EntranceToTheShroud());
        abilities.add(weapon.getAbility());
        super.setAbilities(abilities);
    }
}