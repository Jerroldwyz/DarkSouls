package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import game.enums.Status;

import java.util.List;

public class GreatMachete extends MeleeWeapon{
    private Actor actor;
    Actions actions = new Actions();
    BurningGroundAction burningGroundAction;

    public GreatMachete(Actor actor) {
        super("GreatMachete", 'M', 95, "Slash", 60);
        this.actor = actor;
    }

    @Override
    public List<Action> getAllowableActions() {
        if (burningGroundAction == null) {
            if (actor.hasCapability(Status.ENRAGED)) {
                this.burningGroundAction = new BurningGroundAction(this);
                actions.add(burningGroundAction);

            }
        }return actions.getUnmodifiableActionList();
    }

    public void setHitRate(int hitRate){
        this.hitRate = hitRate;
    }


}
