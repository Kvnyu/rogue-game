package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * CorpseItem is a portable item that is dropped when a human actor dies.
 * @author alanzhang
 *
 */
public class CorpseItem extends Item {
	private int age = 0;
	
	/**
	 * Constructor for CorpseItem
	 * 
	 * @param name will be stored as the name of the item
	 */
	public CorpseItem(String name) {
		super(name, '%', true);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This is the first tick function which keeps track of the age of the corpse when it is an item
	 * on the ground. Once it is over 5 turns old, if there are available adjacent location, a zombie
	 * will spawn there and the item will be removed.
	 * 
	 * @param location	The location of the CorpseItem
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age ++;
		if (age >= 5) {
			
			Zombie zombie = new Zombie("Aaargh");
			for (Exit exit : location.getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(zombie)) {
					location.removeItem(this);
					destination.addActor(zombie);
					break;
				}
			
			
		}
		}
		
	}
	
	/**
	 * This is the second tick function which keeps track of the age of the corpse when it is an item
	 * an an actors inventory. Once it is over 5 turns old, if there are available  at an adjacent 
	 * location to the actor, a zombie will spawn there and the item will be removed from the actors
	 * inventory.
	 * 
	 * @param location	The location of the CorpseItem
	 * @param actor 	The actor which currently is holding this corpse item.
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		super.tick(currentLocation);

		age ++;
		if (age >= 5) {
			
			Zombie zombie = new Zombie("Aaargh");
		
			for (Exit exit : currentLocation.getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(zombie)) {
					actor.removeItemFromInventory(this);;
					destination.addActor(zombie);
					break;
				}
			}
	
		}
	}
}
