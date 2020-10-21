package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * @author kevinyu
 * Special action for crafting items 
 * An instance of CraftAction has a particular recipe attached to it
 * It's execute method will craft this item 
 * 
 */
public class CraftAction extends Action{
	Recipe recipe;
	CraftAction(Recipe recipe){
		this.recipe = recipe;
	}
	
	/**
	 *Removes the required items from the player's inventory and adds the item that is to 
	 *be crafted. It gets the items information from the recipe 
	 *
	 *@param actor the actor that is crafting
	 *@param map the map that the actor is on 
	 *
	 */
	public String execute (Actor actor, GameMap map) {
	List<Item> inventory = actor.getInventory();
	List<Item> itemsRequired = this.recipe.getItemsRequired();
		for (Item item: itemsRequired) {

			boolean notFound = true;
			int i = 0;
			while ( notFound ){
				if (inventory.get(i).getClass().equals(item.getClass())){
				
					actor.removeItemFromInventory(inventory.get(i));
					notFound = false;
					break;
				}
				i++;
			}	
		}
		Item returnItem = recipe.getItem();
		actor.addItemToInventory(returnItem);			
	
		String item = this.recipe.toString();
		return actor + " crafted " + item ;
	}
	



	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts " + recipe.toString();
	}
	
	
	/**
	 * This method crafts an item for an actor according to the particular recipe
	 * It removes all the required items from the player's inventory and adds the Item that the recipe crafts to the inventory
	 * 
	 * 
	 * @param actor The actor that is performing the craft
	 */

	
	

}
