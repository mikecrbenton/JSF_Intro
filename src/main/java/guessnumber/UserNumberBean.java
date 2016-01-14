
package guessnumber;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author Mike Benton
 */
@ManagedBean(name="userNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable {

    Integer randomInt;
    Integer userNumber;
    String response;
    String message = "";
    String description;          //FOR getDescription() METHOD
    Integer playCount = 0;       //KEEPS TRACK OF HOW MANY GAMES (FOR AVERAGING)
    Integer userGuessCount = 0;  //KEEPS TRACK OF HOW MANY USER GUESSES
    
    List<Integer> rndIntList = new ArrayList<>();  //HOLDS MEMORY OF RANDOM NUMBERS
    RandomCount[] randomCountList = new RandomCount[10];
    
    public UserNumberBean() {
    }
    
    public void generateRandomNumber(){
        
        try{
            Random randomGR = new Random();
            randomInt = new Integer(randomGR.nextInt(10));
            System.out.println("Random number generated is: " + randomInt);
            
            //NEW FUNCTIONALITY FOR ASSIGNMENT----------------------------------
            rndIntList.add(randomInt);  //ADD EACH NEW RANDOM INTEGER TO A LIST
            playCount++;                //INCREMENT FOR EACH NEW GAME STARTED
            //------------------------------------------------------------------
            
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect("greeting.xhtml");
            
        }catch(IOException e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }   
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
        //EACH TIME A USER GUESSES, INCREMENT BY 1
        userGuessCount++;            
    }
    
    /** 
     * getDescription   receives no parameters and produces a List<> of 10 Strings
     *                  with an incremented count
     * 
     * @return          ArrayList of Strings 
     */
    public List<String> getDescription(){
        
        List<String> descriptionList = new ArrayList<>();
            
            for(int i=0; i<=10 ; i++){
                description = "The number of random "+i+" is:";
                descriptionList.add(description);
            }
            return descriptionList;
    }  
    
    /**
     * getTheResults    creates a List<Integer> and populates it by incrementing
     *                  the tally of the random number (1-10) produced each time
     *                  the game is played
     * 
     * @return          a List<Integer> of 10 Integers, holding the tallies of each
     *                  random number that was produced playing the game
     */
    public List<Integer> getTheResults(){
        
        //ARRAYLIST IS TO HOLD A COUNT OF EACH RANDOM NUMBER GENERATED
        List<Integer> listOfEachInteger = new ArrayList<>();
        
        //VARIABLES TO INCREMENT FOR EACH USER GUESS
        Integer zero = 0;
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        Integer six = 0;
        Integer seven = 0;  
        Integer eight = 0;
        Integer nine = 0;
        Integer ten = 0;
        
        for(int i=0 ; i < rndIntList.size() ; i++){
            
            if(rndIntList.get(i) == 0){
                zero++;
            }
            if(rndIntList.get(i) == 1){   
                one++;
            }
            if(rndIntList.get(i) == 2){
                two++;
            }
            if(rndIntList.get(i) == 3){
                three++;
            }
            if(rndIntList.get(i) == 4){
                four++;
            }
            if(rndIntList.get(i) == 5){
                five++;
            }
            if(rndIntList.get(i) == 6){
                six++;
            }
            if(rndIntList.get(i) == 7){
                seven++;
            }
            if(rndIntList.get(i) == 8){
                eight++;
            }
            if(rndIntList.get(i) == 9){
                nine++;
            }
            if(rndIntList.get(i) == 10){    
                ten++;
            }
        }
        
        listOfEachInteger.add(0, zero);
        listOfEachInteger.add(1, one);
        listOfEachInteger.add(2, two);
        listOfEachInteger.add(3, three);
        listOfEachInteger.add(4, four);
        listOfEachInteger.add(5, five);
        listOfEachInteger.add(6, six);
        listOfEachInteger.add(7, seven);
        listOfEachInteger.add(8, eight);
        listOfEachInteger.add(9, nine);
        listOfEachInteger.add(10, ten);  
        
        return listOfEachInteger;
    }
    /**
     * getRandomCount   calls methods getTheResults() and getDescription() and 
     *                  returns an Array of RandomCount Objects
     * 
     * @return          array of RandomCount Objects with fields initialized 
     */
    public RandomCount[] getRandomCountList(){
        
        List<Integer> number =  this.getTheResults();                        
        List<String> descrip =  this.getDescription();                         
          
        for(int i=0; i<10 ; i++){
            
            randomCountList[i]= new RandomCount(descrip.get(i),number.get(i));    
        }
        return randomCountList;
    }
    
    /**
     * getTheAverage    receives no parameters and returns the average number of
     *                  user guesses for games played
     * 
     * @return          average number of user guesses as an Integer
     */
    
    public Integer getTheAverage(){
        
        Integer average;
        
        if(playCount==0){
            return 0;
        }
        
        average = userGuessCount/playCount;
        return average;
    }
    
    public Integer getTotalGames(){
        return playCount;
    }
  
    public String getResponse() {
        try{
            //Integer is an Object, use equals() not ==
            if(userNumber != null &&userNumber.equals(randomInt)){ 
                
                //REDIRECT THE USER BACK TO INDEX.XHTML IF NUMBER IS CORRECT TO PLAY AGAIN
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();
                ec.redirect("index.xhtml");   
                
                this.message = "Yay, you guessed it!";
                response = "";
                return response;
            }
            
            response = "Sorry, " + userNumber + " isn't correst. Guess again.";
        }catch (Exception e){
            response = "Unable to redirect page."; //Example of using a return variable in a catch block
        }
        return response;
    }

    public String getMessage() {
        return message;
    }
    
    
    
    
}
