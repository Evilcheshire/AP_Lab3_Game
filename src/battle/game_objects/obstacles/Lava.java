package battle.game_objects.obstacles;

import battle.game_objects.droids.Droid;
import utils.Gr;

public class Lava extends Obstacle{
    public Lava() {
        super ("Lava", " burned in lava!", Gr.BG_B_ORANGE, Gr.RED );
    }

    public void onCollision(Droid droid) {
        droid.setHealth(0);
    }
}
