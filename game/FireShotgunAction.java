package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

/**
 * @author kevinyu
 * 
 * This action implements the shotgun shooting feature
 *
 */
public class FireShotgunAction extends Action{
	protected Display display = new Display();
	protected Random rand = new Random();
	/**
	 *The bread and butter of the class, execute shoots the shotgun in a certain direction
	 *@param actor The actor performing the action
	 * @param map The map that actor is currently on
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		//These strings are used for display
		List<String> crossDirectionsStrings = new ArrayList<String>(
				Arrays.asList("North","East","South","West"));
		List<String> diagonalDirectionsStrings = new ArrayList<String>(
				Arrays.asList("North-East","South-East","South-West","North-West"));
		
		//These numbers are used to generate the vectors e.g [1,0] meaning 1 to the right, these are used as directions for shooting
		List<Integer> crossDirections = new ArrayList<Integer>(
				Arrays.asList(0,1,0,-1,0));
		List<Integer> diagonalDirections = new ArrayList<Integer>(
				Arrays.asList(-1,1,1,-1,-1));
		
		//These characters are used for character input, using the right side of the keyboard, they map to directions
		List<Character> crossLabels = new ArrayList<Character>(Arrays.asList('w','d','x','a'));
		List<Character> diagonalLabels = new ArrayList<Character>(Arrays.asList('e','c','z','q'));
		
		
		//These hashmaps map a character to a vector direction. For example, "d" would be to the right, which maps to [1,0]
		HashMap<Character, List<Integer>> cross = new HashMap<Character, List<Integer>>();
		HashMap<Character, List<Integer>> diagonal = new HashMap<Character, List<Integer>>();
		
		
		//Generating vectors 
		for (int i = 0; i < crossDirections.size() -1; i++) {
			List<Integer> newDirection = new ArrayList<Integer>(Arrays.asList(crossDirections.get(i),crossDirections.get(i+1)));
			cross.put(crossLabels.get(i), newDirection);
			display.println(crossLabels.get(i) + ": Shoot " + crossDirectionsStrings.get(i));
		}		
		
		for (int i = 0; i < diagonalDirections.size() -1; i++) {
			List<Integer> newDirection = new ArrayList<Integer>(Arrays.asList(diagonalDirections.get(i+1),diagonalDirections.get(i)));
			diagonal.put(diagonalLabels.get(i), newDirection);
			display.println(diagonalLabels.get(i) + ": Shoot " + diagonalDirectionsStrings.get(i));
		}		
		
		
		//Taking the actor's ammunition
		removeAmmunition(actor);

		
		//Reading user input
		// TODO Auto-generated method stub
		char key;
		do {
			key = display.readChar();
		} while (!cross.containsKey(key) && !diagonal.containsKey(key));
		
		//Generating the list of actors 
		
		//If the direction is up down left or right
		if (cross.containsKey(key)) {
			Location actorLocation = map.locationOf(actor);
			int actorX = actorLocation.x();
			int actorY = actorLocation.y();
			NumberRange rangeX = map.getXRange();
			NumberRange rangeY = map.getYRange();
			
			List<Location> targetLocations = new ArrayList<Location>(); //This stores the locations of possible targets
			List<Integer> vector = cross.get(key); //Getting the vector
			
			
			
			//Going north or south
			
			//The subsequent code just generates locations using the vectors 
			if (vector.get(0) == 0) {
				//Goes 
				for (int i = 1; i<=3; i++) {
					System.out.println("hi");
					//This points i in the direction of the unit vector
					int k = - i * vector.get(1);
					for (int j = -i; j<= i; j++ ) {
						int x = actorX + j;
						int y =	actorY + k;
						if (rangeX.contains(x) && rangeY.contains(y)) { //checking if the locations are out of bounds
						
						Location newLocation = map.at(x,y);
						
						targetLocations.add(newLocation);
						
						}
					}
				}
			}
			else {
				for (int i = 1; i<=3; i++) {
					//This points i in the direction of the unit vector
					int k = i * vector.get(0);
					for (int j = -i; j<= i; j++ ) {
						int x = actorX + k;
						int y = actorY + j;
						if (rangeX.contains(x) && rangeY.contains(y)) {
						Location newLocation = map.at(x , y);
						
						targetLocations.add(newLocation);
						}
						
					}
				}
			}
			
			
			shoot(actor,targetLocations,map); //Shoot the actors in the locations on the map
			String directionString = crossDirectionsStrings.get(crossLabels.indexOf(key));
			
			return "Player fired shotgun " + directionString;
		}
				
		
		//Diagonal is pretty much the same as the vertical one above, just different calculation
		if (diagonal.containsKey(key)) {
			Location actorLocation = map.locationOf(actor);
			
			//Actor's co-ordinates
			int actorX = actorLocation.x();
			int actorY = actorLocation.y();
			NumberRange rangeX = map.getXRange();
			NumberRange rangeY = map.getYRange();
			List<Location> targetLocations = new ArrayList<Location>(); //Locations of possible targets
			List<Integer> vector = diagonal.get(key); 
			
			for (int i = 0; i<= 3; i++) {
				int x = vector.get(0)*i + actorX;
				int y = vector.get(1)*i + actorY;
				if (i != 0 && rangeX.contains(x) &&rangeY.contains(y)) {
					Location newLocation = map.at(x, y);
					
					if (newLocation.containsAnActor()) {
						targetLocations.add(newLocation);
						
					}
				}
				for (int j = 3 - i ;j>0;j--) {
					int x1 = x;
					int y1 = y + j*vector.get(1);
					int x2 = x + j*vector.get(0);
					int y2 = y;
					
					if (rangeX.contains(x1) && rangeY.contains(y1)) {
						Location newLocationX = map.at(x1, y1);
						targetLocations.add(newLocationX);
					}
					if (rangeX.contains(x2) && rangeY.contains(y2)) {
						Location newLocationY = map.at(x2, y2);
						targetLocations.add(newLocationY);
					}
					
					
				}
				
				
			}
			
			shoot(actor,targetLocations,map);
			String directionString = diagonalDirectionsStrings.get(diagonalLabels.indexOf(key));
			
			return "Player fired shotgun " + directionString;
			
			
			
			
		}
		return null;
		

		
		
		
		
	}

	/**
	 *Menu description
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Fire shotgun";
	}
	private void removeAmmunition(Actor actor) {
		List<Item> inventory = actor.getInventory();
		Item ammunition = null;
		for (Item item: inventory) {
			if (item instanceof ShotgunAmmunition) {
				ammunition = item;	
			}
		}
		actor.removeItemFromInventory(ammunition);
		
		
	}
	/**
	 * 
	 * This method checks the locations in the locations variable, and shoots the actors if there are any there
	 * 
	 * @param actor The actor performing the action
	 * @param locations The list of locations where the shotgun has sprayed
	 * @param map The map that actor is currently on
	 */
	private void shoot(Actor actor,List<Location> locations, GameMap map) {
	
		GunItem shotgun = new Shotgun();
		
		for (Location location: locations) { //Checking each location for an actor
			
			
			if (location.containsAnActor()) {
			
				if (rand.nextInt(100)<75) { //75 percent chance of hitting each target
				
					
			Actor target = map.getActorAt(location); 
			int damage = shotgun.getDamageOnShot();
			
			String result = actor + " " + shotgun.verb() + " " + target + " for " + damage + " damage.";
			System.out.println(result);
			target.hurt(damage);
			
	
			
			
			//If they get killed, they drop all the items they had in the inventory 
			if (!target.isConscious()) {
				if (!(target instanceof Zombie)) {
					
				Item corpse = new CorpseItem(target.toString());
				map.locationOf(target).addItem(corpse);
				}
				else {
					Item corpse = new PortableItem(target.toString() + "'s corpse",'%');
					map.locationOf(target).addItem(corpse);
				}
				Actions dropActions = new Actions();
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction());
				for (Action drop : dropActions)		
					drop.execute(target, map);
				map.removeActor(target);	
				result += System.lineSeparator() + target + " is killed.";
			}
		}
		}
	}
		
	}
	
	

}







/*
 * .................................
 * .................................
 * ..............3333...............
 * ..............3333...............
 * ..............3333...............
 * ..............@333...............
 * .................................
 * .................................
 * .................................
 * .................................
 * .................................
 * .................................
 * .................................
 * 
 * 
 * 
 * .................................
 * .................................
 * ...........******E...............
 * ............****EE...............
 * .............**EEE...............
 * ..............@EEE...............
 * ...............EEE...............
 * ................EE...............
 * .................E...............
 * .................................
 * .................................
 * .................................
 * .................................
 * 
 *  

 * 
 * 
 * 
 */
