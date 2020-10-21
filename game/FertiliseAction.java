package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * Fertilise action will add 10 to the age of the unripe crop at the inputted location.
 * @author alanzhang
 *
 */
public class FertiliseAction extends Action{
	
	private Location location;
	
	/**
	 * This is the constructor for the FertiliseAction class which takes a location as an input which
	 * indicates the location on the map where an unripe crop is to be fertilised.
	 * 
	 * @param location	A location which is an unripe crop which is to be fertilised.
	 */
	public FertiliseAction(Location location) {
		this.location = location;
	}
	
	/**
	 * This is the exection of the FertiliseAction which will conduct the fertilise method on the
	 * location of this FeriliseAction, which should be an unripe crop.
	 * 
	 * @return A string which is made from the menuDescription function with actor as input.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Ground ground = this.location.getGround();
		((Crop)ground).fertilise();
		return this.menuDescription(actor);
	}
	
	/**
	 * Creates a string that describes the fertilise action that is conducted.
	 * 
	 * @param actor	The actor which will peform this fertilise action
	 * 
	 * @return A string which describes the action that is performed which reads: actor name has fertilised a crop.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " has fertilised a crop.";
	}

}
