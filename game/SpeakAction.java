package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author kevinyu
 * SpeakAction is used when a zombie makes their signature sound
 */
public class SpeakAction extends Action{
	
	private String line;
	
	public SpeakAction(Zombie actor) {
		this.line = actor.getLine();
	}
	
	@Override
	/**
	 * This is the execution of the SpeakAction which is a function from the Action class that is 
	 * overriden. This will make the zombie say the line in the menuDescription
	 * 
	 * @param actor	The actor which will perform the speak action
	 * @param map 	The GameMap which the actor is on, and the action will be performed on
	 * 
	 * @return A string which is made from the menuDescription function with actor as input.
	 */
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}
	/**
	 * Creates a string that describes the speak action that is conducted.
	 * 
	 * @param actor	The actor which will peform this speak action
	 * 
	 * @return A string which describes the action that is performed which reads: actor name says \(their line)
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " says " + line;
	}

}
