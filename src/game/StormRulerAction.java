package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

public class StormRulerAction extends Action {
    private Enum ability;
    private StormRuler stormRuler;

    public StormRulerAction(StormRuler stormRuler,Enum ability){
        this.ability = ability;
        this.stormRuler = stormRuler;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        String result = "";

        if(this.ability == Abilities.CHARGE ){
            actor.addCapability(Status.DISARMED);
            stormRuler.setActions(new Actions());
            result = "Player is charging";
        }else if(this.ability == Abilities.WINDSLASH){
            int damage = stormRuler.damage() * 2;
            stormRuler.setHitRate(100);
            for(Exit exit : here.getExits()){
                Location destination = exit.getDestination();
                if(map.isAnActorAt(destination)) {
                    if (map.getActorAt(destination).getClass() == YhormTheGiant.class) {
                        YhormTheGiant yhormTheGiant = (YhormTheGiant) map.getActorAt(destination);
                        yhormTheGiant.hurt(damage);
                        result = "Player slashes " + yhormTheGiant + " for " + damage;
                        if (!yhormTheGiant.isConscious()) {
                            yhormTheGiant.asSoul().transferSouls(actor.asSoul());
                            map.removeActor(yhormTheGiant);
                            StormRuler.setSkill(Abilities.CHARGE);
                            stormRuler.setActions(new Actions());
                            return "LORD OF CINDER FALLEN";
                        }
                        map.getActorAt(destination).addCapability(Status.STUNNED);
                    }
                }
            }
            StormRuler.setSkill(Abilities.CHARGE);
            stormRuler.setActions(new Actions());
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled (Player) use StormRuler (" + ability + ")";
    }
}
