package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

import java.util.List;

public class GreatMachete extends MeleeWeapon{
    /**
     * Constructor
     * @param name String for great machete
     */
    public GreatMachete(String name){
        super(name, 'm', 95, "slash", 60);
//        this.addCapability(Ability.);
    }

    /**
     * The amount of damage the Weapon will inflict
     *
     * @return the damage, in hitpoints
     */
    @Override
    public int damage() {
        return damage;
    }

    /**
     * A verb to use when displaying the results of attacking with this Weapon
     *
     * @return String, e.g. "punches", "zaps"
     */
    @Override
    public String verb() {
        return verb;
    }

    /**
     * An integer of how many percent (as dividend/100) the Actor can hit with this weapon.
     * @return the chance to hit.
     */
    @Override
    public int chanceToHit() {
        return hitRate;
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
    public List<Action> getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//            if (followBehaviour == null) {
//                this.followBehaviour = new FollowBehaviour(otherActor);
//                behaviours.add(this.followBehaviour);
//            }
//            actions.add(new EmberFormAction(this, direction));

        }
        return allowableActions.getUnmodifiableActionList();
    }
}
