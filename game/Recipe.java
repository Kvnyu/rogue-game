package game;

import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
/**
 * @author kevinyu
 * Abstract class representing a recipe that can be used to create items 
 * 
 *
 */
abstract class Recipe {
	private Item itemToCraft;
	private List<Item> itemsRequired; 
	
	/**
	 * 
	 * @param itemToCraft the item that is to be crafted
	 * @param itemsRequired the items that are required, in a list
	 */
	Recipe(Item itemToCraft, List<Item> itemsRequired){
		this.itemToCraft = itemToCraft;
		this.itemsRequired = itemsRequired;
	}
	
	
	
	public String toString() {
		
		return this.itemToCraft.toString();
	}
	
	public List<Item> getItemsRequired(){
		return itemsRequired;
		
	}
	
	
	/**
	 * This method is to be implemented by the subclasses. It returns a new object of a recipe's particular item that gets crafted
	 * 
	 * 
	 * @return
	 */
	abstract Item getItem();
}
