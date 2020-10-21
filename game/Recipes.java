package game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevinyu
 *A collection of recipes, stores recipes and can return a list of recipes 
 */
public class Recipes {
	private List<Recipe> recipes;
	public Recipes(List<Recipe> recipes){
		this.recipes = recipes;
	}

	/**
	 * Gets a list of recipes which can be iterated through to check if any of the recipes can be crafted 
	 * 
	 * @return a list of recipes that this recipes object holds 
	 */
	public List<Recipe> getRecipes() {
		List<Recipe> newList = new ArrayList<Recipe>();

		for (Recipe recipe: this.recipes) {
			newList.add(recipe);
		}
		return newList;
	}
	/**
	 * basic setter for the list of recipe objects parameter of Recipes
	 * 
	 * @param recipes a list of recipe objects that are potentially craftable
	 */
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}


}
