package Staff;

import java.util.Scanner;

import Managers.MovieManager;
import Managers.SettingsManager;
import Managers.StaffManager;
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
            System.out.println("--- MOBLIMA STAFF APP ---\n"
                              +" 1. Login\n"
                              +" 2. Exit\n"
                              +"-------------------------\n");

            System.out.println("Please enter your choice:");

            /*
             * Check if input is an integer
             */
            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // remove newline
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter Username: ");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String username = sc.nextLine();

                    System.out.println("Enter Password: ");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String password = sc.nextLine();
                    
                    boolean auth = StaffManager.getInstance().login(username, password);

                    if(auth){
                        LoggedIn = true;
                        this.displayStaffLoggedIn();
                        exit = true;
                    }
                    else {
                        System.out.println("Invalid Username or Password, please try again.");
                    }
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
        int choice = 0;
        while(choice != 4){
            System.out.println("----- MOBLIMA STAFF APP -----\n"
                              +" 1. Display top 5 movies\n"
                              +" 2. Configure movies\n"
                              +" 3. Configure system settings\n"
                              +" 4. Exit\n"
                              +"-----------------------------\n");

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
                    // Display top 5 movies
                    System.out.println("Top 5 movies");
                    MovieManager.getInstance().showTop5();
                    break;
                case 2:
                    System.out.println("Configure movies (add movie etc.)");
                    MovieManager.getInstance().MovieMenuStaff();
                    break;
                case 3:
                    System.out.println("Configure system settings (ticket prices etc.)");
                    SettingsManager.getInstance().displaySettingsMenu();
                    break;
                case 4:
                    System.out.println("Returning to main page...\n");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-4.");
                    break;
            }
        }
    }
}
