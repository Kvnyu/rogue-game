package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Crop will we sowed by a farmer. After 20 turns, which can be sped up by fertilisation, it will 
 * become ready to harvest
 * 
 * @author alanzhang
 *
 */
public class Crop extends Ground{
	private int age = 0;
	
	/**
	 * This is the constructor for Crop. Since all crops will be initialised the same, it prefills the
	 * display character as m
	 */
	public Crop() {
		super('m');
	}
	
	/**
	 * The tick function counts iterates the age of the crop every turn so that we know when the crop
	 * is ripe
	 * 
	 * @param location	the location of the crop
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age >= 20) {
			displayChar = 'M';
		}
	}
	
	/**
	 * this is a function which is used by farmers which just speed up the ripening process of crops
	 * by adding 10 to the age of the crop in  one turn.
	 */
	public void fertilise() {
		age += 10;		
	}
    
	/**
	 * This function tells us if the crop is ready to harvest or not based on whether or not its age 
	 * greater than or equal to 20
	 * 
	 * @return a boolean, true if it is ripe and false otherwise
	 */
	public Boolean readyToHarvest(){
		return (age >= 20);
	}
}

