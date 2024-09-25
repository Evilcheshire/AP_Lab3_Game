package droids.abilities;

import droids.Droid;

public abstract class Ability {
    public enum AbilityType { // type of the target for abilities
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

    // getters

    public String getName() { return name; }
    public AbilityType getType() { return type; }
    public int getCurrCd() { return currCd; }

    public boolean isAvailable(){ return currCd == 0; }

    // methods to update or change current cooldown of the ability

    public void setCurrCd() { currCd = cd; }
    public void resetCurrCd() { currCd = 0; }

    public void updateCurrCd() { if (currCd > 0)  currCd--; }

    // method to generalise the usage of the ability for every droid
    public abstract void use(Droid caster, Droid target);
}
