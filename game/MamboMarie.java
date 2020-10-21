package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
/**
 * this is a class that represents the NPC which is Mambo Marie. It extends Zombie Actor class.
 * @author alanzhang
 *
 */

public class MamboMarie extends ZombieActor{
	
	private int age = 0;
	private Behaviour behaviour = new WanderBehaviour();
	
	/**
	 * Constructor which does not take a parameter because the attributes of Mambo Marie are preset.
	 */
	public MamboMarie() {
		super("Mambo Marie", '&', 100, ZombieCapability.UNDEAD);
	}
	
	/**
	 * This funciton decides what action the Mambo Marie actor will do each turn. If the actor's age is 30
	 * Mambo MArie will disappear from the map. If the age is a multiple of 10, then it will return a 
	 * spawnZombieAction. Otherwise it will just return an action from Wander behaviour
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current actor is
	 * @param display the Display where the Mambo Marie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		age++;
		if (age == 30) {
			map.removeActor(this);
			((MamboMarieGameMap)map).mamboDisappears();
		}
		if (age%10 == 0) {
			return new SpawnZombieAction(5);
		}
		
		Action action = behaviour.getAction(this, map);
		if (action != null)
			return action;
		
		return new DoNothingAction();	
	}
}

