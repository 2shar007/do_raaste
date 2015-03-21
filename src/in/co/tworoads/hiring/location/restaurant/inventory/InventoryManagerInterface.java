/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.inventory;

import in.co.tworoads.hiring.location.ingredients.IngredientInterface;

import java.util.Map;
import java.util.Set;

/**
 * @author Tushar Ghosh
 * 
 */
public interface InventoryManagerInterface {
    
    public Integer getID();
    
    public String getName();
    
    public void add(Map<String, IngredientInterface> ingredientNames);

    public boolean execute(Map<String, Integer> ingredientList, Integer qty);
    
}
