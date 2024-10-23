package battle.effects;

import battle.game_objects.droids.Droid;

public abstract class Effect {
    private String name;
    private final int DURATION;
    private int currentTurns;

    public Effect(String name, int duration) {
        this.name = name;
        this.DURATION = duration;
        this.currentTurns = duration;
    }

    public abstract void apply(Droid droid);
    public abstract void onExpired(Droid droid);

    public boolean isExpired() {
        return currentTurns <= 0;
    }

    public void reduceDuration() {
        currentTurns--;
    }

    public int getDURATION() {
        return DURATION;
    }

    public String getName() {
        return name;
    }
}