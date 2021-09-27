package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class BonFire extends Ground{

    private RestAction restAction;
    protected Actions actions = new Actions();

    public BonFire(){super('B');}

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        actions.add(new RestAction(this));
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor){
        return true;
    }
}
