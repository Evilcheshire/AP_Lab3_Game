package battle.arenas;

import utils.Gr;
import droids.Droid;

public class Arena {
    private final int WIDTH;
    private final int HEIGHT;
    private final Droid[][] grid; // a grid to place droids

    public Arena(int width, int HEIGHT) {
        this.WIDTH = width;
        this.HEIGHT = HEIGHT;
        grid = new Droid[width][HEIGHT];
    }

    // getters

    public int getWIDTH() {return WIDTH;}
    public int getHEIGHT() {return HEIGHT;}

    // method checks if the position given is valid
    private boolean isValidPosition(int x, int y, Droid droid) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && (droid.getX() - x <= 4 && droid.getY() - y <= 4) && grid[x][y] == null;
    }

    // method to place droid at the start of the combat
    public void placeDroid(int y, int x, Droid droid) {
        if (isValidPosition(x, y, droid)) {
            grid[y][x] = droid;
            droid.setPosition(y, x);
        } else
            System.out.println(" Invalid position. Try again.");
    }

    // method that handles the mechanic of moving
    public boolean moveDroid(int x, int y, Droid droid) {
        if (isValidPosition(x, y, droid)) {
            grid[droid.getY()][droid.getX()] = null;
            grid[y][x] = droid;
            droid.setPosition(y, x);
        } else return false;
        return true;
    }

    // shows the current state of the arena
    public void showArena(){
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("\n\t\t");
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] != null && grid[i][j].isAlive())
                    System.out.print(grid[i][j].getName().substring(0, 6) + Gr.RESET + " ");
                else
                    System.out.print(". ");
            }
        }
        System.out.println();
    }
}
