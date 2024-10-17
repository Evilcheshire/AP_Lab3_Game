package battle.game_objects.obstacles;

import battle.game_objects.droids.Droid;
import utils.Gr;

public class Asteroid extends Obstacle{
    public Asteroid(){
        super("Asteroid", " collided with an asteroid!", Gr.BLUE_GRAY, "");
    }

    public void onCollision(Droid droid) {
        droid.setHealth(droid.getHealth() - 50);
    }
}
