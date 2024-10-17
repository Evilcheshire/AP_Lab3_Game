package battle.arenas;

import battle.enums.GameObjectTypes;
import battle.events.ArenaEvent;
import battle.game_objects.GameObject;
import battle.game_objects.droids.Droid;
import battle.game_objects.obstacles.Obstacle;
import utils.logs.BattleLogger;

import java.util.Random;


public class Arena {
    protected final int WIDTH;
    protected final int HEIGHT;
    protected final GameObject[][] grid; // a grid to place droids
    protected final Random random;
    protected ArenaEvent event;
    private BattleLogger logger;

    private boolean logEnabled = false;

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
                && (grid[x][y] == null || !grid[x][y].isGivenType(GameObjectTypes.DROID));
    }

    private boolean isCollision(int x, int y, GameObject object){
        return  grid[x][y] != null && grid[x][y].isGivenType(GameObjectTypes.OBSTACLE);
    }

    public void handleCollision(int x, int y, GameObject object){
        Obstacle obstacle = (Obstacle) grid[y][x];
        System.out.println(" " + object.getName() + obstacle.getCollisionMessage());
        obstacle.onCollision((Droid) object);
    }

    // method to place droid at the start of the combat
    public void placeObject(int y, int x, GameObject object) {
        if (isValidPosition(x, y, object)) {
            grid[y][x] = object;
            object.setPosition(y, x);
        } else
            System.out.println(" Coordinates ( " + (x + 1) + "; " + (y + 1) + " ) are already taken by " + object.getName());
    }

    protected void generateObstacles() {}

    // method that handles the mechanic of moving
    public boolean moveObject(int x, int y, GameObject object) {
        if (isValidPosition(x, y, object)) {
            if (isCollision(x, y, object))
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
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("\n\t\t");
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] != null && (grid[i][j].isAlive()))
                    System.out.print(grid[i][j].getAppearance()+ " ");
                else
                    System.out.print(". ");
            }
        }
        System.out.println();
    }

}
