package battle.events;

import battle.game_objects.droids.Droid;

import java.util.List;

public interface ArenaEvent {
    void apply(List<Droid> team1, List<Droid> team2);
    String getMessage();
}
