package game;

import edu.monash.fit2099.engine.*;

public class PickUpTOSAction extends PickUpItemAction {

    private TokenOfSoul tokenOfSoul;
    private ActorLocations actorLocations;

    public PickUpTOSAction(TokenOfSoul tokenOfSoul){
        super(tokenOfSoul);
        this.tokenOfSoul = tokenOfSoul;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
//        tokenOfSoul.asSoul().transferSouls(actor.asSoul());
        for(Exit exit : map.locationOf(actor).getExits()){
            Location location = exit.getDestination();
            if(location.getItems().contains(tokenOfSoul)){
                tokenOfSoul.asSoul().transferSouls(actor.asSoul());
                location.removeItem(tokenOfSoul);
                return menuDescription(actor);
            }
        }
        tokenOfSoul.asSoul().transferSouls(actor.asSoul());
        map.locationOf(actor).removeItem(tokenOfSoul);
        return menuDescription(actor);
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + tokenOfSoul;
    }
}
