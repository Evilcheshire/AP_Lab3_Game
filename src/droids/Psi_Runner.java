package droids;

import droids.abilities.*;
import utils.Gr;
import java.util.*;

public class Psi_Runner extends Droid {

    public Psi_Runner(String name) {
        super(name, 80, 50, 50, 25, 5);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new EnterTheShroud());
        abilities.add(new PsiShot());
        super.setAbilities(abilities);
    }

    public String getName() {
        return Gr.B_CYAN + super.getName() + Gr.RESET;
    }
}