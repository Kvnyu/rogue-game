package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * SpeakBehaviour implements the behaviour that a zombie has a 10% chance to say their zombie utterance in their turn
 * @author alanzhang
 *
 */
public class SpeakBehaviour implements Behaviour {

		
	private Random rand = new Random();
	
	/**
	 * The getAction runs a 10% probability, if it succeeds, the method will return the speakAction
	 * 
	 * @param actor	The actor who displays this behaviour
	 * @param map	The GameMap which the Actor is in
	 * 
	 * @return returns the speakAction if the 10% probability returns true, otherwise returns null 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (rand.nextInt(10)==1) {
			SpeakAction line = new SpeakAction(((Zombie)actor));
			return line;
		}
		return null;
	}
	
}
