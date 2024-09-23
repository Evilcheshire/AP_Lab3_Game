package battle.arenas;

import graphics.Gr;
import droids.Droid;

public class Arena {
    private final int width;
    private final int height;
    private final Droid[][] grid;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Droid[width][height];
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}


    private boolean isValidPosition(int x, int y, Droid droid) {
        return x >= 0 && x < width && y >= 0 && y < height && (droid.getX() - x <= 4 && droid.getY() - y <= 4);
    }

    public void placeDroid(int x, int y, Droid droid) {
        if (isValidPosition(x, y, droid)) {
            grid[y][x] = droid;
            droid.setPosition(x, y);
        } else
            System.out.println(" Invalid position. Try again.");
    }

    public void moveDroid(int x, int y, Droid droid) {
        if (isValidPosition(x, y, droid)) {
            grid[droid.getX()][droid.getY()] = null;
            grid[y][x] = droid;
            droid.setPosition(x, y);
            System.out.println(" " + droid.getName() + " moved to (" + x + "; " + y + ")");
        } else
            System.out.println(" Invalid position. Try again.");
    }

    public void showArena(){
        for (int i = 0; i < height; i++) {
            System.out.print("\n\t\t");
            for (int j = 0; j < width; j++) {
                if (grid[i][j] != null && grid[i][j].isAlive()) {
                    System.out.print(grid[i][j].getName().substring(0, 6) + Gr.RESET + " ");
                } else {
                    System.out.print(". ");
                }
            }

        }
        System.out.println();
    }
}
