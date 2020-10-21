package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Sow action will turn the type of ground at the location inputted from dirt into crop.
 * @author alanzhang
 *
 */
public class SowAction extends Action{
	
	private Location location;
	
	/**
	 * This is the constructor for the SowAction class which takes in a location as input which 
	 * indicates which location in the map will be sowed.
	 * 
	 * @param location	A location object on which the execution of the SowAction will be performed
	 */
	public SowAction(Location location) {
		this.location = location;
	}

	
	/**
	 * This is the execution of the SowAction which is a function from the Action class that is 
	 * overriden. This will set the location of this SowAction object to an instance of a Crop.
	 * 
	 * @param actor	The actor which will perform the sow action
	 * @param map 	The GameMap which the actor is on, and the action will be performed on
	 * 
	 * @return A string which is made from the menuDescription function with actor as input.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		this.location.setGround(new Crop());
		return this.menuDescription(actor);
	}

	/**
	 * Creates a string that describes the sow action that is conducted.
	 * 
	 * @param actor	The actor which will peform this sow action
	 * 
	 * @return A string which describes the action that is performed which reads: actor name has sowed a crop.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + "has sowed a crop";
	}

}
