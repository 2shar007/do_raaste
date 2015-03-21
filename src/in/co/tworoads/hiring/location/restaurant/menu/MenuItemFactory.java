/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.menu;

import in.co.tworoads.hiring.location.ingredients.IngredientFactory;
import in.co.tworoads.hiring.location.ingredients.IngredientInterface;
import in.co.tworoads.hiring.location.restaurant.menu.type.SimpleMenuItem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tushar Ghosh
 * 
 */
public class MenuItemFactory {
    
    static {
        numItems = 0;
        menuItems = new HashMap<String, MenuItemInterface>();
    }
    
    private static Integer numItems;
    
    private static Map<String, MenuItemInterface> menuItems;
    
    public static void createMenuItem(String item,
            Map<String, Integer> ingredientTable) {
        MenuItemInterface instance = new SimpleMenuItem(numItems++, item);
        
        for (String ingredientName : ingredientTable.keySet()) {
            IngredientInterface ingredient = IngredientFactory
                    .getIngredient(ingredientName);
            instance.add(ingredient, ingredientTable.get(ingredientName));
        }
        
        menuItems.put(item, instance);
    }
    
    public static MenuItemInterface getItem(String itemName) {
        return menuItems.get(itemName);
    }
    
    public static void print() {
        System.out.println("[MenuItemFactory] Size: " + numItems);
        String buff = "Contents:";
        for(String item: menuItems.keySet()) {
            buff = buff + " " + item;
        }
        System.out.println(buff + "\n");
    }
    
}
