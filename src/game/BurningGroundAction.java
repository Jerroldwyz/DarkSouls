package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class BurningGroundAction extends Action {
    protected GreatMachete greatMachete;

    public BurningGroundAction(GreatMachete greatMachete){
        this.greatMachete = greatMachete;

    }

    private ArrayList<Location> burningLocation = new ArrayList<>();

    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().getClass() == Dirt.class) {
                destination.setGround(new BurningGround(actor));
                burningLocation.add(destination);

            }
        }return "Yhorm The Giant burnt its surrounding dirt.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
