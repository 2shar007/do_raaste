/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring;

import in.co.tworoads.hiring.location.ingredients.IngredientFactory;
import in.co.tworoads.hiring.location.restaurant.RestaurantFactory;
import in.co.tworoads.hiring.location.restaurant.RestaurantInterface;
import in.co.tworoads.hiring.location.restaurant.menu.MenuItemFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tushar Ghosh
 * 
 */
public class Main {
    
    private static void load(String path1, String path2, String path3)
            throws IOException {
        String currentLine = null;
        
        // Ingredient information
        BufferedReader buff = new BufferedReader(new FileReader(path3));
        while ((currentLine = buff.readLine()) != null) {
            String[] components = currentLine.split("[,\\n\\t\\s]+");
            if (components.length != 2) {
                System.out.println("Ingredient information is corrupted. Exit");
                System.exit(0);
            }
            IngredientFactory.createIngredient(components[0],
                    Integer.valueOf(components[1]), "");
        }
        buff.close();
        
        // Menu information
        buff = new BufferedReader(new FileReader(path2));
        while ((currentLine = buff.readLine()) != null) {
            String[] components = currentLine.split("[,\\n\\t\\s]+");
            if (components.length % 2 == 0) {
                System.out.println("Menu information is corrupted. Exit");
                System.exit(0);
            }
            String item = components[0];
            Map<String, Integer> ingredientTable = new HashMap<String, Integer>();
            for (int i = 1; i < components.length; i += 2) {
                Integer qty = Integer.valueOf(components[i]);
                String ingredient = components[i + 1];
                ingredientTable.put(ingredient, qty);
            }
            MenuItemFactory.createMenuItem(item, ingredientTable);
        }
        buff.close();
        
        // Restaurant information
        buff = new BufferedReader(new FileReader(path1));
        while ((currentLine = buff.readLine()) != null) {
            if (currentLine.isEmpty()) {
                continue;
            }
            String restaurant = currentLine.split("[,\\n\\t\\s]+")[0];
            if ((currentLine = buff.readLine()) == null) {
                System.out.println("Restaurant information is corrupted. Exit");
                System.exit(0);
            }
            String[] items = currentLine.split("[,\\n\\t\\s]+");
            RestaurantFactory.createRestaurant(restaurant, "", items);
        }
        buff.close();
        
    }
    
    private static void run(String path) throws IOException {
        
        // Read and process Order Information
        String currentOrder = null;
        BufferedReader buff = new BufferedReader(new FileReader(path));
        while ((currentOrder = buff.readLine()) != null) {
            String[] components = currentOrder.split("[,]+");
            if (components.length != 4) {
                System.out.println("Order information is corrupted. Exit");
                System.exit(0);
            }
            String curr_t = components[0].trim();
            String restaurant = components[1].trim();
            String item = components[2].trim();
            Integer qty = Integer.valueOf(components[3].trim());
            
            /*System.out.println(curr_t);
            System.out.println(restaurant);
            System.out.println(item);
            System.out.println(qty);*/
            
            Config conf = Config.getInstance();
            conf.setCurr_t(curr_t);
            
            RestaurantInterface restaurantInstance = RestaurantFactory
                    .getRestaurant(restaurant);
            
            restaurantInstance.processOrder(item, qty);
        }
        buff.close();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        if (args.length != 6) {
            System.out.println("Inadequate input");
            System.exit(0);
        }
        
        try {
            System.out.println("Loading Start Time and Inventory seed");
            Config conf = Config.getInstance();
            conf.setSeed(args[4]);
            conf.setStart_t(args[5]);
            
            System.out
                    .println("Loading restaurant, menu and ingredient information");
            
            load(args[0], args[1], args[2]);
            
            System.out.println("Running orders");
            
            run(args[3]);
        } catch (IOException ex) {
            System.out.println("Failed to load data");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System ran unexpectedly");
        } finally {
            System.exit(0);
        }
        
    }
    
}
