package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;

public class CindersOfTheLord extends PortableItem {

    protected LordOfCinder lordOfCinder;

    public CindersOfTheLord(LordOfCinder lordOfCinder) {
        super("Cinders Of The Lord",'L');
        this.lordOfCinder = lordOfCinder;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return super.getDropAction(actor);
    }
}
