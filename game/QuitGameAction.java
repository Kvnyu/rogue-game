package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author kevinyu
 *
 *	A new action that allow shte player to quit the game
 *
 */
public class QuitGameAction extends Action{

	/**
	 * 
	 * Removes the actor from the game
	 *@param actor The actor performing the action
	 * @param map The map that actor is currently on
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		((Player) actor).setQuit(true);
		map.removeActor(actor);
		
		return "Ending game...";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		
		return "Quit game";
	}
	

}
