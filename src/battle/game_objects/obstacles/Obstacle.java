package battle.game_objects.obstacles;

import battle.enums.GameObjectTypes;
import battle.enums.ObstacleTypes;
import battle.game_objects.GameObject;
import battle.game_objects.droids.Droid;

public abstract class Obstacle extends GameObject {

    protected String onCollisionMessage;
    protected ObstacleTypes type;

    public Obstacle(String name, String onCollisionMessage, String FGAppearance, String BGAppearance) {
        super (name, GameObjectTypes.OBSTACLE, FGAppearance, BGAppearance);
        this.onCollisionMessage = onCollisionMessage;
    }

    public ObstacleTypes getObstacleType() { return this.type; }
    public boolean isGivenObstacleType(ObstacleTypes type) { return this.type == type; }
    public abstract void onCollision(Droid droid);
}
