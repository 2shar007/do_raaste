/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location.ingredients.type;

import in.co.tworoads.hiring.location.Location;
import in.co.tworoads.hiring.location.ingredients.IngredientInterface;

/**
 * @author Tushar Ghosh
 * 
 */
public class SimpleIngredient implements IngredientInterface {
    
    private Integer id;
    
    private String name;
    
    private Location place;
    
    private Integer expiry;
    
    /**
     * 
     */
    public SimpleIngredient(Integer uid, String name, Integer duration,
            String coords) {
        this.id = uid;
        this.name = name;
        this.expiry = duration;
        this.place = new Location(coords);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.ingredients.IngredientInterface#getID()
     */
    @Override
    public Integer getID() {
        return this.id;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.ingredients.IngredientInterface#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.tworoads.hiring.location.ingredients.IngredientInterface#getExpiry
     * ()
     */
    @Override
    public Integer getExpiry() {
        return this.expiry;
    }
    
}
