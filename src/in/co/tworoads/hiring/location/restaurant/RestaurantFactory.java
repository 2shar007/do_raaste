/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant;

import in.co.tworoads.hiring.location.restaurant.menu.MenuItemFactory;
import in.co.tworoads.hiring.location.restaurant.menu.MenuItemInterface;
import in.co.tworoads.hiring.location.restaurant.types.SimpleRestaurant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tushar Ghosh
 * 
 */
public class RestaurantFactory {
    
    static {
        numRestaurants = 0;
        restaurants = new HashMap<String, RestaurantInterface>();
    }
    
    private static Integer numRestaurants;
    
    private static Map<String, RestaurantInterface> restaurants;
    
    public static void createRestaurant(String name, String location,
            String[] menuItems) {
        
        RestaurantInterface instance = new SimpleRestaurant(numRestaurants++,
                name, location);
        for (String itemName : menuItems) {
            MenuItemInterface item = MenuItemFactory.getItem(itemName);
            instance.add(item);
        }
        
        createRestaurant(name, instance);
        
    }
    
    // Method allows decorators to customize restaurants based on locations
    public static void createRestaurant(String name,
            RestaurantInterface instance) {
        restaurants.put(name, instance);
    }
    
    public static RestaurantInterface getRestaurant(String name) {
        return restaurants.get(name);
    }
    
    public static void print() {
        System.out.println("[RestaurantFactory] Size: " + numRestaurants);
        String buff = "Contents:";
        for (String item : restaurants.keySet()) {
            buff = buff + " " + item;
        }
        System.out.println(buff + "\n");
    }
    
}
