package Staff;

import java.util.Scanner;

public class Settings{
    // Attributes

    public Scanner sc = new Scanner(System.in);
    /*
     * instance checks whether Settings has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static Settings instance = null;

    /*
     * Empty class constructor
     */
    private Settings(){}

    // Public methods

    /*
     * getInstance checks if Settings has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static Settings getInstance()
    {
        if (instance == null)
            instance = new Settings(); // instance is a static variable
        return instance;
    }

    public void displaySettingsMenu(){
        int choice = 0;
        while(choice != 4){
            System.out.println("--------- SETTINGS MENU ---------\n"
                              +" 1. Configure movie showtimes\n"
                              +" 2. Configure ticket prices\n"
                              +" 3. Configure holidays\n"
                              +" 4. Exit\n"
                              +"---------------------------------\n");

            System.out.println("Please enter your choice:");

            /*
             * Check if input is an integer
             */
            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // remove newline
            }

            choice = sc.nextInt();
        }
    }
    public void configTicketPrices(){}

    public void configHolidays(){}


    
}
