package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Item;

/**
 * @author kevinyu
 * A recipe for ZombieClub that can be used to craft it
 */
public class ZombieClubRecipe extends Recipe{
	ZombieClubRecipe(){
		

		super(new ZombieClub(),  new ArrayList<Item>() {
			{add(new ZombieArm());
			};
	});
		
		
	}

	@Override
	Item getItem() {
		// TODO Auto-generated method stub
		return new ZombieClub();
	}
}
