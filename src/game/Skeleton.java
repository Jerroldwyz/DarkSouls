package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Random;

/**
 * A monster that holds either a broadsword or giant axe
 */
public class Skeleton extends Enemy implements Soul {
    /**
     * An array list to store the behaviours of skeleton such as wander and follow behaviour
     */
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Declaring a follow behaviour object
     */
    private FollowBehaviour followBehaviour;

    /**
     * Boolean value to determine if its the skeleton's first death
     */
    private boolean skeletonFirstDeath = true;

    /**
     * Gets the boolean value of skeleton first death
     * @return skeletonFirstDeath
     */
    public boolean isSkeletonFirstDeath() {
        return skeletonFirstDeath;
    }

    /**
     * Setter for the skeleton first death
     * @param isFirst boolean value to set
     */
    public void setSkeletonFirstDeath(boolean isFirst) {
        skeletonFirstDeath = isFirst;
    }


    /**
     * Constructor.
     * All skeletons are represented by an 's' and have 100 hit points.
     *
     * @param name the name of this Skeleton
     */
    public Skeleton(String name) {
        super(name, 's', 100);
        behaviours.add(new WanderBehaviour());
        this.setSoulCount(250);
        Random random = new Random();
        int randomInt = random.nextInt(100);
        if(randomInt < 50){
//            this.addItemToInventory(BroadSword);
        }else{
//            this.addItemToInventory(GiantAxe);
        }

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

    /**
     * Figure out what to do next.
     * FIXME: A Skeleton wanders around at random and it cannot attack anyone.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        if(map.locationOf(this).getGround().getClass() == Valley.class ){
            map.removeActor(this);
            return new DoNothingAction();
        }

        if (!actions.getUnmodifiableActionList().isEmpty()) {
            for (Action action : actions.getUnmodifiableActionList()) {
                if (action.getClass() == AttackAction.class) {
                    display.println("Skeleton" + " [" + this.hitPoints + "/" + this.maxHitPoints + "] using " + this.getWeapon());
                    return action;
                }
            }
        }

        for (Behaviour Behaviour : behaviours) {
            if (behaviours.contains(followBehaviour)) {
                Action action = followBehaviour.getAction(this, map);
                display.println("Skeleton" + " [" + this.hitPoints + "/" + this.maxHitPoints + "] using " + this.getWeapon());
                return action;
            }
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
