package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Farmer is a class that extends the human class as it is a type of human. On top of the human traits
 * it will also have other behaviours that allows it to create crops and harvest food.
 * 
 * @author alanzhang
 *
 */
public class Farmer extends Human{
	/**
	 * These are the behaviours which every instance of a Farmer will possess. This allows the farmer
	 * to perform its farming actions in the desired priority, which is harvest, fertilise, sow and 
	 * then wander.
	 */
	private Behaviour[] behaviours = {
			new HarvestBehaviour(),
			new FertiliseBehaviour(),
			new SowBehaviour(),
			new WanderBehaviour()
			
	};
	
	/**
	 * This is the constructor to create a farmer object. 
	 * 
	 * @param name A string which will represent the name of the farmer
	 */
	protected Farmer(String name) {
		super(name, 'F', 100);
	}
	
	/**
	 * The play turn function decides which action that the farmer will do on each turn. It does this
	 * by iterating through the behaviours and seeing which is the first to return an action for the 
	 * farmer to do.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour: behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
}
