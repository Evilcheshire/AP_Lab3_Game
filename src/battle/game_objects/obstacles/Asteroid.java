package battle.game_objects.obstacles;

import battle.enums.GameObjectTypes;
import battle.game_objects.droids.Droid;
import utils.Gr;

public class Asteroid extends Obstacle{
    public Asteroid(){
        super("Asteroid", " collided with an asteroid!",
                Gr.BLUE_GRAY, "", GameObjectTypes.PASSABLE);
    }

    public void onCollision(Droid droid) {
        droid.setAvoidance(0);
        droid.takeDamage(50);
        droid.setAvoidance(droid.getBaseAvoidance());
    }
}
