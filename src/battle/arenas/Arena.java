package battle.arenas;

import utils.Gr;
import droids.Droid;

public class Arena {
    private final int width;
    private final int height;
    private final Droid[][] grid; // a grid to place droids

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Droid[width][height];
    }

    // getters

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    // method checks if the position given is valid
    private boolean isValidPosition(int x, int y, Droid droid) {
        return x >= 0 && x < width && y >= 0 && y < height && (droid.getX() - x <= 4 && droid.getY() - y <= 4) && grid[x][y] == null;
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
        for (int i = 0; i < height; i++) {
            System.out.print("\n\t\t");
            for (int j = 0; j < width; j++) {
                if (grid[i][j] != null && grid[i][j].isAlive())
                    System.out.print(grid[i][j].getName().substring(0, 6) + Gr.RESET + " ");
                else
                    System.out.print(". ");
            }
        }
        System.out.println();
    }
}
