package Managers;

import java.util.Scanner;

public class SettingsManager{
    // Attributes

    public Scanner sc = new Scanner(System.in);
    /*
     * instance checks whether Settings has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static SettingsManager instance = null;

    /*
     * Empty class constructor
     */
    private SettingsManager(){}

    // Public methods

    /*
     * getInstance checks if Settings has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static SettingsManager getInstance()
    {
        if (instance == null)
            instance = new SettingsManager(); // instance is a static variable
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
                    this.configTicketPrices();
                    break;
                case 2:
                    this.configHolidays();
                    break;
                case 3:
                    System.out.println("Exiting...");
                default:
                    System.out.println("Please enter an integer between 1-3");
            }
        }
    }

    private void configTicketPrices(){}

    private void configHolidays(){
        int choice = 0;
        while(choice != 3){
            System.out.println("--------- HOLIDAYS MENU ---------\n"
                              +" 1. Add holiday\n"
                              +" 2. Delete holiday\n"
                              +" 3. Show all holidays\n"
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
            switch(choice){
                case 1:
                    // add holiday
                    break;
                case 2:
                    // delete holiday
                    break;
                case 3:
                    // show holidays
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3");
            }
        }
    }


    
}
