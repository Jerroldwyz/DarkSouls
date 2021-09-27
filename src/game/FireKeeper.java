package game;

import edu.monash.fit2099.engine.*;

public class FireKeeper extends Actor {

    protected Player player;
    private Broadsword broadSword = new Broadsword();
    private GiantAxe giantAxe = new GiantAxe();

    public FireKeeper(Player player) {
        super("FireKeeper", 'F', Integer.MAX_VALUE);
        this.player = player;
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
