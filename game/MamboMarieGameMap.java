package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Item;

/**
 * This is an extension of GameMap which creates a map where each turn, there is a chance that Mambo Marie spawns on 
 * the edge of the map.
 * 
 * @author alanzhang
 *
 */
public class MamboMarieGameMap extends GameMap{
	
	protected Random rand = new Random();
	private int mambo = 0;

	/**
	 * This is the constructor which takes in a groundFactory and lines as an input which will
	 * form what the map looks like and is made of.
	 * 
	 * @param groundFactory	a ground factory which creates the map
	 * @param lines an array of strings which are display characters for typers of ground
	 */
	public MamboMarieGameMap(GroundFactory groundFactory, List<String> lines) {
		super(groundFactory, lines);
	}
	
	/**
	 * This is a tick function which is run every turn. It calls the tick function on all locations
	 * of the map. At the start it will also check if there is a possibility for MamboMarie to spawn.
	 * If so there is a 5% chance that this function initialised Mambo Marie on one of the edges of the
	 * map.
	 */
	@Override
	public void tick() {
		if (this.mambo == 0){
			if (rand.nextInt(100)<5) {
				this.mambo = 1;
				int edge = rand.nextInt(4);
				if (edge == 0) {
					this.at(0, rand.nextInt(this.getYRange().max())).addActor(new MamboMarie());;
				}
				else if (edge == 1) {
					this.at(this.getXRange().max(), rand.nextInt(this.getYRange().max())).addActor(new MamboMarie());;
				}
				else if (edge == 2) {
					this.at(rand.nextInt(this.getXRange().max()), 0).addActor(new MamboMarie());
				}
				else if (edge == 3) {
					this.at(rand.nextInt(this.getXRange().max()), this.getYRange().max()).addActor(new MamboMarie());
				}
			}
		}
		for (Actor actor : actorLocations) {
			if (this.contains(actor)) {
				for (Item item : new ArrayList<Item>(actor.getInventory())) { 
					item.tick(actorLocations.locationOf(actor), actor);
				}
			}
		}

		for (int y : heights) {
			for (int x : widths) {
				this.at(x, y).tick();
			}
		}
	}
	
	/**
	 * This is a mutator that sets the private attribute of Mambo to -1 to signify that mambo marie has been
	 * killed and cannot respawn
	 */
	public void mamboDies() {
		this.mambo = -1;
	}
	
	/**
	 * This is a mutator that sets the private attribute of Mambo to 0 to signify that mambo marie has disappeared
	 * meaning it can still reappear somtime in the future.
	 */
	public void mamboDisappears() {
		this.mambo = 0;
	}

}
