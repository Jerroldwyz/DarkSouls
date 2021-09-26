package game;

import edu.monash.fit2099.engine.*;

import java.util.List;


public class EstusFlask extends Item {
    private final int MAXCHARGE = 3;
    private int charge;
    protected Player player;
    protected Actions actions = new Actions();
    private DrinkItemAction drinkItemAction;



    public EstusFlask(Player player) {
        super("EstusFlask", 'E', true);
        this.player = player;
        this.charge = MAXCHARGE;

    }

    @Override
    public List<Action> getAllowableActions() {
        if(drinkItemAction == null){
            this.drinkItemAction = new DrinkItemAction(this);
            actions.add(drinkItemAction);
        }return actions.getUnmodifiableActionList();
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public boolean useCharges(){
        boolean result = false;
        if (this.charge > 0){
            int heal_hp = 40;
            player.heal(heal_hp);
            this.charge -=1;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {return "EstusFlask's charges: " + this.charge + "/" + this.MAXCHARGE; }

}