package battle.arenas;

import battle.enums.GameObjectTypes;
import battle.events.ArenaEvent;
import battle.game_objects.GameObject;
import battle.game_objects.droids.Droid;
import utils.logs.BattleLogger;

import java.util.Random;

public class Arena {
    protected final int WIDTH;
    protected final int HEIGHT;
    public final GameObject[][] grid; // a grid to place game objets (droids and obstacles)
    protected final Random random;
    protected ArenaEvent event;
    private BattleLogger logger;

    public Arena(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.grid = new GameObject[WIDTH][HEIGHT];
        this.random = new Random();
        this.generateObstacles();
    }

    // getters

    public int getWIDTH() { return WIDTH; }
    public int getHEIGHT() { return HEIGHT; }
    public ArenaEvent getEvent() { return event; }

    // method checks if the position given is valid
    private boolean isValidPosition(int x, int y, GameObject object) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && (object.getX() - x <= 4 && object.getY() - y <= 4)
                && (grid[x][y] == null || !grid[x][y].isGivenType(GameObjectTypes.UNPASSABLE));
    }

    // method that checks if object have collided
    private boolean isCollision(int x, int y){
        return grid[y][x] != null && grid[y][x].isGivenType(GameObjectTypes.PASSABLE);
    }

    // method to handle collision between objects
    public void handleCollision(int x, int y, GameObject object){
            logger.log(" " + object.getName() + grid[y][x].getCollisionMessage() + "\n");
            grid[y][x].onCollision((Droid) object);
    }

    // method to place droid at the start of the combat
    public void placeObject(int y, int x, GameObject object) {
        if (isValidPosition(x, y, object)) {
            grid[y][x] = object;
            object.setPosition(y, x);
        } else
            System.out.println(" Coordinates ( " + (x + 1) + "; " + (y + 1) + " ) are already taken by " + object.getName());
    }

    // method to log messages
    public void enableLog(BattleLogger logger) {
        this.logger = logger;
    }

    // method that handles the mechanic of moving
    public boolean moveObject(int x, int y, GameObject object) {
        if (isValidPosition(x, y, object)) {
            if (isCollision(x, y))
                handleCollision(x, y, object);
            if (object.isAlive()){
                grid[object.getY()][object.getX()] = null;
                grid[y][x] = object;
                object.setPosition(y, x);
            }
            return true;
        }
        return false;
    }

    // shows the current state of the arena
    public void showArena(){

        logger.log("\n\t\t\t");
        for (int col = 1; col <= WIDTH; col++) {
            logger.log(col + " ");
        }

        for (int i = 0; i < HEIGHT; i++) {
            logger.log("\n\t\t" + (i + 1) + "\t");
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] != null && grid[i][j].isAlive()) {
                    logger.log(grid[i][j].getAppearance() + " ");
                } else {
                    logger.log(". ");
                }
            }
        }
        logger.log("\n");
    }

    // method that randomly generates obstacles for a map
    protected void generateObstacles() {}
}
