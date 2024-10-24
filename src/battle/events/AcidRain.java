package battle.events;

import battle.game_objects.droids.Droid;
import utils.Gr;

import java.util.List;
import java.util.Random;

public class AcidRain implements ArenaEvent {
    private final Random random = new Random();
    private boolean started = false;

    // deals 15 damage to health for each droid in every team
    public void apply(List<Droid> team1, List<Droid> team2) {
        if (random.nextDouble() <= 0.4) {
            started = true;
            damageDroids(team1);
            damageDroids(team2);
        }
    }

    public String getMessage() {
        return started ? Gr.B_GREEN + " \tThe Acid rain has started!\n" +
            " Every droid receives 15 health damage!\n" + Gr.RESET : "";
    }

    private void damageDroids(List<Droid> team) {
        for (Droid droid : team)
            droid.setHealth(droid.getHealth() - 15);
    }
}
