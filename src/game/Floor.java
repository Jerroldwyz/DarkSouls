package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	private Actions actions = new Actions();
	private PickUpTOSAction pickUpTOSAction;

	public Floor() {
		super('_');
	}

	@Override
	public Actions allowableActions(Actor actor, Location destination, String direction) {
		if(pickUpTOSAction == null) {
			for (Item item : destination.getItems()) {
				if (item.getClass() == TokenOfSoul.class && actor.hasCapability(Abilities.PICKUPTOS)) {
					pickUpTOSAction = new PickUpTOSAction((TokenOfSoul) item);
					actions.add(pickUpTOSAction);
					return actions;
				}
			}
		}
		return actions;
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor.hasCapability(Abilities.TOENTERFLOOR)){
			return true;
		}return false;
	}
}
