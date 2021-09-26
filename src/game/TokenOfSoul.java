package game;

import edu.monash.fit2099.engine.*;
import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;
import game.enums.Abilities;
import game.enums.Status;
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
        return new PickUpTOSAction(this);
    }

    /**
     * Gets the allowable actions that the other actor can perform unto player
     * @param otherActor the Actor that might be performing attack
     * @param map        current GameMap
     * @return returns an attack action if there is hostile enemy nearby
     */
    public Actions getAllowableActions(Actor otherActor, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Abilities.PICKUPTOS)){
            actions.add(new PickUpTOSAction(this));
        }
        return actions;
    }

    public String toString(){
        return "Token Of Soul";
    }

}
