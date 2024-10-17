package battle.game_objects.obstacles;

import battle.enums.ObstacleTypes;
import battle.game_objects.droids.Droid;
import utils.Gr;

public class Lava extends Obstacle{
    public Lava() {
        super ("Lava", " burned in lava!", Gr.BG_B_ORANGE, Gr.RED );
        this.type = ObstacleTypes.LAVA;
    }

    public void onCollision(Droid droid) {
        droid.setHealth(0);
    }
}
