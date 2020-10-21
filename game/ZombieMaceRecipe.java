package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
/**
 * @author kevinyu
 * A recipe for ZombieClub that can be used to craft it
 */
public class ZombieMaceRecipe extends Recipe{
	ZombieMaceRecipe(){
		super(new ZombieMace(),  new ArrayList<Item>() {
			{add(new ZombieLeg());
			};
	});
		
		
	}

	@Override
	Item getItem() {
		// TODO Auto-generated method stub
		return new ZombieMace();
	}
	
}
