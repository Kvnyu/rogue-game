package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Weapon;

import java.util.Random;
/**
 * This Zombie has been made more interesting.
 * The main antagonist of this game. 
 * They hunt down humans and attack them, eating their flesh
 * 
 * 
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	
	private Behaviour[] behaviours = {
			new PickUpBehaviour(),
			new SpeakBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
			
	};
	//Each zombie has arms and legs that can be lost in battle
	private int arms = 2;
	private int legs = 2;
	
	//probability of punching attribute needed as it can decrease when they lose limbs
	private int punchChance = 50;
	private String line = "Braaaaains";
	
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	
	/**
	 * This method retunrs the weapon that the Zombie will use to attack
	 * If a Zombie has 2 arms, it will punch with the default probability
	 * If a Zombie has 1 arm, it will punch with half of the default probability
	 * If a Zombie has 0 arms, it will always use a bite attack
	 * 
	 * 
	 * 

	
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (arms == 2) {
			Random rand = new Random();
			if (rand.nextInt(100) <= 100-punchChance) {
				return new Bite();
			}
			return new IntrinsicWeapon(10, "punches");
		}
		else if (arms == 1) {
			Random rand = new Random();
			if (rand.nextInt(100) <= 100 - punchChance/2) {
				return new Bite();
			}
			return new IntrinsicWeapon(10, "punches");
		}	
		else {
			return new Bite();
		}
	}

	/**
	 * In order of what the Zombie may do:
	 * If a Zombie can pick up a weapon, it will
	 * It also has a 10% chance on any given turn to do nothing and say "brains"
	 * If a Zombie can attack, it will. 
	 * If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly. 
	 * 
	 * For moving actions, it checks how many legs the zombie has and whether it can make that moving action
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			
			if (action instanceof MoveActorAction) {
				//If the zombie moved last turn and they have one leg then they can't move this turn 
				if (this.legs == 1) {
					if (lastAction instanceof MoveActorAction) {
						continue;
					}
					else {
						return action;
					}
				}
				//If the zombie has no legs they can't move at all 
				if (this.legs == 0) {
					action = null;
				}
			}
			
			
			if (action != null)
				
				return action;
		}
		return new DoNothingAction();	
	}
	
	/**
	 * This method is called by Attack action when a Zombie is hit
	 * It essentially runs probabilites on 
	 *  - Whether a zombie will lose one or more limbs
	 *  - How many limbs a zombie will lose
	 *  - Which limbs the zombie will lose
	 * Once it determines the limbs to lose, it will execute this
	 *  
	 * Additionally, if the zombie loses arms, it will also run a probability on whether or not it will drop its item
	 * 50% if the zombie loses one arm, and 100% if it loses both
	 * 
	 * 
	 * @param map
	 */

	public void zombieLoseLimbs(GameMap map) {
		Random rand = new Random();
			//25% chance of losing a limb or more 
			int i = 75;
			if (rand.nextInt(100)<i) {
				
			//Zombie has a chance to lose 1 up to how many limbs it has, each amount with equal chance 
			//limbsLost is the number of limbs the Zombie will lose 
			Integer limbsLost = rand.nextInt(arms+legs) +1;
			System.out.println(limbsLost);
			
			while (limbsLost >0) {
				//If the zombie has no arms, it will only be losing limbsLost amount of legs
				if (arms ==0) {
					legs -= limbsLost;
					while (limbsLost > 0) {
						map.locationOf(this).addItem(new ZombieLeg());
						System.out.println(this + " has lost " + "a leg!");
						limbsLost-=1;
					}
					limbsLost = 0; 
				}
				//If the zombie has no legs, it will only be losing limbsLost amount of arms
				else if (legs ==0) {
					arms -= limbsLost;
					
					while (limbsLost > 0) {
						map.locationOf(this).addItem(new ZombieArm());
						System.out.println(this + " has lost " + "an arm!");
						limbsLost-=1;
					}
					//50% probability of dropping each item if zombie has 1 arm left, 100% if 0 arms
					if (arms == 0) {
						dropItems(this,100,map);
						
						
					}
					else if (arms == 1) {
						dropItems(this,50,map);
		
					}
				}
				//If the zombie has both arms and legs, then we need to run a probability to 
				//determine whether to lose arms or legs. 1/2 chance to lose arm/leg resp.
				
				else {
					if (rand.nextBoolean()) {
						arms -= 1;
						limbsLost -= 1;
						map.locationOf(this).addItem(new ZombieArm());
						System.out.println(this + " has lost " + "an arm!");
			
					
						//50% probability of dropping each item if zombie has 1 arm left, 100% if 0 arms
						if (arms == 0) {
							dropItems(this,100,map);
							
							
						}
						else if (arms == 1) {
							dropItems(this,50,map);
			
						}

					}
					else {
						legs -= 1 ;
						limbsLost -= 1;
						map.locationOf(this).addItem(new ZombieLeg());
						System.out.println(this + " has lost " + "a leg!");
				
				
					}
					
				}

			}
			
		}
		
		System.out.println(this +" has " + arms + " arm/s");
		System.out.println(this +" has "  + legs + " leg/s");
		
		
		
	}
	/**
	 * This method helps the zombieLoseLimbs function, when the zombie loses arms, this function runs probabilities
	 * on the zombie dropping its items. 
	 * 
	 * 
	 * @param target This is the zombie
	 * @param chance Chance of dropping per item in inventory
	 * @param map The map that the zombie is on
	 */
	private void dropItems(Actor target, int chance, GameMap map) {
		Random rand = new Random();
		Actions dropActions = new Actions();		
		for (Item item :target.getInventory() ) {
			if (item instanceof Weapon) {
				if (rand.nextInt(100) <=chance) {
					dropActions.add(item.getDropAction());
				}
			}
		}
		for (Action drop : dropActions) {
			
			drop.execute(target, map);

				
			}
		

	}
	/**
	 * Used by AttackAction to see if the zombieLoseLimbs function should be called
	 * @return true if the zombie still has limbs 
	 */
	public boolean hasLimbs() {
		if (arms + legs > 0){
			return true;
		}
		else {
			return false;
		}
	}


	public String getLine() {
		return line;
	}
	public boolean canPickUp() {
		if (arms>0) {
			return true ;
		}
		else {
			return false;
		}
		
	}

	

}
