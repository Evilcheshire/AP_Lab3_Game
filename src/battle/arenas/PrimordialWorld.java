package battle.arenas;

import battle.events.AcidRain;
import battle.game_objects.obstacles.Lava;

public class PrimordialWorld extends Arena {
    public PrimordialWorld(int WIDTH, int HEIGHT) {
        super(WIDTH, HEIGHT);
        this.event = new AcidRain();
    }

    // generation of lava
    public void generateObstacles(){
        int placedObstacles = 0;
        int maxObstacles = (int) ((WIDTH * HEIGHT) - (WIDTH*2)) / 2;
        while (placedObstacles < maxObstacles) {
            placeObject(random.nextInt(Math.max(1, HEIGHT - 2)) + 1, random.nextInt(WIDTH), new Lava());
            placedObstacles++;
        }
    }
}
