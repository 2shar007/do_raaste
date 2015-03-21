/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.menu;

import in.co.tworoads.hiring.location.ingredients.IngredientInterface;

import java.util.Map;

/**
 * @author Tushar Ghosh
 *
 */
public interface MenuItemInterface {
    
    public Integer getID();
    
    public String getName();
    
    public Map<String, IngredientInterface> getIngredients();
    
    public Map<String, Integer> getQuantities();

    public void add(IngredientInterface ingredient, Integer integer);
    
}
