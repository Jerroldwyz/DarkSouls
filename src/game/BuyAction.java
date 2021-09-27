package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class BuyAction extends Action {
    private Item weapon;

    public BuyAction(Item weapon){
        this.weapon = weapon;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        Weapon currentWeapon = actor.getWeapon();
        List<Item> items = actor.getInventory();
        String result = "";

        if(this.weapon.getClass() == Broadsword.class){
            Broadsword broadsword = (Broadsword) weapon;
            if(player.getSoulCount() > broadsword.getSoulPrice()){
                for( Item item : items){
                    if(item.asWeapon() != null){
                        player.removeItemFromInventory(item);
                        player.addItemToInventory(broadsword);
                        player.subtractSouls(broadsword.getSoulPrice());
                        result += player + " has bought " + broadsword;
                        return result;
                    }
                }
            }else{
                result += "Insufficient souls, requires extra " + (500- player.getSoulCount()) + " souls";
                return result;
            }
        }else if(this.weapon.getClass() == GiantAxe.class){
            GiantAxe giantAxe = (GiantAxe) weapon;
            if(player.getSoulCount() > giantAxe.getSoulPrice()){
                for( Item item : items){
                    if(item.asWeapon() != null){
                        player.removeItemFromInventory(item);
                        player.addItemToInventory(giantAxe);
                        player.subtractSouls(giantAxe.getSoulPrice());
                        result += player + " has bought " + giantAxe;
                        return result;
                    }
                }
            }else{
                result += "Insufficient souls, requires extra " + (1000- player.getSoulCount()) + " souls";
                return result;
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + weapon.getClass().getSimpleName();
    }

}
