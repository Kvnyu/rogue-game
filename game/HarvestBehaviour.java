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
 * Harvest behaviour lets all actor who possess this trait to perform the harvest action on adjacent 
 * locations which are instances of ripe crop
 * 
 * @author alanzhang
 *
 */
public class HarvestBehaviour implements Behaviour{
	
	/**
	 * The getAction function will return a HarvestAction if it detects that an adjacent location
	 * to the actor is an instance of a ripe crop. This action will be performed on the actors 
	 * playTurn() method. Otherwise it will just return null.
	 * 
	 * @param actor	The actor who displays this behaviour
	 * @param map	The GameMap which the Actor is in
	 * 
	 * @return HarvestAction with the location of the ripe crop if there is one, otherwise null.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround() instanceof Crop) {
            	Ground ground = destination.getGround();
            	if (((Crop)ground).readyToHarvest()) {
            		return new HarvestAction(destination);
            	}
			}
		}
		return null;
	}
}


