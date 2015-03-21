/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.menu.type;

import java.util.HashMap;
import java.util.Map;

import in.co.tworoads.hiring.location.ingredients.IngredientInterface;
import in.co.tworoads.hiring.location.restaurant.menu.MenuItemInterface;

/**
 * @author Tushar Ghosh
 *
 */
public class SimpleMenuItem implements MenuItemInterface {
    
    private Integer id;
    
    private String name;

    private Map<String,IngredientInterface> ingredientList;
    
    private Map<String,Integer> ingredientQuantities;
    
    /**
     * 
     */
    public SimpleMenuItem(Integer uid, String name) {
        this.id = uid;
        this.name = name;
        this.ingredientList = null;
        this.ingredientQuantities = null;
    }
    
    /* (non-Javadoc)
     * @see in.co.tworoads.hiring.location.restaurant.menu.MenuItemInterface#getID()
     */
    @Override
    public Integer getID() {
        return this.id;
    }
    
    /* (non-Javadoc)
     * @see in.co.tworoads.hiring.location.restaurant.menu.MenuItemInterface#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /* (non-Javadoc)
     * @see in.co.tworoads.hiring.location.restaurant.menu.MenuItemInterface#getIngredients()
     */
    @Override
    public Map<String,IngredientInterface> getIngredients() {
        return this.ingredientList;
    }

    @Override
    public void add(IngredientInterface ingredient, Integer qty) {
        String ingredientName = ingredient.getName();
        if(ingredientList == null) {
            ingredientList = new HashMap<String, IngredientInterface>();
        }
        if(ingredientQuantities == null) {
            ingredientQuantities = new HashMap<String, Integer>();
        }
        this.ingredientList.put(ingredientName, ingredient);
        this.ingredientQuantities.put(ingredientName, qty);
    }

    @Override
    public Map<String, Integer> getQuantities() {
        return this.ingredientQuantities;
    }
    
}
