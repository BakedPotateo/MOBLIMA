package Managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import utils.ProjectRootPathFinder;

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

    public final static String TICKETS_FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Tickets/tickets.txt";
    public final static String HOLIDAY_FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Settings/holidays.txt";

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

    @SuppressWarnings("unchecked")
    public ArrayList<String> read() {
        try {
            ObjectInputStream objectinputstream = new ObjectInputStream(new FileInputStream(TICKETS_FILE));
            ArrayList<String> movieList = (ArrayList<String>) objectinputstream.readObject();
            objectinputstream.close();
            return movieList;
        } catch (ClassNotFoundException | IOException e) {}
        return new ArrayList<String>();
    }

    public void editTicketsMenu(){}

    public void editHolidaysMenu(){
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
