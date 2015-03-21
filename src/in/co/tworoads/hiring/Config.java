/**
 * 
 */
package in.co.tworoads.hiring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tushar Ghosh
 * 
 */
public class Config {
    
    static {
        conf = new Config();
    }
    
    private static Config conf;
    
    private int seed;
    
    private String start_t;
    
    private String curr_t;// prev_t
    
    private SimpleDateFormat timeStampParser;
    
    /**
     * 
     */
    private Config() {
        timeStampParser = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    }
    
    public static Config getInstance() {
        if (conf == null) {
            conf = new Config();
        }
        return conf;
    }
    
    public void setSeed(String init) {
        this.seed = Integer.valueOf(init);
    }
    
    public void setStart_t(String t) {
        this.start_t = t;
        setCurr_t(start_t);
    }
    
    public void setCurr_t(String t) {
        this.curr_t = t;
    }
    
    /**
     * @return the seed
     */
    public int getSeed() {
        return seed;
    }
    
    /**
     * @return the start_t
     */
    public String getStart_t() {
        return start_t;
    }
    
    /**
     * @return the curr_t
     */
    public String getCurr_t() {
        return curr_t;
    }
    
    /**
     * @return the curr_t
     */
    public String getCurrDate() {
        return curr_t.split("[\\s]+")[0].trim();
    }

    public String getTime(long t) {
        return timeStampParser.format(new Date(t));
    }
    
    public long getTime(String t) {
        long tt = 0;
        try {
            tt = timeStampParser.parse(t).getTime();
        } catch (ParseException e) {
            System.out
                    .println("Error in calculating time representation. Exiting");
            System.exit(0);
        }
        return tt;
    }
    
    public long getDuration(String t1, String t2) {
        long begin = 0, end = 0;
        try {
            begin = timeStampParser.parse(t1).getTime();
            end = timeStampParser.parse(t2).getTime();
        } catch (Exception e) {
            System.out.println("Error in calculating time difference. Exiting");
            System.exit(0);
        }
        
        long diff = (end - begin) / 86400000;
        return diff;
    }
    
}
