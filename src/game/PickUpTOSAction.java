package game;

import edu.monash.fit2099.engine.*;

public class PickUpTOSAction extends PickUpItemAction {

    private TokenOfSoul tokenOfSoul;

    public PickUpTOSAction(TokenOfSoul tokenOfSoul){
        super(tokenOfSoul);
        this.tokenOfSoul = tokenOfSoul;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        tokenOfSoul.asSoul().transferSouls(actor.asSoul());
        map.locationOf(actor).removeItem(tokenOfSoul);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + tokenOfSoul;
    }
}
