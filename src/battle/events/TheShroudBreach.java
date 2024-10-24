package battle.events;

import battle.enums.MaxStats;
import battle.game_objects.droids.Droid;
import utils.Gr;

import java.util.List;
import java.util.Random;

public class TheShroudBreach implements ArenaEvent{
    private final Random random = new Random();

    // boosts avoidance chance fr each droid in every team
    public void apply(List<Droid> team1, List<Droid> team2) {
        if (random.nextDouble() <= 0.6) {
            boostDodgeChance(team1);
            boostDodgeChance(team2);
        }
    }

    public String getMessage() {
        return Gr.B_MAGENTA + " \tThe Shroud is breached!\n" +
            " Every droid receives +5 avoidance!\n" + Gr.RESET;
    }

    private void boostDodgeChance(List<Droid> team) {
        for (Droid droid : team)
                droid.setAvoidance(Math.min(droid.getAvoidance() + 5, MaxStats.MAX_AVOIDANCE.MAX));
    }
}
