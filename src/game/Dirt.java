package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	private Actions actions = new Actions();
	private PickUpTOSAction pickUpTOSAction;
	private SwapWeaponAction swapWeaponAction;

	public Dirt() {
		super('.');
	}

	@Override
	public Actions allowableActions(Actor actor, Location destination, String direction) {
		if(!destination.getItems().isEmpty()){
			for (Item item : destination.getItems()) {
				if(item.getClass() == TokenOfSoul.class && actor.hasCapability(Abilities.PICKUPTOS)) {
					pickUpTOSAction = new PickUpTOSAction((TokenOfSoul) item);
					actions.add(pickUpTOSAction);
					return actions;
				} else if(item.getClass() == StormRuler.class && actor.hasCapability(Abilities.PICKUPSTORMRULER)){
					//if its not token of soul its storm ruler
					if(swapWeaponAction == null) {
						swapWeaponAction = new SwapWeaponAction(item);
						actions.add(swapWeaponAction);
					}
					return actions;
				}
			}
		}
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}
}
