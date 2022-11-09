package Managers;

import java.util.Scanner;

import utils.ProjectRootPathFinder;

public class BookingManager {
    // Attributes

    /*
     * instance checks whether BookingManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static BookingManager instance = null;

    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Customer/customer.txt";


    
    public Scanner sc = new Scanner(System.in);
    /*
     * Empty class constructor
     */
    private BookingManager(){}

    // Public methods

    /*
     * getInstance checks if BookingManager has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static BookingManager getInstance()
    {
        if (instance == null)
            instance = new BookingManager(); // instance is a static variable
        return instance;
    }

    public void bookingMenu(){
        int choice = 0;
        while(choice != 3){
            System.out.println("------- BOOKING MENU -------\n"
                              +" 1. Book by title\n"
                              +" 2. Book by ID\n"
                              +" 3. Exit\n"
                              +"----------------------------");
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
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3.\n");
                    break;
            }
        }
    }

    private void bookByID(){

    }
}
