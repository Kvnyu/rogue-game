package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		//Intrinsic weapon gets called
		Weapon weapon = actor.getWeapon();

		//Checks if the attack is a bite
		if (weapon instanceof Bite) {
			//If it is, it has an 80% miss chance 
			if (rand.nextInt(100) <= 80) {
				return actor + " misses " + target + ".";
			}
			actor.heal(5);
			//If it hits, then the zombie heals 5 health 
		}
		
		//Handles the case when it isn't a bite attack
		else {
			if (rand.nextBoolean()) {
				return actor + " misses " + target + ".";
			
		}
		}
		//Checks if the target is a zombie
		//If it is, it has been hit so the following code runs probabilities of it losing limbs, and how many limbs will be lost
		//It then changes the zombie's limbs attributes, and drops the necessary items. This is handled by the zombie object methods
		if (this.target instanceof Zombie) {
			if (((Zombie) this.target).hasLimbs()) {
				((Zombie) this.target).zombieLoseLimbs(map);
			}
		}
		
		int damage = weapon.damage();
		
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		
		
		
		//If they get killed, they drop all the items they had in the inventory 
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
			if (target instanceof MamboMarie) {
				((MamboMarieGameMap)map).mamboDies();
			}
			
		}
		return result;
	}
	
	

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}



}
	