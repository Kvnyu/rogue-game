package game;

import edu.monash.fit2099.engine.Item;

/**
 * @author kevinyu
 * Ammunition abstract class implements the ammunition feature 
 */
public abstract class Ammunition extends Item{

	/**
	 * Constructor for ammunition class
	 * 
	 * 
	 * @param name The name of the ammunition
	 * @param string The display character of the ammunition
	 * @param portable Whether it is portable or not
	 */
	public Ammunition(String name, char string, boolean portable) {
		super(name, string, portable);
		// TODO Auto-generated constructor stub
	}
	

}
