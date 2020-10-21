package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * This is the SpawnZombieActor, which given an integer input, it will spawn that amount
 * of zombies randomly on the map.
 * 
 * @author alanzhang
 *
 */
public class SpawnZombieAction extends Action{
	
	private int numberSpawned;
	
	protected Random rand = new Random();
	
	/**
	 * This is the constructor for the class. It takes a number as parameter which keeps track
	 * of how many zombies are to be spawned
	 * @param num 	number of zombies to be spawned
	 */
	public SpawnZombieAction(int num) {
		this.numberSpawned = num;
	}
	
	/**
	 * This function randonly creates an x coordinate and y coordinate. Then it checks if there
	 * is already an actor at this location. If not a zombie is spawned there and the number to
	 * be spawned is decremented. This is looped until all zombies are spawned.
	 * 
	 * @param actor	The actor which will perform the Spawn Zombie action
	 * @param map 	The GameMap which the actor is on, and the action will be performed on
	 * 
	 * @return A string which is made from the menuDescription function with actor as input.
	 */
	public String execute(Actor actor, GameMap map) {
		while (this.numberSpawned > 0) {
			int x = rand.nextInt(map.getXRange().max());
			int y = rand.nextInt(map.getYRange().max());
			Location location = new Location(map,x, y);
			if (location.getActor() == null) {
				map.at(x, y).addActor(new Zombie("Mambo Marie Spawn"));
				this.numberSpawned--;
			}
		}
		return this.menuDescription(actor);
	}

	/**
	 * Creates a string that describes the  SpawnZombieAction that is conducted.
	 * 
	 * @param actor	The actor which will peform this HarvestAction
	 * 
	 * @return A string which describes the action that is performed which reads: actor name has harvested a crop.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " has spawned zombies!";
	}

}
