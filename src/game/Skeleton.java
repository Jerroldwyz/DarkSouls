package game;

import edu.monash.fit2099.engine.*;
import game.FollowBehaviour;
import game.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

public class Skeleton extends Enemy implements Soul {
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private FollowBehaviour followBehaviour;

    public Skeleton(String name) {
        super(name, 'S', 100);
        behaviours.add(new WanderBehaviour());
        setSoulCount(250);
    }

    /**
     * Figure out what to do next.
     * FIXME: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        if(!actions.getUnmodifiableActionList().isEmpty()) {
            for (Action action : actions.getUnmodifiableActionList()) {
                if (action.getClass() == AttackAction.class) {
                    display.println(this.getClass().getName() + " [" + this.hitPoints + "/" + this.maxHitPoints + "] using fist");
                    return action;
                }
            }
        }

        for(Behaviour Behaviour : behaviours) {
            if (behaviours.contains(followBehaviour)){
                Action action = followBehaviour.getAction(this, map);
                display.println("Undead" + " [" + this.maxHitPoints + "/" + this.hitPoints + "] using fist");
                return action;
            }
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

}
