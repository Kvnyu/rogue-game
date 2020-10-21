package game;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Sow behaviour lets all actor who possess this trait to have a chance to turn adjacent locations 
 * which are dirt, into crop
 * 
 * @author alanzhang
 *
 */
public class SowBehaviour implements Behaviour{
	
	private Random random = new Random();

	
	/**
	 * SowBehaviour is an objective of every instance of a Farmer Actor. This tells the farmer
	 * if it is possible for it to sow a crop in one of the Farmer's adjacent locations by
	 * returning a SowAction object which can be called in an Actor's playTurn() method.
	 * 
	 * @param actor	The actor who displays this behaviour
	 * @param map	The GameMap which the Actor is in
	 * 
	 * @return A chance to return SowAction performed on one of the adjacent locations to the actor; otherwise null.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround() instanceof Dirt) {
            	if (random.nextInt(100) <= 33) {
            		actions.add(new SowAction(destination));
            	}
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}
	}

}
