package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

public class Chest extends Ground {

    public Chest() {super('?');}

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if(actor.hasCapability(Abilities.OPENCHEST)){
            actions.add(new OpenChestAction());
        }
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
