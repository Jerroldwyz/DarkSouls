package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;

import java.util.List;

public class StormRuler extends MeleeWeapon {
    private static Enum ability;
    private static StormRulerAction stormRulerAction;
    private Actor target;
    private Actions actions = new Actions();
    private static int charge = 0;

    public StormRuler(Enum ability){
        super("StormRuler", '7', 70, "slash",60);
        this.ability = ability;
    }

    @Override
    public List<Action> getAllowableActions() {
        if (stormRulerAction == null) {
            if (this.ability == Abilities.CHARGE && charge == 0) {
                this.stormRulerAction = new StormRulerAction(this, Abilities.CHARGE);
                actions.add(stormRulerAction);
            }
//        } else if (this.ability == Abilities.WINDSLASH) {
//            this.stormRulerAction = new StormRulerAction(this, Abilities.WINDSLASH);
//            actions.add(stormRulerAction);
//            stormRulerAction = null;
        }
        return actions.getUnmodifiableActionList();
    }

    public static int getCharge(){
        return charge;
    }

    public static void setCharge(int newCharge){
        charge = newCharge;
    }

    public static void setSkill(Enum skill){
        ability = skill;
    }

    public void setHitRate(int hitRate){
        this.hitRate = hitRate;
    }

    public void setActions(Actions actions){
        this.actions = actions;
    }

    public static Enum getAbility(){
        return ability;
    }

    public static void setStormRulerAction(){
        stormRulerAction = null;
    }

}
