package game;

import edu.monash.fit2099.engine.Item;
/**
 * Class representing items that can be used as Food
 * @author alanzhang
 *
 */
public abstract class FoodItem extends Item{

	private int health;
	
	/**
	 * Constructor for all FoodItems
	 * 
	 * @param name	name of the food
	 * @param displayChar	character to use for display when item is on the ground
	 * @param health	integer representing the healing properties of this foodItem
	 */
	public FoodItem(String name, char displayChar, int health) {
		super(name, displayChar, true);
		this.health = health;
	}
	
	/**
	 * Accessor for the health healed when consuming this foodIten
	 *
	 * @return the health
	 */
	public int health() {
		return health;
	}
}
