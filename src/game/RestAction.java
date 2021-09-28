package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * A rest Action where player can rest at the BonFire when he is near to it.
 *
 * @author Ng Kai Yi
 * @author Edmund Tai Weng Chen
 * @version 2
 * @see Action
 */

public class RestAction extends Action{

    /**
     * player's reset location
     */
    private Bonfire bonfire;

    /**
     * Player's Attributes
     */
    protected  Player player;

    /**
     * Estus Flask's Attributes
     */
    protected EstusFlask estusFlask;

    /**
     * Constructor.
     *
     * @param bonfire bonfire's Attributes
     */
    public RestAction(Bonfire bonfire){this.bonfire = bonfire;}

    /**
     * This method is used to execute the RestAction class where the player will undergo soft reset.
     * actors.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result, a descriptive message of telling the player he is resting at the BonFire.
     */
    @Override
    public String execute(Actor actor, GameMap map){

        String result = "The " + actor;

        //if player's hp is below zero
        if (map.locationOf(actor).getGround().getClass() == Bonfire.class ){
            map.moveActor(actor, map.at(38,12));
        }

        Player player = (Player) actor;
        player.heal(1000);
        player.getEstusFlask().setCharge(3);
        result += " took a good rest";
        return result;

    }

    /**
     * Descriptive message to describe the actor is resting at the Bonfire.
     * @param actor The actor performing the action.
     * @return a string of descriptive message
     */
    @Override
    public String menuDescription(Actor actor){
        if (actor.hasCapability(Abilities.REST)){
            return actor + " rest at Bonfire.";
        }
        return null;
    }
}
