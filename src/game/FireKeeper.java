package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

public class FireKeeper extends Actor {

    protected Actor player;
    private Broadsword broadSword = new Broadsword();
    private GiantAxe giantAxe = new GiantAxe();
    private Actions actions = new Actions();

    public FireKeeper(Actor player) {
        super("FireKeeper", 'F', Integer.MAX_VALUE);
        this.player = player;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Abilities.BUY)){
            actions.add(new BuyAction(broadSword));
            actions.add(new BuyAction(giantAxe));
        }
        return actions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
