package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import game.enums.Abilities;

import java.util.List;

import game.enums.Abilities;

public class GiantAxe extends MeleeWeapon{

    private int soulPrice = 1000;
    protected Actions actions = new Actions();
    private SpinAttackAction spinAttackAction;
    private Actor target;

    public GiantAxe() {
        super("GiantAxe", 'A', 50, "hits", 80);
        this.addCapability(Abilities.CASTSPIN);
    }

    @Override
    public List<Action> getAllowableActions() {
        if(spinAttackAction == null){
            this.spinAttackAction = new SpinAttackAction(this);
            actions.add(spinAttackAction);
        }return actions.getUnmodifiableActionList();
    }

}
