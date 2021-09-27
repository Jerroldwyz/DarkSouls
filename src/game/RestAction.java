package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

public class RestAction extends Action{

    /**
     * player's reset location
     */
    private BonFire bonfire;
    protected  Player player;
    protected EstusFlask estusFlask;


    public RestAction(BonFire bonfire){this.bonfire = bonfire;}


    @Override
    public String execute(Actor actor, GameMap map){

        String result = "The " + actor;

        //if player's hp is below zero
        if (map.locationOf(actor).getGround().getClass() == BonFire.class ){
            map.moveActor(actor, map.at(38,12));
            player.heal(100);
            estusFlask.setCharge(3);
            result += " took a good rest";

        }
        //return String
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        if (actor.hasCapability(Abilities.REST)){
            return actor + " rest at Bonfire.";
        }
        return null;
    }
}
