package game;

import edu.monash.fit2099.engine.Actor;

public class Broadsword extends MeleeWeapon{

    private int soulPrice = 500;

    public Broadsword() {
        super("Broadsword", 'B', 30, "hits", 80);
    }


    public int getSoulPrice() {
        return soulPrice;
    }
}
