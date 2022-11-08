package Managers;

import java.util.ArrayList;
import java.util.Scanner;

import Tickets.Ticket;

public class SettingsManager {
    // Attributes

    private Scanner sc = new Scanner(System.in);
    /*
     * instance checks whether MovieManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static SettingsManager instance = null;

    /*
     * Empty class constructor
     */
    private SettingsManager(){}

    // Public methods

    /*
     * getInstance checks if SettingsManager has been instantiated before. 
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
                    this.editTicketsMenu();
                    break;
                case 2:
                    this.editHolidaysMenu();
                    break;
                case 3:
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3");
                    break;
            }
        }
    }

    public void editTicketsMenu(){
        int choice = 0;
        while(choice != 4){
            System.out.println("--------- TICKETS MENU ---------\n"
                              +" 1. Edit 2D ticket prices\n"
                              +" 2. Edit 3D ticket prices\n"
                              +" 3. Show all ticket prices\n"
                              +" 4. Exit\n"
                              +"--------------------------------\n");

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
                    this.edit2DTickets();
                    break;
                case 2:
                    this.edit3DTickets();
                    break;
                case 3:
                    TicketManager.getInstance().printAllTickets();
                    break;
                case 4:
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3");
                    break;
            }
        }
    }

    private void edit2DTickets(){
        int choice = 0;
        while(choice != 9){
            System.out.println("-------- 2D TICKETS MENU ---------\n"
                              +" 1. Ticket Type: Senior Citizens\n"
                              +" 2. Ticket Type: Students\n"
                              +" 3. Ticket Type: Mon - Wed\n"
                              +" 4. Ticket Type: Thu\n"
                              +" 5. Ticket Type: Fri (Before 6pm)\n"
                              +" 6. Ticket Type: Sat & Sun\n"
                              +" 7. Ticket Type: Public holiday\n"
                              +" 8. Show all 2D tickets\n"
                              +" 9. Exit\n"         
                              +"----------------------------------\n");

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
                    TicketManager.getInstance().editTicket("Senior Citizens (Mon - Fri Before 6pm)", false);
                    break;
                case 2:
                    TicketManager.getInstance().editTicket("Students (Mon - Fri Before 6pm)", false);
                    break;
                case 3:
                    TicketManager.getInstance().editTicket("Mon - Wed", false);
                    break;
                case 4:
                    TicketManager.getInstance().editTicket("Thu", false);
                    break;
                case 5:
                    TicketManager.getInstance().editTicket("Fri (Before 6pm)", false);
                    break;
                case 6:
                    TicketManager.getInstance().editTicket("Sat & Sun", false);
                    break;
                case 7:
                    TicketManager.getInstance().editTicket("Public holiday", false);
                    break;
                case 8:
                    ArrayList<Ticket> tickets2D = TicketManager.getInstance().get2DMovies();
                    for(Ticket ticket : tickets2D) ticket.makeString();
                    break;
                case 9:
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-9\n");
                    break;
            }
        }
    }

    private void edit3DTickets(){
        int choice = 0;
        while(choice != 9){
            System.out.println("-------- 3D TICKETS MENU ---------\n"
                              +" 1. Ticket Type: Senior Citizens\n"
                              +" 2. Ticket Type: Students\n"
                              +" 3. Ticket Type: Mon - Wed\n"
                              +" 4. Ticket Type: Thu\n"
                              +" 5. Ticket Type: Fri (Before 6pm)\n"
                              +" 6. Ticket Type: Sat & Sun\n"
                              +" 7. Ticket Type: Public holiday\n"
                              +" 8. Show all 3D tickets\n"
                              +" 9. Exit\n"      
                              +"----------------------------------\n");

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
                    TicketManager.getInstance().editTicket("Senior Citizens (Mon - Fri Before 6pm)", true);
                    break;
                case 2:
                    TicketManager.getInstance().editTicket("Students (Mon - Fri Before 6pm)", true);
                    break;
                case 3:
                    TicketManager.getInstance().editTicket("Mon - Wed", true);
                    break;
                case 4:
                    TicketManager.getInstance().editTicket("Thu", true);
                    break;
                case 5:
                    TicketManager.getInstance().editTicket("Fri (Before 6pm)", true);
                    break;
                case 6:
                    TicketManager.getInstance().editTicket("Sat & Sun", true);
                    break;
                case 7:
                    TicketManager.getInstance().editTicket("Public holiday", true);
                    break;
                case 8:
                    ArrayList<Ticket> tickets3D = TicketManager.getInstance().get3DMovies();
                    for(Ticket ticket : tickets3D) ticket.makeString();
                    break;
                case 9:
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-9\n");
                    break;
            }
        }
    }

    public void editHolidaysMenu(){
        int choice = 0;
        while(choice != 4){
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
                    HolidayManager.getInstance().addHoliday();
                    break;
                case 2:
                    HolidayManager.getInstance().deleteHoliday();
                    break;
                case 3:
                    HolidayManager.getInstance().printHolidays();
                    break;
                case 4:
                    System.out.println("Exiting...\n");
                default:
                    System.out.println("Please enter an integer between 1-4\n");
            }
        }
    }
}
