/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant;

import java.util.Map;

import in.co.tworoads.hiring.location.restaurant.menu.MenuItemInterface;

/**
 * @author Tushar Ghosh
 * 
 */
public interface RestaurantInterface {
    
    public Integer getID();
    
    public String getName();
    
    public Map<String,MenuItemInterface> getMenu();
    
    public MenuItemInterface getMenuItem(String item);

    public void add(MenuItemInterface menuItem);

    public void processOrder(String menuItem, Integer qty);
    
}
