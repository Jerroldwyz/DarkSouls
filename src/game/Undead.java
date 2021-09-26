package game;


import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * An undead minion.
 */
public class Undead extends Enemy implements Soul {
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	/**
	 * An array list to store the behaviours of skeleton such as wander and follow behaviour
	 */
	private ArrayList<Behaviour> behaviours = new ArrayList<>();

	/**
	 * Declaring a follow behaviour object
	 */
	private FollowBehaviour followBehaviour;

	/**
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 *
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'U', 50);
		behaviours.add(new WanderBehaviour());
		this.setSoulCount(50);
	}

	/**
	 * A getter to get the weapon of the undead
	 * @return a new intrinsic weapon since ghost does not hold any weapon
	 */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(50, "punches");
	}

	/**
	 * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
	 * You can do something else with this method.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			if (followBehaviour == null) {
				this.followBehaviour = new FollowBehaviour(otherActor);
				behaviours.add(this.followBehaviour);
			}
			actions.add(new AttackAction(this, direction));

		}
		return actions;
	}

	/***
	 *
	 * Figure out what to do next.
	 * Loops through the list of behaviours and action list.
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 *
	 * @param actions   the action which is being played the current turn
	 * @param lastAction not in use
	 * @param map       used to passed as a parameter
	 * @param display   displays the Skeleton's current hitpoints out of the maximum hitpoints along with weapon used
	 * @return the current action
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// loop through all behaviours
		if(map.locationOf(this).getGround().getClass() == Valley.class ){
			map.removeActor(this);
		}
		if (!actions.getUnmodifiableActionList().isEmpty()) {
			for (Action action : actions.getUnmodifiableActionList()) {
				if (action.getClass() == AttackAction.class) {
					display.println("Undead" + " [" + this.hitPoints + "/" + this.maxHitPoints + "] using fist");
					return action;
				}
			}
		}

		for (Behaviour Behaviour : behaviours) {
			if (behaviours.contains(followBehaviour)) {
				Action action = followBehaviour.getAction(this, map);
				display.println("Undead" + " [" + this.hitPoints + "/" + this.maxHitPoints + "] using fist");
				return action;
			}
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
}

