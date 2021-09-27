package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	private Actions actions = new Actions();
	private PickUpTOSAction pickUpTOSAction;

	public Dirt() {
		super('.');
	}

	@Override
	public Actions allowableActions(Actor actor, Location destination, String direction) {
		if(pickUpTOSAction == null) {
			for (Item item : destination.getItems()) {
				if (item.getClass() == TokenOfSoul.class && actor.hasCapability(Abilities.PICKUPTOS)) {
					pickUpTOSAction = new PickUpTOSAction((TokenOfSoul)item);
					actions.add(pickUpTOSAction);
					return actions;
				}
			}
		}
		return actions;
	}
}
