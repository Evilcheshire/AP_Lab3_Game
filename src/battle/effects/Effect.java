package battle.effects;

import battle.game_objects.droids.Droid;

public abstract class Effect {
    private final String name;
    private int duration;
    private final String onApplyMessage;
    private final String onExpiredMessage;

    public Effect(String name, int duration, String onApplyMessage, String onExpiredMessage) {
        this.name = name;
        this.duration = duration;
        this.onApplyMessage = onApplyMessage;
        this.onExpiredMessage = onExpiredMessage;
    }

    // methods to change droid state on application or is expired
    public abstract void apply(Droid droid);
    public abstract void onExpired(Droid droid);

    // checks if effect has expired
    public boolean isExpired() {
        return duration <= 0;
    }

    // reduces duration
    public void reduceDuration() {
        duration--;
    }

    // getters

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