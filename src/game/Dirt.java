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
		if (!destination.getItems().isEmpty()) {
			for (Item item : destination.getItems()) {
				pickUpTOSAction = new PickUpTOSAction((TokenOfSoul) item);
				if (item.getClass() == TokenOfSoul.class && actor.hasCapability(Abilities.PICKUPTOS)) {
					actions.add(pickUpTOSAction);
					return actions;
				}
			}
		}return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}
}
