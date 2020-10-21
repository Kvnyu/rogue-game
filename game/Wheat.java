package game;
/**
 * Wheat is a basic form of food that is acquired from harvesting ripe crops
 * @author alanzhang
 *
 */
public class Wheat extends FoodItem{

	/**
	 * This constructor is prefilled since all wheat will have the same properties.
	 */
	public Wheat() {
		super("Wheat", 'W', 10);
	}

}
