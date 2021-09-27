package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

public class PickUpTOSAction extends PickUpItemAction {

    private TokenOfSoul tokenOfSoul;

    public PickUpTOSAction(TokenOfSoul tokenOfSoul){
        super(tokenOfSoul);
        this.tokenOfSoul = tokenOfSoul;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Location here = map.locationOf(actor);

        for(Exit exit : here.getExits()){
            Location location = exit.getDestination();
            if(location.getItems().contains(tokenOfSoul)){
                tokenOfSoul.asSoul().transferSouls(actor.asSoul());
                location.removeItem(tokenOfSoul);
                if(location.getGround().getClass() == Floor.class){
                    Floor floor = (Floor)location.getGround();
                    floor.setActions(new Actions());
                }else{
                    Dirt dirt = (Dirt)location.getGround();
                    dirt.setActions(new Actions());
                }
                result += tokenOfSoul + " has been picked up";
            }
        }
        tokenOfSoul.asSoul().transferSouls(actor.asSoul());
        here.removeItem(tokenOfSoul);
        if(here.getGround().getClass() == Floor.class){
            Floor floor = (Floor)here.getGround();
            floor.setActions(new Actions());
        }else{
            Dirt dirt = (Dirt)here.getGround();
            dirt.setActions(new Actions());
        }
        result += tokenOfSoul + " has been picked up";
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " holds up the " + tokenOfSoul;
    }
}
