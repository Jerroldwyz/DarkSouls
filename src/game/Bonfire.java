package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import java.util.List;

/**
 * Class representing the BonFire.
 */
public class Bonfire extends Ground{

    private RestAction restAction;


    protected Actions actions = new Actions();

    /**
     * Constructor
     */
    public Bonfire(){super('B');}


    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (restAction == null) {
            restAction = new RestAction(this);
            actions.add(restAction);

        }return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(Abilities.REST);
    }
}

