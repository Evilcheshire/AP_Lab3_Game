package battle.game_objects.obstacles;

import battle.enums.GameObjectTypes;
import battle.game_objects.droids.Droid;
import utils.Gr;

import static battle.Battle.attack;

public class Lava extends Obstacle{
    public Lava() {
        super ("Lava", " burned in lava!",
                Gr.BG_B_ORANGE, Gr.RED , GameObjectTypes.PASSABLE);
    }

    public void onCollision(Droid droid) {
        droid.setHealth(0);
    }
}
