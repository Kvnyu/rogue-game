package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Harvest action will turn the location of the inputted ripe crop into dirt. Then it will intialise
 * a wheat item at the location where the ripe crop was.
 * 
 * @author alanzhang
 *
 */
public class HarvestAction extends Action{
	
	private Location location;
	
	/**
	 * This is the constructor for the SowAction class which takes in a location as input which 
	 * indicates the location in the map where there is a ripe crop to be harvested.
	 * 
	 * @param location	A location object on which the execution of the HarvestAction will be performed
	 */
	public HarvestAction(Location location) {
		this.location = location;
	}
	
	/**
	 * This is the execution of the HarvestAction which is a function from the Action class that is 
	 * overriden. This will set the location of this HarvestAction object to dirt, and then initialise
	 * a wheat item at this location.
	 * 
	 * @param actor	The actor which will perform the sow action
	 * @param map 	The GameMap which the actor is on, and the action will be performed on
	 * 
	 * @return A string which is made from the menuDescription function with actor as input.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		this.location.setGround(new Dirt());
		this.location.addItem(new Wheat());
		return this.menuDescription(actor);
	}
	
	/**
	 * Creates a string that describes the  HarvestAction that is conducted.
	 * 
	 * @param actor	The actor which will peform this HarvestAction
	 * 
	 * @return A string which describes the action that is performed which reads: actor name has harvested a crop.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " has harvested a crop";
	}

}
