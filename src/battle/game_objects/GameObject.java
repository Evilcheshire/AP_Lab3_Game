package battle.game_objects;

import battle.enums.GameObjectTypes;
import battle.game_objects.droids.Droid;
import utils.Gr;

public abstract class GameObject {
    private final String name;
    private final GameObjectTypes type;
    private final String FGAppearance;
    private final String BGAppearance;
    protected String onCollisionMessage;
    private int x;
    private int y;

    public GameObject(String name, GameObjectTypes type, String FGAppearance, String BGAppearance) {
       this.name = name;
       this.type = type;
       this.FGAppearance = FGAppearance;
       this.BGAppearance = BGAppearance;
    }

    // getters

    public String getName() { return FGAppearance + name + Gr.RESET; }
    public String getAppearance() { return BGAppearance + FGAppearance + name.charAt(0) + Gr.RESET;}

    // setters

    public void setPosition(int y, int x) { this.x = x; this.y = y; }
    public String getCollisionMessage() { return this.onCollisionMessage; }
    public abstract void onCollision(Droid droid);

    // status checkers

    public boolean isAlive() { return true; }
    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isGivenType(GameObjectTypes type) { return this.type == type; }


}
