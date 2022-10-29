package Staff;

import java.util.Scanner;

public class StaffApp {
    private Scanner sc = new Scanner(System.in);

    // Attributes

    /*
     * instance checks whether StaffApp has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static StaffApp instance = null;

    /*
     * Empty class constructor
     */
    private StaffApp(){}

    // Public methods

    /*
     * getInstance checks if StaffApp has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static StaffApp getInstance()
    {
        if (instance == null)
            instance = new StaffApp(); // instance is a static variable
        return instance;
    }

    public void displayStaffLogin(){
        int choice;
        boolean LoggedIn = false;
        boolean exit = false;

        while(!LoggedIn && !exit){
            System.out.println("--- MOBLIMA STAFF APP ---\n");
            System.out.println(" 1. Login\n 2. Exit\n");
            System.out.println("-------------------------\n");

            System.out.println(" Please enter your choice:");

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
                    // Login

                    /*
                     * Fill in code for user credentials here
                     */
                    System.out.println("Login");
                    displayStaffLoggedIn();
                    LoggedIn = true;
                    break;
                case 2:
                    System.out.println("Returning to main page...\n");
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter an integer between 1-2.");
                    break;
            }
        }
    }

    // Private methods

    private void displayStaffLoggedIn(){

    }
}
