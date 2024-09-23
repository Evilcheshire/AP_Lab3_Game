package droids;

import droids.abilities.*;
import utils.Gr;

import java.util.ArrayList;
import java.util.List;

public class Juggernaut extends Droid {

    public Juggernaut(String name) {
        super(name, 150, 45, 65, 5,4);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new LaserCanon());
        abilities.add(new Disable());
        super.setAbilities(abilities);
    }

    public String getName() {
        return Gr.B_RED + super.getName() + Gr.RESET;
    }
}
