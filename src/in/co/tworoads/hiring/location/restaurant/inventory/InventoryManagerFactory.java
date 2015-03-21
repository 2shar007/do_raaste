/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.inventory;

import in.co.tworoads.hiring.location.restaurant.inventory.type.SimpleInventoryManager;

/**
 * @author Tushar Ghosh
 * 
 */
public class InventoryManagerFactory {
    
    static {
        numInventoryManagers = 0;
    }
    
    private static Integer numInventoryManagers;
    
    public static InventoryManagerInterface createInventoryManager(String name,
            Integer init) {
        SimpleInventoryManager instance = new SimpleInventoryManager(
                numInventoryManagers++, name, init);
        return instance;
    }
}
