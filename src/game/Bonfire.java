package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

import java.util.List;

public class Bonfire extends Ground{

    private RestAction restAction;
    protected Actions actions = new Actions();


    public Bonfire(){super('B');}

    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (restAction == null) {
            restAction = new RestAction(this);
            actions.add(restAction);

        }return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor){
        if(actor.hasCapability(Abilities.REST)){
            return true;

        }return false;
    }
}
