package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new FirstWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new MamboMarieGameMap(groundFactory, map );
		
		List<String> townMap = Arrays.asList(
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................",
		".............................................................");
		GameMap town = new MamboMarieGameMap(groundFactory, townMap);
				
		world.addGameMap(gameMap);
		world.addGameMap(town);
		
		town.at(9, 4).addItem(new Shotgun());
		town.at(9, 5).addItem(new ShotgunAmmunition());
		town.at(9, 5).addItem(new ShotgunAmmunition());
		town.at(9, 5).addItem(new ShotgunAmmunition());
		town.at(9, 5).addItem(new ShotgunAmmunition());
		town.at(9, 5).addItem(new ShotgunAmmunition());
		town.at(9, 5).addItem(new ShotgunAmmunition());
		
		
		
		town.at(10, 4).addItem(new Rifle());
		town.at(10, 5).addItem(new RifleAmmunition());
		town.at(10, 5).addItem(new RifleAmmunition());
		town.at(10, 5).addItem(new RifleAmmunition());
		town.at(10, 5).addItem(new RifleAmmunition());
		town.at(10, 5).addItem(new RifleAmmunition());
		town.at(10, 5).addItem(new RifleAmmunition());
		
		
		Vehicle car = new Vehicle("lamborghini", '$', false);
		car.addAction(new MoveActorAction(town.at(7, 2), "to the Wild West!"));
		gameMap.at(1, 1).addItem(car);
		
		
		Player player = new Player("Player", '@', 500);
		world.addPlayer(player, gameMap.at(23, 15));
		
		
		
		// Create recipes and add to recipe list

		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new ZombieMaceRecipe());
		recipes.add(new ZombieClubRecipe());
		Recipes defaultRecipeList = new Recipes(recipes);
		player.addRecipesToInventory(defaultRecipeList);
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		

		//adding a new farmer 
//		gameMap.at(24, 5).addActor(new Farmer("Farmer Joe"));
		
		// place a simple weapon
		// Player is at 
		gameMap.at(74, 20).addItem(new Plank());
		gameMap.at(31, 20).addItem(new Plank());
		
		gameMap.at(50, 19).addItem(new Plank());
		gameMap.at(41, 16).addItem(new ZombieLeg());
		
		
		
		gameMap.at(24, 16).addItem(new Plank());
		gameMap.at(24, 16).addItem(new Plank());
		
		
		gameMap.at(34,  12).addActor(new Zombie("Boo"));
		gameMap.at(34,  13).addActor(new Zombie("Uuuurgh"));
		gameMap.at(34, 15).addActor(new Zombie("Mortalis"));
		gameMap.at(35, 12).addActor(new Zombie("Gaaaah"));
		gameMap.at(35, 13).addActor(new Zombie("Aaargh"));
		
		
		
		
		world.run();
	}
}
