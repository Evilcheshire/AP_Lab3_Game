package battle.events;

import battle.game_objects.droids.Droid;
import utils.Gr;

import java.util.List;
import java.util.Random;

public class TheShroudBreach implements ArenaEvent{
    private final Random random = new Random();

    public void apply(List<Droid> team1, List<Droid> team2) {
        if (random.nextDouble() <= 0.6) {
            boostDodgeChance(team1);
            boostDodgeChance(team2);
        }
    }

    public String getMessage() {return Gr.B_MAGENTA + " \tThe Shroud is breached!\n" +
            " Every droid receives +5 avoidance!" + Gr.RESET; }

    private void boostDodgeChance(List<Droid> team) {
        for (Droid droid : team) {
            droid.setAvoidance(droid.getAvoidance() + 5);
        }
    }
}
