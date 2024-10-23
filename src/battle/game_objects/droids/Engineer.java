package battle.game_objects.droids;

import battle.game_objects.droids.abilities.Ability;
import battle.game_objects.droids.abilities.OverloadShield;
import battle.game_objects.droids.abilities.RestoreShield;
import battle.game_objects.droids.weapons.Weapon;
import utils.Gr;
import java.util.*;

public class Engineer extends Droid {

    public Engineer(String name, Weapon weapon) {
        super(name, 100, 65, 15, weapon, Gr.B_YELLOW, "");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new RestoreShield());
        abilities.add(weapon.getAbility());
        super.setAbilities(abilities);
    }
}