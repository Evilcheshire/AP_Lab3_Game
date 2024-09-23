package droids;

import droids.abilities.Ability;
import droids.abilities.OverloadShield;
import droids.abilities.RestoreShield;
import utils.Gr;
import java.util.*;

public class Engineer extends Droid {

    public Engineer(String name) {
        super(name, 100, 35, 70, 10, 6);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new RestoreShield());
        abilities.add(new OverloadShield());
        super.setAbilities(abilities);
    }

    public String getName() {
        return Gr.B_YELLOW + super.getName() + Gr.RESET;
    }
}