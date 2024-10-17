package battle.game_objects.droids;

import battle.game_objects.droids.abilities.*;
import utils.Gr;

import java.util.ArrayList;
import java.util.List;

public class Juggernaut extends Droid {

    public Juggernaut(String name) {
        super(name, 150, 40, 65, 5,4, Gr.B_RED, "");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new LaserCanon());
        abilities.add(new Disable());
        super.setAbilities(abilities);
    }
}
