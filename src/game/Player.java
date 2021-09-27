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
	private int soulCount = 1000;
	private  Location prevLocation;
	private EstusFlask estusFlask = new EstusFlask(this);

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
		this.addCapability(Abilities.PICKUPTOS);
		this.addItemToInventory(estusFlask);
		this.addItemToInventory(new GiantAxe());
	}

	public void setSoulCount(int soulCount) {
		this.soulCount = soulCount;
	}

	/**
	 * Gets the soul count of the player
	 * @return soulcount
	 */
	public int getSoulCount() {
		return soulCount;
	}

	public int getMaxHitPoints(){
		return maxHitPoints;
	}

	/**
	 * Gets the allowable actions that the other actor can perform unto player
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return returns an attack action if there is hostile enemy nearby
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER_ONLY)){
			actions.add(new AttackAction(this, direction));
		}
		return actions;
	}

	/**
	 * Plays the actor's current turn
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the console menu
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions

		if(map.locationOf(this).getGround().getClass() == Valley.class ){
			hurt(1000);
			TokenOfSoul tokenOfSoul = new TokenOfSoul("tokenOfSoul", this);
			this.asSoul().transferSouls(tokenOfSoul.asSoul());
			display.println(Integer.toString(tokenOfSoul.getSoulCount()));
			prevLocation.addItem(tokenOfSoul);
		}

		if(!this.isConscious()){
			map.moveActor(this, map.at(38,12));
			this.heal(1000);
			display.println("YOU ARE DEAD AND YOU ARE SENT BACK TO BONFIRE");
			display.println("Unkindled " + "(" + this.hitPoints + "/" + this.maxHitPoints + "), " + "holding " + this.getWeapon() + ", " + this.getSoulCount() + " souls.");
			return new DoNothingAction();
		}
		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();}

		prevLocation = map.locationOf(this);
		display.println(Integer.toString(prevLocation.x()));
		display.println(Integer.toString(prevLocation.y()));

		// return/print the console menu
		display.println("Unkindled " + "(" + this.hitPoints + "/" + this.maxHitPoints + "), " + "holding " + this.getWeapon() + ", " + this.getSoulCount() + " souls.");
		display.println("Estus Flask Charges: " + estusFlask.getCharge() + "/3");
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Transfers the soul from a soul object to another
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(soulCount);
		this.setSoulCount(0);
	}

	/**
	 * Adds the souls of the player
	 * @param souls number of souls to be incremented.
	 * @return a boolean value to indicate if addition is successful
	 */
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
