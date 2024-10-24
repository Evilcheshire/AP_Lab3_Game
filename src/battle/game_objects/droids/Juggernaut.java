package battle.game_objects.droids;

import battle.game_objects.droids.abilities.*;
import battle.game_objects.droids.weapons.Weapon;
import utils.Gr;

import java.util.ArrayList;
import java.util.List;

public class Juggernaut extends Droid {

    public Juggernaut(String name, Weapon weapon) {
        super(name, 140, 40, 10, weapon, Gr.B_RED, "");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new Stun());
        abilities.add(weapon.getAbility());
        super.setAbilities(abilities);
    }
}
