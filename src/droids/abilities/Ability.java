package droids.abilities;

import droids.Droid;

public abstract class Ability {
    public enum AbilityType {
        SELF, ENEMY, ALLY
    }

    protected String name;
    protected AbilityType type;
    protected int cd;
    protected int currCd;


    public Ability(String name, int cd, AbilityType type) {
        this.name = name;
        this.cd = cd;
        this.currCd = 0;
        this.type = type;
    }

    public boolean isAvailable(){ return currCd == 0; }

    public void setCurrCd() { currCd = cd; }
    public void resetCurrCd() { currCd = 0; }

    public void updateCurrCd() { if (currCd > 0)  currCd--; }

    public abstract void use(Droid caster, Droid target);

    public String getName() { return name; }
    public AbilityType getType() { return type; }
    public int getCurrCd() { return currCd; }
}
