package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Random;

public class Mimic extends Enemy implements Soul {

    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private FollowBehaviour followBehaviour;
    private Location initLocation;


    public Mimic(String name, GameMap gameMap,int x, int y) {
        super(name, 'M', 100);
        behaviours.add(new WanderBehaviour());
        this.setSoulCount(200);
        this.initLocation = new Location(gameMap,x,y);
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (followBehaviour == null) {
                this.followBehaviour = new FollowBehaviour(otherActor);
                behaviours.add(this.followBehaviour);
            }
            if(!otherActor.hasCapability(Status.DISARMED) && otherActor.getWeapon().getClass() != DarkmoonBow.class) {
                actions.add(new AttackAction(this, direction));
            }
        }
        return actions;
    }
    public Location getInitLocation() {
        return initLocation;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours

        if (!actions.getUnmodifiableActionList().isEmpty()) {
            for (Action action : actions.getUnmodifiableActionList()) {
                if (action.getClass() == AttackAction.class) {
                    display.println("Mimic" + " [" + this.hitPoints + "/" + this.maxHitPoints + "]");
                    return action;
                }
            }
        }
        for (Behaviour Behaviour : behaviours) {
            if (behaviours.contains(followBehaviour)) {
                Action action = followBehaviour.getAction(this, map);
                display.println("Mimic" + " [" + this.hitPoints + "/" + this.maxHitPoints + "]");
                if(action != null){
                    return action;
                }
            }
        }

        return new DoNothingAction();
    }
}
