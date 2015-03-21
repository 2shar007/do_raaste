/**
 * Two Roads Hiring Challenge
 */
package in.co.tworoads.hiring.location;

/**
 * @author Tushar Ghosh
 *
 */
public class Location {
    
    private String name;
    
    /**
     * 
     */
    public Location(String locName) {
        this.name = locName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
