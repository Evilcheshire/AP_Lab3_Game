package battle.effects;

import battle.game_objects.droids.Droid;

public abstract class Effect {
    private final String name;
    private final int DURATION;
    private int currentTurns;
    private final String onApplyMessage;
    private final String onExpiredMessage;

    public Effect(String name, int duration, String onApplyMessage, String onExpiredMessage) {
        this.name = name;
        this.DURATION = duration;
        this.currentTurns = duration;
        this.onApplyMessage = onApplyMessage;
        this.onExpiredMessage = onExpiredMessage;
    }

    public abstract void apply(Droid droid);
    public abstract void onExpired(Droid droid);

    public boolean isExpired() {
        return currentTurns <= 0;
    }

    public void reduceDuration() {
        currentTurns--;
    }

    public String getName() {
        return name;
    }

    public String getOnApplyMessage() {
        return onApplyMessage;
    }

    public String getOnExpiredMessage() {
        return onExpiredMessage;
    }
}