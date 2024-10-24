package battle.game_objects.droids.abilities;

import battle.enums.AbilityTypes;
import battle.game_objects.droids.Droid;

public abstract class Ability {
    protected String name;
    protected AbilityTypes type;
    protected int cd;
    protected int currCd;

    public Ability(String name, int cd, AbilityTypes type) {
        this.name = name;
        this.cd = cd;
        this.currCd = 0;
        this.type = type;
    }

    // getters

    public String getName() { return name; }
    public AbilityTypes getType() { return type; }
    public int getCurrCd() { return currCd; }

    public boolean isAvailable(){ return currCd == 0; }

    // methods to update or change current cooldown of the ability
    public void setCurrCd() { currCd = cd; }
    public void resetCurrCd() { currCd = 0; }

    public void updateCurrCd() {
        if (currCd > 0)  currCd--;
    }

    // method to generalise the usage of the ability for every droid
    public abstract void use(Droid caster, Droid target);
}
