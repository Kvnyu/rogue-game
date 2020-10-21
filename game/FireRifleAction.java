package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.NumberRange;

/**
 * @author kevinyu
 * This class implements firing the rifle
 * 
 *
 *
 */
public class FireRifleAction extends Action{
	private int roundsWaited; //The amount of turns the player has been aiming 
	protected Display display = new Display();
	private boolean shot = false; //Whether or not this action has shot or not
	private Actor target = null; //The target the player is aiming at
	private Random rand = new Random(); 
	

	/**
	 * The beef of the class, this method is called when the player decides to fire
	 * 
	 * @param actor The actor performing the action
	 * @param map The map that actor is currently on
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (this.target == null) {//If the player hasn't chosen a target yet, we want to find them a target to shoot
		List<Actor> validTargets = new ArrayList<Actor>(); //A list of zombies that the player can shoot at
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		for (int x : xRange) {
			for (int y : yRange) {
				if (map.isAnActorAt(map.at(x, y))) {
					Actor target = map.getActorAt(map.at(x, y));
					if (!(target instanceof Human)) {
						validTargets.add(target);
					}
		}
			
			}
			
		}
		//This bit of code prints out the actors that the player can shoot
		
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Actor> keyToActorMap = new HashMap<Character, Actor>();

		for (char i = 'a'; i <= 'z'; i++)
			freeChars.add(i);

		// Show with the actions with hotkeys first;
		for (Actor target : validTargets) {
			char c;
			
			if (freeChars.isEmpty()){
				break;
			}// we've run out of characters to pick from.
			c = freeChars.get(0);
			keyToActorMap.put(c, target);
			freeChars.remove(Character.valueOf(c));
			display.println(c + ": aim at " + target.toString());
		}
		char key;
		do {
			key = display.readChar();
		} while (!keyToActorMap.containsKey(key)); //Reading the player's input
		
		
		this.target = keyToActorMap.get(key);
		
		}
		boolean wait = waitOrShoot(); //This bit of code asks the player whether they want to wait or shoot, takes input, and returns boolean
		//true if we are wating, false if we shoot
		
		
		String result = "";
		if (wait) {
			roundsWaited +=1;
			result += "Player is aiming rifle"; 
		}
		
		else {
			Rifle rifle = new Rifle();
			int damage = rifle.getDamageOnShot();
			if (roundsWaited == 0) {
				if (rand.nextInt(100) < 75) {
					
					result += System.lineSeparator() + actor + " " + rifle.verb() + " " + target + " for " + damage + " damage.";
					System.out.println(result);
					target.hurt(damage);
					this.shot = true;
				}
				else {
					result += System.lineSeparator() + actor + " has missed " + target;
				}
				
			}
			if (roundsWaited == 1) {
				if (rand.nextInt(100) < 90) {
					damage *= 2;
					result += System.lineSeparator() + actor + " " + rifle.verb() + " " + target + " for " + damage + " damage.";
					
					target.hurt(damage);
					this.shot = true;
					
				}
				else {
					result += System.lineSeparator() + actor + " has missed " + target;
				}
			}
			if (roundsWaited == 2) {
				target.hurt(999999);
				result += System.lineSeparator() + actor + " " + rifle.verb() + " " + target + " for instakill damage";
				
				this.shot = true;
				
			}
			
			
		}
		if (!target.isConscious()) {
			if (!(target instanceof Zombie)) {
				
			Item corpse = new CorpseItem(target.toString());
			map.locationOf(target).addItem(corpse);
			}
			else {
				Item corpse = new PortableItem(target.toString() + "'s corpse",'%');
				map.locationOf(target).addItem(corpse);
			}
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			result += System.lineSeparator() + target + " is killed.";
		}
		
		
		((Player) actor).setPreviousTurnHitpoints(((Player) actor).hitpoints());
		
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Fire rifle";
	}
	/**
	 * @return returns whether or not this action has been shot yet
	 */
	public boolean shot() {
		return this.shot;
	}
	
	//Returns true if the user wants to wait or false otherwise
	/**
	 * This method prints out the user's options depending on how many turns they've waited, and then reads input, returning true
	 * if the player wants to wait or false if they want to shoot
	 * 
	 * 
	 * @return
	 */
	public boolean waitOrShoot() {
		System.out.println("You have waited " + this.roundsWaited + " turns" );
		System.out.print("Current chance to hit: ");
		if (this.roundsWaited == 0) {
			System.out.println("75%");
			System.out.println("Current damage: standard");
		}
		if (this.roundsWaited == 1) {
			System.out.println("90%");
			System.out.println("Current damage: double");
		}
		if (this.roundsWaited == 2) {
			System.out.println("100%");
			System.out.println("Current damage: instakill");
		}
		
		
		
		
		display.println('a' + ": shoot " + this.target.toString());
		if (this.roundsWaited < 2) {
		display.println('b' + ": wait a turn");
		}
		char key;
		do {
			key = display.readChar();
		} while (key != 'a' && key != 'b');
		return key == 'b';
		
	}

}
