package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {
	private Recipes recipes;
	private Menu menu = new Menu();
	private int previousTurnHitpoints;
	private boolean quit = false;


	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		for (Recipe recipe: this.recipes.getRecipes()) {

			if (craftable(recipe)) {
				
				actions.add(new CraftAction(recipe));
			}
		}
	
		List<Action> shootActions = shootActions(lastAction);
		for (Action shootAction: shootActions) {
			if (!(shootAction == null)) {
				actions.add(shootAction);
			}
		}
		
		
		
		if (((Human)this).damaged()) {
			for (Item item: this.getInventory()) {
				if (item instanceof FoodItem) {
					actions.add(new EatAction(((FoodItem)item)));
				}
			}
		}
		actions.add(new QuitGameAction());

		return menu.showMenu(this, actions, display);
	}
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "punches");
	}
	/**
	 * This method is called by Player to check if a particular recipe is craftable by the player
	 * It compares the items in itemsRequired to the items in the player's inventory. If they have all the itemsRequired, then
	 * they can craft the itemToCraft
	 * 
	 * 
	 * @param recipe This is the recipe we are checking to see if they can craft 
	 * @return true if the player can craft it, false otherwise 
	 */
	private boolean craftable(Recipe recipe){
		List<Item> inventory = this.getInventory();
		List<Item> itemsRequired = recipe.getItemsRequired();
		
		for (Item item: itemsRequired) {
			boolean notFound = true;
			int i = 0;
			while ( notFound && i<inventory.size()){
				if (inventory.get(i).getClass().equals(item.getClass())){
					notFound = false;
					break;
				}
				i++;
					
			}
			if (notFound) {
			return false; 
			}
		}
		return true;
	}

	/**
	 * 
	 * Setter method to set the recipes for a player 
	 * @param recipes
	 */
	public void addRecipesToInventory(Recipes recipes) {
		
		this.recipes = recipes;
	}
	/**
	 * This method is used by player to check if they have the necessary items to make a firerifle or fireshotgun action
	 * 
	 * 
	 * 
	 * @param lastAction the last action the player took
	 * @return A list of shooting actions, empty if none can be made at the time
	 */
	private List<Action> shootActions(Action lastAction){
		List<Action> ShootActions = new ArrayList();
		List<Item> inventory = this.getInventory();
		for (Item item:inventory) {
			if (item instanceof GunItem) {
				//Checks if the player has a gun
				
				
				Ammunition ammunition = ((GunItem) item).ammunitionType();
				
				//Checks if the player has the proper ammunition type, which is stored in the GunItem
				for (Item item2:inventory) {
					if (item2.getClass() == ammunition.getClass()) {
						if (item instanceof Rifle) {
							if (lastAction instanceof FireRifleAction)
								
								//IF the player's last turn was a fire rifle action
								// and if they didn't take damage last turn and 
								// and if they aimed last turn and didnt shoot
								//we add the rifle action!
								if (!((FireRifleAction) lastAction).shot() && !this.tookDamage()) {
									ShootActions.add(lastAction);
									break;
								}
						}
						ShootActions.add(((GunItem) item).getAction());
						break;
					}
				}
			}
		}
		
		
		
		
		return ShootActions;
	}
	/**
	 * 
	 * Checks whether the player has taken damage since the last turn 
	 * for use with firerifleaction
	 * @return true if they took damage, false otherwise
	 */
	public boolean tookDamage() {
		int currentTurnHitpoints = this.hitPoints;
		
		
		return currentTurnHitpoints != this.previousTurnHitpoints;
		
	}
	/**
	 * @return returns the health of the player
	 */
	public int hitpoints() {
		
		return this.hitPoints;
	}
	/**
	 * 
	 * This is used by FireRifleAction to keep track of whether the player has taken damage or not
	 * @param hitpoints
	 */
	public void setPreviousTurnHitpoints(int hitpoints) {
		this.previousTurnHitpoints = hitpoints;
	}
	/**
	 * 
	 * Setter for the Quit method. True if the player as quit and false otherwise
	 * @param quit
	 */
	public void setQuit(Boolean quit) {
		this.quit = quit;
	}
	/**
	 * This method is used by FirstWorld to check if the player has quit
	 * 
	 * @return 
	 */
	public boolean getQuit() {
		return this.quit;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}