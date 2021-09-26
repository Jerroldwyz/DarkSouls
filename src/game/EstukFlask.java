package game;

import edu.monash.fit2099.engine.*;


public class EstusFlask extends Item {
    private final int maxCharge = 3;
    private int charge;
    protected  Player player;


    public EstusFlask(Player player) {
        super("EstusFlask", 'e', true, Player player);
        this.player = player;
        this.charge = maxCharge;
        this.allowableAction.add(new DrinkItemAction(this));

    }

    @Override
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
    public String toString() {return "EstusFlask's charges: " + this.charge + "/" + this.maxCharge; }

}