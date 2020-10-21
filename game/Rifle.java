package game;

import edu.monash.fit2099.engine.Action;

/**
 * @author kevinyu
 * The rifle item
 */
public class Rifle extends GunItem{
	public Rifle() {
		super("Rifle", 'F' , 5, new RifleAmmunition(), "Kachow",150);
		
	}

	/**
	 *
	 *Used by player to get the FireRifleAction 
	 *
	 */
	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return new FireRifleAction();
	}

}
