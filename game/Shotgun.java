package game;

import edu.monash.fit2099.engine.Action;

/**
 * @author kevinyu
 *
 * A shotgun
 */
public class Shotgun extends GunItem  {

	public Shotgun() {
		super("Shotgun", 'L', 5, new ShotgunAmmunition(), "blat blat blat", 90);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This class is used by player to get the FireShotgunAction
	 */
	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return new FireShotgunAction();
	}
	

}
