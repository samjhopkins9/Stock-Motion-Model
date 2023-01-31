import java.util.Scanner;
import java.util.ArrayList;

// Class reads user input and returns object with symbol and interval of desired security contained in Strings
public class UserInput {
    
    public String symbol;
    public String interval = "1min"; // interval is set to only be one minute because only one set of fake data is necessary for comparison, but can change interval in code if data for other intervals is placed in folder
    
    // Creates instance of UserInput, prompts the user to select 1 or 2 for SPY or FAKE, assigns the value of the symbol attribute according to the user's choice
    public UserInput(){
        
        Scanner userin = new Scanner(System.in);
        
        System.out.println("Select Symbol:");
        System.out.println("     1) SPY");
        System.out.println("     2) FAKE");
        
        int choice = userin.nextInt();
        
        if (choice == 1){
            this.symbol = "SPY";
        }
        
        else if (choice == 2){
            this.symbol = "FAKE";
        }
        
        // if invalid symbol is selected, constructor is called recursively until valid symbol is selected
        else {
            System.out.println(choice);
            System.out.println("Invalid choice. Please try again.");
            new UserInput();
        }
        
    } // end of constructor
    
} // end of UserInput class
