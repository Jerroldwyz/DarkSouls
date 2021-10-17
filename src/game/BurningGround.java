package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

/**
 * A class where the ground is burning.
 *
 * @author Ng Kai Yi
 * @author Jerrold Wong Youn Zhuet
 * @version 2
 * @see Ground
 */

public class BurningGround extends Ground {
    /**
     * YhormTheGiant's attributes
     */
    private Actor yhormTheGiant;

    /**
     * The time for the ground to burn.
     */
    private int burningTurn = 0;

    /**
     * Constructor.
     *
     * @param yhormTheGiant YhormeTheGiant's attributes.
     */
    public BurningGround(Actor yhormTheGiant){
        super('V');
        this.yhormTheGiant = yhormTheGiant;
    }

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
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
                TokenOfSoul tokenOfSoul = new TokenOfSoul("tokenOfSoul", target, 0);
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

    /**
     * Player can enter the burning ground.
     * @param actor The actor performing the action.
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

}
