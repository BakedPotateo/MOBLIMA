import java.util.*;
import Staff.StaffApp;
import Customer.CustomerApp;
public class MainApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while(choice != 3){
            System.out.println("Welcome to MOBLIMA!\n");

            System.out.println("Please enter your choice:");
            System.out.println("1. Customer\n2. Staff\n3. Exit\n");

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
                    // Customer app
                    System.out.println("Customer app");
                    CustomerApp.getInstance().displayCustomerMenu();
                    break;
                case 2:
                    // Staff app
                    System.out.println("Staff app");
                    StaffApp.getInstance().displayStaffLogin();
                    break;
                case 3:
                    System.out.println("Thank you for using MOBLIMA!");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3.");
                    break;
            }
        }
        sc.close();
    }
}
