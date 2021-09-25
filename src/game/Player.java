package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul {

	private final Menu menu = new Menu();
	private int soulCount = 0;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
	}
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(100, "punches");
	}

	public int getSoulCount() {
		return soulCount;
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this, direction));
		}
		return actions;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		display.println(Integer.toString(this.getSoulCount()));
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(soulCount);
	}

	@Override
	public boolean addSouls(int souls) {
		boolean success = false;
		if(souls != 0){
			soulCount += souls;
			success = true;
		}
		return success;
	}

}
