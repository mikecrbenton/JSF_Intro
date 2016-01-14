

package guessnumber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mike Benton CSC470
 */
public class RandomCount extends UserNumberBean {
    
    String printDescription;
    Integer timesGuessed;
    RandomCount[] iterator = new RandomCount[10];
    
    public RandomCount(String description, Integer timesGuessed){
        this.printDescription = description;
        this.timesGuessed = timesGuessed;
    }

    public String getPrintDescription() {
        return printDescription;
    }

    public Integer getTimesGuessed() {
        return timesGuessed;
    }

    public RandomCount[] getIterator() {
        return iterator;
        
        
    }
    
    
    
    
    
}
