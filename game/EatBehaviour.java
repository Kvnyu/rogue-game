package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * eat behaviour lets all actors which possess this trait to consume items of food if they are in
 * their inventory.
 * 
 * @author alanzhang
 *
 */
public class EatBehaviour implements Behaviour{
	
	/**
	 * The getAction functin checks if the actor can conduct the EatAction by seeing if firstly, the
	 * inputted actor is damaged, and secondly if they actor has an instance of a FoodItem in their 
	 * inventory.
	 * 
	 * @param actor	The actor who displays this behaviour
	 * @param map	The GameMap which the Actor is in
	 * 
	 * @return Will return EatAction with the first instance of FoodItem in the actors inventory as a parameter, otherwise will return null
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Human human = (Human) actor;
		if (human.damaged()) {
			for (Item item: actor.getInventory()) {
				if (item instanceof FoodItem) {
					return new EatAction(((FoodItem)item));
				}
			}
		}
		return null;
		}
}