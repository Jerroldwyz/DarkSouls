package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;

/**
 * Class representing the weapon used by Aldrich, Darkmoon Longbow.
 *
 * @author Jerrold Wong Youn Zhuet
 * @version 1
 * @see edu.monash.fit2099.engine.Weapon
 */
public class DarkmoonBow extends WeaponItem {
    /**
     * to signify that this is a ranged weapon
     */
    private Enum ranged = Abilities.RANGEDWEAPON;
    /**
     * Constructor.
     *
     */
    public DarkmoonBow(Actor actor) {
        super("DarkMoon Longbow", 'D', 70, "shoots", 100);
        actor.addCapability(ranged);
    }
}
