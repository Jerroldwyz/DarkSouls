package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * A method used in execute created to reduce repetition of code due to it being recurring
	 * @param actor the non player character
	 * @param map in case where actor drops an item
	 */
	public void transferSouls(Actor actor, GameMap map){
		target.asSoul().transferSouls(actor.asSoul());
		Actions dropActions = new Actions();
		// drop all item
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction(actor));
		for (Action drop : dropActions)
			drop.execute(target, map);
		// remove actor
		map.removeActor(target);

	}

	/***
	 * This method is used to execute the attack instruction when there is an actor within the adjacent spaces of other
	 * actors.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return result, a descriptive message of the damage done by the actor to the target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		int damage;
		Weapon weapon = actor.getWeapon();
		if(weapon.getClass() == Broadsword.class || weapon.getClass() == StormRuler.class){
			int dealDoubleDmg = rand.nextInt(100);
			if(dealDoubleDmg >= 80){
				damage = weapon.damage() * 2;
			}else{
				damage = weapon.damage();
			}
		}
		else{
			damage = weapon.damage();
		}

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			//special condition check for skeleton to give him a 50% chance to revive
			if (target.getClass() == Skeleton.class) {
				Skeleton skeleton = (Skeleton) target;
				if (skeleton.isSkeletonFirstDeath()) {
					int randomInt = rand.nextInt(100);
					if (randomInt >= 50) {
						skeleton.heal(1000);
						skeleton.setSkeletonFirstDeath(false);
						result += System.lineSeparator() + target + " is revived.";
					}else{
						transferSouls(actor,map);
						result += System.lineSeparator() + target + " is killed.";
					}
				} else { //if the target is not a skeleton then it will just proceed as normal when the target is not conscious
					transferSouls(actor,map);
					result += System.lineSeparator() + target + " is killed.";
				}
			}else if(target.getClass() == YhormTheGiant.class){
				transferSouls(actor,map);
				result += System.lineSeparator() + "LORD OF CINDER FALLEN";
			}else if(target.getClass() == Undead.class){
				transferSouls(actor,map);
				result += System.lineSeparator() + target + " is killed.";
			}else if(target.getClass() == Player.class){
				target.addCapability(Status.DEAD);
				TokenOfSoul tokenOfSoul = new TokenOfSoul("tokenOfSoul", target);
				target.asSoul().transferSouls(tokenOfSoul.asSoul());
				map.locationOf(target).addItem(tokenOfSoul);
				map.moveActor(target, map.at(38,12));
				if(actor.getClass() == Skeleton.class){
					Skeleton skeleton = (Skeleton) actor;
					map.moveActor(skeleton, map.at(skeleton.getInitLocation().x(),skeleton.getInitLocation().y()));
				}
				else if(actor.getClass() == Undead.class){
					Undead undead = (Undead) actor;
					map.moveActor(undead, map.at(undead.getInitLocation().x(),undead.getInitLocation().y()));
				}
				target.heal(1000);
				actor.heal(1000);
				result += "\n YOU ARE DEAD AND HAS BEEN SENT BACK TO BONFIRE";
			}


		}
		return result;
	}


	/***
	 * Descriptive message to describe the actor hitting the target in which direction
	 * @param actor The actor performing the action.
	 * @return a string of descriptive message
	 */
		@Override
		public String menuDescription (Actor actor){
			return actor + " attacks " + target + " at " + direction;
		}
	}

