/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.inventory.type;

import in.co.tworoads.hiring.Config;
import in.co.tworoads.hiring.location.ingredients.IngredientInterface;
import in.co.tworoads.hiring.location.restaurant.inventory.Inventory;
import in.co.tworoads.hiring.location.restaurant.inventory.InventoryManagerInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tushar Ghosh
 * 
 */
public class SimpleInventoryManager implements InventoryManagerInterface {
    
    private Integer id;
    
    private String name;
    
    private Integer seed;
    
    private String lastStockedDate;
    
    private Map<String, Inventory> stock;
    
    public SimpleInventoryManager(Integer uid, String name, Integer init) {
        this.id = uid;
        this.name = name;
        this.seed = init;
        this.stock = new HashMap<String, Inventory>();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.restaurant.inventory.InventoryManagerInterface
     * #getID()
     */
    @Override
    public Integer getID() {
        return this.id;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.restaurant.inventory.InventoryManagerInterface
     * #getName()
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * @return the seed
     */
    public Integer getSeed() {
        return seed;
    }
    
    /**
     * @param seed
     *            set seed
     */
    public void setSeed(Integer seed) {
        this.seed = seed;
    }
    
    private synchronized boolean updateInventory() {
        Config conf = Config.getInstance();
        if(this.lastStockedDate.equalsIgnoreCase(conf.getCurrDate())) {
            return false;
        }
        String curr_t = conf.getCurr_t();
        
        for (String inv : stock.keySet()) {
            Inventory row = stock.get(inv);
            long exp = row.getExpiry();
            String buy_t = row.getBuy_t();
            long diff = conf.getDuration(buy_t, curr_t);
            if (diff > exp) {
                row.setStockedQuantity(0);
                row.setBuy_t(curr_t);
            }
        }
        
        stockInventory(curr_t);
        return true;
    }
    
    private synchronized void stockInventory(String t) {
        String date = t.split("[\\s]+")[0].trim();
        Config conf = Config.getInstance();
        
        String buff = "Stocking Inventory for " + date + "\n";
        
        for (String inv : stock.keySet()) {
            Inventory row = stock.get(inv);
            long t1 = conf.getTime(row.getBuy_t());
            Integer q1 = row.getStockedQuantity();
            long t2 = conf.getTime(t);
            Integer q2 = row.getEstimatedConsumption();
            long tt = t1 + (t2 * (q2 - q1) + t1 * q1) / (q1 + q2);
            String inv_t = conf.getTime(tt);
            row.setBuy_t(inv_t);
            row.setStockedQuantity(q2);
            buff = buff + inv + " = " + q2 + "\n";
        }
        this.lastStockedDate = date;
        System.out.println(buff);
    }
    
    @Override
    public synchronized void add(Map<String, IngredientInterface> ingredients) {
        // ingredient name, current stock, buy day, expiry,
        Config conf = Config.getInstance();
        for (String ingredientName : ingredients.keySet()) {
            Inventory instance = new Inventory(conf.getCurr_t(),
                    ingredients.get(ingredientName), conf.getSeed());
            this.lastStockedDate = conf.getCurrDate();
            this.stock.put(ingredientName, instance);
        }
    }
    
    @Override
    public synchronized boolean execute(Map<String, Integer> ingredientList,
            Integer qty) {
        boolean isServiced=true;
        boolean isUpdated = updateInventory();
        
        for (String ingredientName : ingredientList.keySet()) {
            Integer qtyPerItem = ingredientList.get(ingredientName);
            Inventory row = stock.get(ingredientName);
            isServiced = row.updateConsumption(qty*qtyPerItem, isUpdated);
        }
        
        if(isServiced) {
            for (String ingredientName : ingredientList.keySet()) {
                Integer qtyPerItem = ingredientList.get(ingredientName);
                Inventory row = stock.get(ingredientName);
                row.use(qty*qtyPerItem);
            }
        }
        
        return isServiced;
    }
    
}
