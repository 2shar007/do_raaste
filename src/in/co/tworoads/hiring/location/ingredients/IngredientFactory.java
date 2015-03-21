/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.ingredients;

import in.co.tworoads.hiring.location.ingredients.type.SimpleIngredient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tushar Ghosh
 * 
 */
public class IngredientFactory {
    
    static {
        numIngredients = 0;
        ingredients = new HashMap<String, IngredientInterface>();
    }
    
    private static Integer numIngredients;
    
    private static Map<String, IngredientInterface> ingredients;
    
    public static void createIngredient(String ingredientName,
            Integer numExpiry, String location) {
        IngredientInterface instance = new SimpleIngredient(numIngredients++,
                ingredientName, numExpiry, location);
        
        createIngredient(ingredientName, instance);
    }
    
    public static void createIngredient(String name,
            IngredientInterface instance) {
        ingredients.put(name, instance);
    }
    
    public static IngredientInterface getIngredient(String ingredientName) {
        return ingredients.get(ingredientName);
    }
    
    public static void print() {
        System.out.println("[IngredientFactory] Size: " + numIngredients);
        String buff = "Contents:";
        for(String item: ingredients.keySet()) {
            buff = buff + " " + item;
        }
        System.out.println(buff + "\n");
    }
    
}
