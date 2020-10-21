package game;

import java.util.List;

import edu.monash.fit2099.engine.*;


/**
 * A class that implements the Behaviour class, creating an objective that both zombies and humans 
 * will possess. It is the objective to pick up certain items from the ground.
 */
public class PickUpBehaviour implements Behaviour {
	
	/**
	 * PickUpBehaviour is an objective that will be possessed by all instances of a ZombieActor. This
	 * tells the actor, depending on if they are a human or a zombie, whether they can conduct the
	 * pickUpItemAction on the item that they are currently standing on.
	 * 
	 * @param actor	the actor that will display this behaviour
	 * @param map 	the GameMap which the Actor is in.
	 * 
	 * @return Will return a pickUpItemAction on the item that the actor is standing on if they are able to pick it up, or null otherwise
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> itemList = map.locationOf(actor).getItems();
		
		
		for (Item item: itemList) {
			if (actor instanceof Zombie) {
				if (!((Zombie) actor).canPickUp()) {
					return null;
				}
				if (item.asWeapon() instanceof WeaponItem){
					PickUpItemAction pickUpItemAction = new PickUpItemAction(item);
					return pickUpItemAction;
				}
			}
			else if (actor instanceof Human) {
				if (item instanceof FoodItem){
					PickUpItemAction pickUpItemAction = new PickUpItemAction(item);
					return pickUpItemAction;
				}
			}
			
		}
		return null;
	}

	
}
