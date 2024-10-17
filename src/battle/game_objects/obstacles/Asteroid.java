package battle.game_objects.obstacles;

import battle.enums.GameObjectTypes;
import battle.game_objects.droids.Droid;
import utils.Gr;

import static battle.Battle.attack;

public class Asteroid extends Obstacle{
    public Asteroid(){
        super("Asteroid", " collided with an asteroid!",
                Gr.BLUE_GRAY, "", 50, GameObjectTypes.PASSABLE);
    }

    public void onCollision(Droid droid) {
        droid.setAvoidance(0);
        attack(this, droid);
        droid.setAvoidance(droid.getBaseAvoidance());
    }
}
