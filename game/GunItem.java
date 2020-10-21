package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * 
 * 
 * @author kevinyu
 *Parent class for a GunItem
 */
public abstract class GunItem extends WeaponItem{
	private Ammunition ammunition;
	private Integer damageOnShot;


	/**
	 * @param name The name of the gun
	 * @param displayChar The display character on the map
	 * @param damage The damage the gun does if used as a melee weaponItem
	 * @param ammunition The ammunition type that the gun takes
	 * @param verb The noise the gun makes when it attacks
	 * @param damageOnShot The damage the gun does when it shoots
	 */
	public GunItem(String name, char displayChar,int damage,Ammunition ammunition, String verb, Integer damageOnShot ) {
		super(name, displayChar, damage , verb);
		this.ammunition = ammunition;
		this.damageOnShot = damageOnShot;
		
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * returns the type of ammunition used by the gun
	 * @return Ammunition
	 */
	public Ammunition ammunitionType() {
		return ammunition;
	}
	/**
	 * 
	 * returns the damage the gun does when it shoots
	 * @return Integer the amount of dammage when shot
	 */
	public Integer getDamageOnShot() {
		return damageOnShot;
	}
	/**
	 * 
	 * This is to be implemented as subclass guns might have their own respective shoot____ actions
	 * @return a shoot____action which can be executed by player
	 */
	public abstract Action getAction();
	
	

}
