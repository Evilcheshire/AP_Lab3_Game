package battle.game_objects.droids;

import battle.game_objects.droids.abilities.Ability;
import battle.game_objects.droids.abilities.OverloadShield;
import battle.game_objects.droids.abilities.RestoreShield;
import utils.Gr;
import java.util.*;

public class Engineer extends Droid {

    public Engineer(String name) {
        super(name, 100, 35, 70, 10, 6, Gr.B_YELLOW, "");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new RestoreShield());
        abilities.add(new OverloadShield());
        super.setAbilities(abilities);
    }
}