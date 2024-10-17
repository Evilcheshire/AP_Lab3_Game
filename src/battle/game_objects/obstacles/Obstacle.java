package battle.game_objects.obstacles;

import battle.enums.GameObjectTypes;
import battle.game_objects.GameObject;

public abstract class Obstacle extends GameObject {
    public Obstacle(String name, String onCollisionMessage, String FGAppearance, String BGAppearance, int damage, GameObjectTypes type) {
        super (name, type, FGAppearance, BGAppearance);
        this.onCollisionMessage = onCollisionMessage;
        this.damage = damage;
    }
}
