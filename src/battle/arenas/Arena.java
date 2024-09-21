package battle.arenas;

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

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void placeDroid(int x, int y, Droid droid) {
        if (isValidPosition(x, y)) {
            grid[x][y] = droid;
            droid.setPosition(x, y);
        } else
            System.out.println(" Invalid position. Try again.");
    }

    public void moveDroid(int x, int y, Droid droid) {
        int currentX = x;
        int currentY = y;
        if (isValidPosition(x, y)) {
            grid[currentX][currentY] = null;
            grid[x][y] = droid;
            droid.setPosition(x, y);
            System.out.println(" " + droid.getName() + " moved to " + x + " " + y);
        } else
            System.out.println(" Invalid position. Try again.");
    }

    public void showArena(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getName().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
