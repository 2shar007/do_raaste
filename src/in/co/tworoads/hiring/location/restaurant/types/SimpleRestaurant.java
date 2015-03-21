/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.types;

import in.co.tworoads.hiring.Config;
import in.co.tworoads.hiring.location.Location;
import in.co.tworoads.hiring.location.restaurant.RestaurantInterface;
import in.co.tworoads.hiring.location.restaurant.inventory.InventoryManagerFactory;
import in.co.tworoads.hiring.location.restaurant.inventory.InventoryManagerInterface;
import in.co.tworoads.hiring.location.restaurant.menu.MenuItemInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tushar Ghosh
 * 
 */
public class SimpleRestaurant implements RestaurantInterface {
    
    private Integer id;
    
    private String name;
    
    private Location place;
    
    private Map<String, MenuItemInterface> menuItems;
    
    private InventoryManagerInterface inventory;
    
    /**
     * 
     */
    public SimpleRestaurant(Integer uid, String name, String coords) {
        this.id = uid;
        this.name = name;
        this.place = new Location(coords);
        this.menuItems = null;
        // create inventory manager which with initial stock for each
        // ingredient
        this.inventory = null;
        
        this.initInventoryManager();
    }
    
    private void initInventoryManager() {
        Config conf = Config.getInstance();
        this.inventory = InventoryManagerFactory.createInventoryManager(name
                + "." + place.getName(), conf.getSeed());
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.restaurant.RestaurantInterface#getID()
     */
    @Override
    public Integer getID() {
        return this.id;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.restaurant.RestaurantInterface#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.restaurant.RestaurantInterface#getMenu()
     */
    @Override
    public Map<String, MenuItemInterface> getMenu() {
        return this.menuItems;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.restaurant.RestaurantInterface#getMenuItem
     * (java.lang.String)
     */
    @Override
    public MenuItemInterface getMenuItem(String item) {
        return this.menuItems.get(item);
    }
    
    @Override
    public void add(MenuItemInterface menuItem) {
        if (this.menuItems == null) {
            this.menuItems = new HashMap<String, MenuItemInterface>();
        }
        this.menuItems.put(menuItem.getName(), menuItem);
        this.inventory.add(menuItem.getIngredients());
    }
    
    @Override
    public void processOrder(String itemName, Integer qty) {
        MenuItemInterface item = menuItems.get(itemName);
        Map<String, Integer> ingredientList = item.getQuantities();
        
        boolean isProcessed = this.inventory.execute(ingredientList, qty);
        
        if (isProcessed) {
            System.out.println(qty + " " + itemName + " served at " + name);
        } else {
            System.out.println("Not able to serve " + itemName + " at " + name);
        }
    }
    
}
