package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

/**
 * This is an item that can be given allowable actions. In this case it will be used
 * as a vehicle which gives a move actor action to another map.
 * @author alanzhang
 *
 */
public class Vehicle extends Item{
	/**
	 * This is the constructor.
	 * 
	 * @param name	A string which represents the name of the vehicle
	 * @param displayChar	A character which will be how the item is displayed on the map
	 * @param portable	Boolean on whether or not this item is portable
	 */
	public Vehicle(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
	}
	
	/**
	 * This adds an action to the items allowable actions.
	 * @param action 	The item to be added to allowable actions.
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

}
