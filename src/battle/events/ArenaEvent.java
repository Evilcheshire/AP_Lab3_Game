package battle.events;

import battle.game_objects.droids.Droid;

import java.util.List;

public interface ArenaEvent {
    // applies if occurred(calculated in battle)
    void apply(List<Droid> team1, List<Droid> team2);
    // message if occurred
    String getMessage();
}
