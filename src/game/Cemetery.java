package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing the cemetery.
 *
 * @author Ng Kai Yi
 * @author Jerrold Wong Youn Zhuet
 * @version 2
 * @see Ground
 */

public class Cemetery extends Ground {

    /**
     * Constructor.
     *
     */
    public Cemetery() {
        super('C');
    }

    /**
     * Check whether the actor can enter cemetery or not.
     * @param actor The actor performing the action.
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     *?????????????????????????????????????????????????
     * @return false
     */
    @Override
    public boolean blocksThrownObjects() {
        return false;
    }
}
