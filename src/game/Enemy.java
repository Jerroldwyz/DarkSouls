package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Soul;

public class Enemy extends Actor implements Soul {
    private int soulCount;

    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    public int getSoulCount() {
        return soulCount;
    }

    public void setSoulCount(int soulCount) {
        this.soulCount = soulCount;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulCount);
    }

}