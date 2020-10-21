package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Eat action will heal the actor that consumes the FoodItem based on the FoodItems healing properties
 * and then remove that item from their inventory
 * @author alanzhang
 *
 */
public class EatAction extends Action{
	
	private FoodItem food;
	
	/**
	 * This is the constructor for EatAction with a FoodItem as input which is the food which is to be
	 * eaten.
	 * 
	 * @param food
	 */
	public EatAction(FoodItem food) {
		this.food = food;
	}
	
	/**
	 * This is the execution of the EatAction where the actor inputted will heal based on the EatActions
	 * food variables healing properties. Then this item will be removed from the actors inventory.
	 * 
	 * @param actor	The actor which will perform the sow action
	 * @param map 	The GameMap which the actor is on, and the action will be performed on
	 * 
	 * @return A string which is made from the menuDescription function with actor as input.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(food.health());
		actor.removeItemFromInventory(this.food);
		return this.menuDescription(actor);
	}
	
	/**
	 * Creates a string that describes the EatAction that is conducted.
	 * 
	 * @param actor	The actor which will peform this EatAction
	 * 
	 * @return A string which describes the action that is performed which reads: actor name consumes the food.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " consumes the " + this.food;
	}
	
	

}
