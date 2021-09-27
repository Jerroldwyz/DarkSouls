package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley());

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					".........................................................................+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#____+__#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++......................+..........+...............++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new YhormTheGiant("Yhorm the Giant"));

			// Place a Hollow in the the map
			// FIXME: the Undead should be generated from the Cemetery
			gameMap.at(37, 10).addActor(new Undead("Undead",gameMap, 37, 10));
//			gameMap.at(38, 11).addActor(new Skeleton("Skeleton",gameMap,38, 11));
//			gameMap.at(38, 13).addActor(new Skeleton("Skeleton",gameMap,38, 13));
//			gameMap.at(30, 2).addActor(new Skeleton("Skeleton",gameMap,30, 2));
//			gameMap.at(30, 3).addActor(new Skeleton("Skeleton",gameMap, 30, 3));
//			gameMap.at(39, 19).addActor(new Skeleton("Skeleton"));
			world.run();

	}
}
