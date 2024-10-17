package battle.game_objects;

import battle.enums.GameObjectTypes;
import battle.game_objects.droids.Droid;
import utils.Gr;

public abstract class GameObject {

    private final String name;
    private final GameObjectTypes type;
    protected String FGAppearance;
    protected String BGAppearance;
    protected String onCollisionMessage;
    private int x;
    private int y;

    public GameObject(String name, GameObjectTypes type, String FGAppearance, String BGAppearance) {
       this.name = name;
       this.type = type;
       this.FGAppearance = FGAppearance;
       this.BGAppearance = BGAppearance;
    }

    public String getName() { return BGAppearance + FGAppearance + name + Gr.RESET; }
    public String getAppearance() { return BGAppearance + FGAppearance + name.charAt(0) + Gr.RESET;}
    public boolean isAlive() { return true; }
    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isGivenType(GameObjectTypes type) { return this.type == type; }

    public void setPosition(int y, int x) { this.x = x; this.y = y; }
    public String getCollisionMessage() { return this.onCollisionMessage; }
    public abstract void onCollision(Droid droid);
}
