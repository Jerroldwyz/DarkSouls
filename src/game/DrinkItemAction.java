package game;

import edu.monash.fit2099.engine.Action;


import edu.monash.fit2099.engine.*;


public class DrinkItemAction extends Action {

    protected EstusFlask estusFlask;

    /**
     * Constructor
     * @param EstusFlask estusFlask to be consumed
     */

    public DrinkItemAction(EstusFlask estusFlask){
        this.estusFlask = estusFlask;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        boolean status = this.estusFlask.useCharges();
        if(status){
            return actor + " consumed " + this.estusFlask.toString();
        }else {
            return actor + " unable to consume " + this.estusFlask.toString() + ",please refill charges.";
        }
    }

    @Override
    public String menuDescription(Actor actor){return actor + " consumes EstusFlask";}
}
}
