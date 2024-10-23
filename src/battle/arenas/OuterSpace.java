package battle.arenas;

import battle.events.TheShroudBreach;
import battle.game_objects.obstacles.Asteroid;

public class OuterSpace extends Arena {
    public OuterSpace(int WIDTH, int HEIGHT) {
        super(WIDTH, HEIGHT);
        this.event = new TheShroudBreach();
    }

    public void generateObstacles(){
        int placedObstacles = 0;
        int maxObstacles = (int) ((WIDTH * HEIGHT) - (WIDTH*2)) / 3;
        while (placedObstacles < maxObstacles) {
            placeObject(random.nextInt(Math.max(1, HEIGHT - 2)) + 1, random.nextInt(WIDTH), new Asteroid());
            placedObstacles++;
        }
    }
}
