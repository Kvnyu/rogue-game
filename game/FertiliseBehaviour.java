package game;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Fertilise behaviour lets all actor who possess this trait to fertilise unripe crops that are 
 * adjacent to the actor.
 * 
 * @author alanzhang
 *
 */

public class FertiliseBehaviour implements Behaviour{
	
	private Random random = new Random();
	
	/**
	 * The getAction function will check all the adjacent locations of the actor that is inputted. If
	 * any are instances of unripe crop, the function will return a fertiliseAction with the location 
	 * of the unripe crop as parameter. If there are no unripe crops it will return null.
	 * 
	 * @param actor	The actor who displays this behaviour
	 * @param map	The GameMap which the Actor is in
	 * 
	 * @return If there is an unripe crop in an adjacent location to the actor, it will return a fertilise action on the location, otherwise it will return null.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
	
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround() instanceof Crop) {
            	Ground ground = destination.getGround();
            	if (!((Crop)ground).readyToHarvest()) {
            		actions.add(new FertiliseAction(destination));
            	}
            }
        
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		
	}
		return null;

}
}