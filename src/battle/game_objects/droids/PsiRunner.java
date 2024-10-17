package battle.game_objects.droids;

import battle.game_objects.droids.abilities.*;
import utils.Gr;
import java.util.*;

public class PsiRunner extends Droid {

    public PsiRunner(String name) {
        super(name, 80, 50, 50, 25, 5, Gr.B_CYAN, "");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new EnterTheShroud());
        abilities.add(new PsiShot());
        super.setAbilities(abilities);
    }
}