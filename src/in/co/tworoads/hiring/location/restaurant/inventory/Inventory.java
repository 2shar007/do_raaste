/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.restaurant.inventory;

import in.co.tworoads.hiring.location.ingredients.IngredientInterface;

/**
 * @author Tushar Ghosh
 * 
 */
public class Inventory {
    
    IngredientInterface item;
    
    private Integer prevConsumptionRequests;
    
    private Integer currentConsumptionRequests;
    
    private Integer stockedQuantity;
    
    private String buy_t;
    
    /**
     * 
     */
    public Inventory(String curr_t, IngredientInterface ingredient, Integer qty) {
        this.buy_t = curr_t;
        this.item = ingredient;
        this.stockedQuantity = qty;
        this.prevConsumptionRequests = 0;
        this.currentConsumptionRequests = 0;
    }
    
    public void use(Integer qty) {
        this.stockedQuantity = stockedQuantity - qty;
    }
    
    public Integer getExpiry() {
        return item.getExpiry();
    }
    
    public String getBuy_t() {
        return this.buy_t;
    }

    /**
     * @param buy_t the buy_t to set
     */
    public void setBuy_t(String buy_t) {
        this.buy_t = buy_t;
    }

    /**
     * @return the stockedQuantity
     */
    public Integer getStockedQuantity() {
        return stockedQuantity;
    }

    /**
     * @param stockedQuantity the stockedQuantity to set
     */
    public void setStockedQuantity(Integer stockedQuantity) {
        this.stockedQuantity = stockedQuantity;
    }
    
    public Integer getEstimatedConsumption() {
        return (this.prevConsumptionRequests+this.currentConsumptionRequests)/2;
    }

    public boolean updateConsumption(Integer numReq, boolean update) {
        if(update == true) {
            this.prevConsumptionRequests = this.currentConsumptionRequests;
            this.currentConsumptionRequests = numReq;
        } else {
            this.currentConsumptionRequests += numReq;
        }
        return (numReq <= stockedQuantity);
    }
    
}
