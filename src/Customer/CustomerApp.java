package Customer;

import java.util.Scanner;

public class CustomerApp{
    private Scanner sc = new Scanner(System.in);

    // Attributes

    /*
     * instance checks whether CustomerApp has been instantiated before, Static variable is the same between objects of the same class.
     */
    public static CustomerApp instance = null;

    /*
     * Empty class constructor
     */
    private CustomerApp() {}

    //public methods

    /*
     * getInstance checks if CustomerApp has been instantiated before.
     * If no previous instance was created, it creates a new one,
     * else it will use the original instance.
     */
    public static CustomerApp getInstance(){
        if(instance == null)
            instance = new CustomerApp();
        return instance;
    }
    
    // Customer main app to access the Moblima System
    public void displayCustomerMenu(){
        int choice;
        boolean exit = false;

        while(!exit){
            System.out.println("----------------- MOBLIMA CUSTOMER APP -----------------\n");
            System.out.println(" 1. View current movies\n" +
                               " 2. Search for a movie\n" +
                               " 3. View movie details\n" +
                               " 4. Book and purchase ticket\n" +
                               " 5. View booking history\n" +
                               " 6. List the Top 5 movies by ticket sales\n" +
                               " 7. List the Top 5 movies by overall reviewers' ratings\n" +
                               " 8. Submit a movie review\n" +
                               " 9. Exit");
            System.out.println("-------------------------------------------------------\n");            

            System.out.println("Please enter your choice:");

            /*
             * Check if input is an integer
            */
            while(!sc.hasNextInt()){
                System.out.println("Invalid input type. Please enter an integer value. ");
                sc.next(); //remove new line
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    System.out.println("Returning to the main page...\n");
                    exit = true;
                    break;
                default:
                     System.out.println("Please enter an integer between 1-9.\n");
                    break;
            
            }
        }

    }

}

        
            
            

      


    
