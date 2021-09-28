package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

public class BurningGround extends Ground {
    private Actor yhormTheGiant;
    private int burningTurn = 0;

    public BurningGround(Actor yhormTheGiant){
        super('V');
        this.yhormTheGiant = yhormTheGiant;
    }

    @Override
    public void tick(Location location) {

        if(burningTurn == 3){
            location.setGround(new Dirt());
            burningTurn = 0;
        }
        if(location.map().isAnActorAt(location) && location.map().getActorAt(location).getClass() != YhormTheGiant.class){
            location.map().getActorAt(location).hurt(25);
            Actor target = location.map().getActorAt(location);
            System.out.println(target.getClass().getSimpleName() + " is burnt for 25 damage.");
            if(!location.map().getActorAt(location).isConscious()){
                target.addCapability(Status.DEAD);
                TokenOfSoul tokenOfSoul = new TokenOfSoul("tokenOfSoul", target);
                target.asSoul().transferSouls(tokenOfSoul.asSoul());
                location.map().locationOf(target).addItem(tokenOfSoul);
                location.map().moveActor(target, location.map().at(38,12));
                this.yhormTheGiant.removeCapability(Status.ENRAGED);
                location.map().moveActor(yhormTheGiant, location.map().at(6,25));
                location.setGround(new Dirt());
                Player player = (Player) target;
                player.getEstusFlask().setCharge(3);
                target.heal(1000);
                yhormTheGiant.heal(1000);
                System.out.println("YOU ARE BURNT TO DEATH");
                System.out.println("YOU WILL BE SENT BACK TO BONFIRE");

            }
        }
        burningTurn += 1;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

}
