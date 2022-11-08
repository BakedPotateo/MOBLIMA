package Managers;

import java.util.Scanner;

import utils.ProjectRootPathFinder;

public class BookingManager {
    // Attributes

    /*
     * instance checks whether BookingManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static BookingManager instance = null;

    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Cineplex/cineplexes.txt";


    
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

    public void bookSeat(){

    }
}
