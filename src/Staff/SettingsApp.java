package Staff;

import java.util.Scanner;

import Managers.SettingsManager;

public class SettingsApp{
    // Attributes

    public Scanner sc = new Scanner(System.in);
    /*
     * instance checks whether Settings has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static SettingsApp instance = null;

    /*
     * Empty class constructor
     */
    private SettingsApp(){}

    // Public methods

    /*
     * getInstance checks if Settings has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static SettingsApp getInstance()
    {
        if (instance == null)
            instance = new SettingsApp(); // instance is a static variable
        return instance;
    }

    public void displaySettingsMenu(){
        int choice = 0;
        while(choice != 3){
            System.out.println("--------- SETTINGS MENU ---------\n"
                              +" 1. Configure ticket prices\n"
                              +" 2. Configure holidays\n"
                              +" 3. Exit\n"
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
            switch(choice){
                case 1:
                    SettingsManager.getInstance().editTicketsMenu();
                    break;
                case 2:
                    SettingsManager.getInstance().editHolidaysMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                default:
                    System.out.println("Please enter an integer between 1-3");
            }
        }
    }


    
}
