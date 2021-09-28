package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * Class representing the BonFire.
 *
 * @author Ng Kai Yi
 * @author Edmund Tai Weng Chen
 * @version 2
 * @see Ground
 */

public class Bonfire extends Ground{

    /**
     * Player's action that rest at the BonFire
     */
    private RestAction restAction;

    /**
     *The object of the Actions class
     */
    protected Actions actions = new Actions();

    /**
     * Constructor
     */
    public Bonfire(){super('B');}

    /**
     * Add RestAction object into the actions List.
     * @return a new list of actions with restaction in it.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (restAction == null) {
            restAction = new RestAction(this);
            actions.add(restAction);

        }
        return actions;
    }
    
    /**
     * Check whether the actor can enter the BonFire or not.
     * @param actor The actor performing the action.
     * @return actor has an ability to rest at the Bonfire.
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(Abilities.REST);
    }
}

