package battle.enums;

// max values of stats
public enum MaxStats {
    MAX_HEALTH(500), MAX_SHIELD(100), MAX_AVOIDANCE(100), MAX_DAMAGE(999), MAX_RANGE(999);

    public final int MAX;
    MaxStats(int max){
        this.MAX = max;
    }
}
