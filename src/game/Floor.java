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
			if(!destination.getItems().isEmpty()){
				for (Item item : destination.getItems()) {
					pickUpTOSAction = new PickUpTOSAction((TokenOfSoul) item);
					if (item.getClass() == TokenOfSoul.class && actor.hasCapability(Abilities.PICKUPTOS)) {
						actions.add(pickUpTOSAction);
						return actions;
					}
				}
			}
		}
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Abilities.TOENTERFLOOR);
	}
}
