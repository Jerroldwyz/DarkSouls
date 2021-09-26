package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;
import game.interfaces.Soul;

public class TokenOfSoul extends Item implements Soul, DesignOSoulsAddOn {
    private int soulCount = 0;
    private Actor player;

    public TokenOfSoul(String name, Actor target){
        super(name, '$', true);
        player = target;
    }

    public int getSoulCount() {
        return soulCount;
    }

    public boolean setSoulCount(int souls){
        boolean flag = false;
        if(souls >= 0){
            flag = true;
            soulCount = souls;
        }
        return flag;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soulCount);
        this.setSoulCount(0);
    }

}
