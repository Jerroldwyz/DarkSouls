package game;

import edu.monash.fit2099.engine.*;
import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;

public class TokenOfSoul extends Item implements Soul, DesignOSoulsAddOn {
    private int soulCount = 0;
    private Actor player;
    private PickUpTOSAction pickUpTOSAction;

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

    /**
     * Adds the souls of the player
     * @param souls number of souls to be incremented.
     * @return a boolean value to indicate if addition is successful
     */
    @Override
    public boolean addSouls(int souls) {
        boolean success = false;
        if(souls != 0){
            soulCount += souls;
            success = true;
        }
        return success;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(actor.hasCapability(Abilities.PICKUPTOS)){
            return null;
        }return null;
    }

    public String toString(){
        return "Token Of Soul";
    }

}
