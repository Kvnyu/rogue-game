package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;

/**
 * @author kevinyu
 *
 *First World is a world that is used in creating the new win/loss conditions
 *
 */
public class FirstWorld extends World {
	private int condition;

	public FirstWorld(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *
	 * This method is used to check if the game should still be running
	 * It checks for the end conditions and returns false if any of them are true
	 *
	 *
	 */
	protected boolean stillRunning() {
		int zombieCount = 0 ;
		int humanCount = 0;
		boolean playerQuit = ((Player) player).getQuit();
		//Checking the amount of zombies and humans on the map. 
		
		for (Actor actor: this.actorLocations) {
			
			if (actor instanceof Zombie) {
				zombieCount +=1;
			}
		
			if (actor instanceof Human && !(actor instanceof Player)) {
					humanCount +=1;
				
			}
			
		}
		
		//Setting win/loss conditions to be displayed in the endGameMessage function
		if (zombieCount == 0) {
			condition = 1;
			return false;
		}
		if (!(actorLocations.contains(player))) {
			condition = 2;
			return false;
		}
		if (humanCount == 0) {
			condition = 3;
			return false;
		}
		
		return actorLocations.contains(player);
	}
	/**
	 *Displays a different message depending on the win/loss condition
	 *
	 *
	 */
	protected String endGameMessage() {
		
		boolean playerQuit = ((Player) player).getQuit();
		
		if (playerQuit) {
			return "Bye!";
		}
		
		if (condition == 2 || condition == 3) {
			return "You lose!";
			
		}
		if (condition == 1){
			return "You win!";
		}
		else {
			return "I don't know how you got to this conditional";
		}
	}
}
