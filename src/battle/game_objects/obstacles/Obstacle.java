package battle.game_objects.obstacles;

import battle.enums.GameObjectTypes;
import battle.game_objects.GameObject;

public abstract class Obstacle extends GameObject {
    public Obstacle(String name, String onCollisionMessage, String FGAppearance, String BGAppearance) {
        super (name, GameObjectTypes.OBSTACLE, FGAppearance, BGAppearance);
        this.onCollisionMessage = onCollisionMessage;
    }
}
