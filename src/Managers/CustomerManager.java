package Managers;

import java.util.Scanner;

public class CustomerManager {
    // Attributes

    /*
     * instance checks whether CustomerManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static CustomerManager instance = null;

    public Scanner sc = new Scanner(System.in);
    /*
     * Empty class constructor
     */
    private CustomerManager(){}

    // Public methods

    /*
     * getInstance checks if CustomerManager has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static CustomerManager getInstance()
    {
        if (instance == null)
            instance = new CustomerManager(); // instance is a static variable
        return instance;
    }

    public void SearchMovie(){
        System.out.println("Please enter the movie title:");
        String title;
        while(!sc.hasNext()){
            System.out.println("Invalid input type. Please enter an integer value. ");
            sc.next(); //remove new line
        }
        title = sc.nextLine();
        MovieManager.getInstance().searchByTitle(title);
    }
    
    public void addReview(){
        System.out.println("Please enter the movie ID:");
        while(!sc.hasNextInt()){
            System.out.println("Invalid input type. Please enter an integer value. ");
            sc.next(); //remove new line
        }
        int id = sc.nextInt();
        System.out.println("Please enter your username:");
        while(!sc.hasNext()){
            System.out.println("Invalid input type. Please try again. ");
            sc.next(); //remove new line
        }
        String username = sc.nextLine();
        System.out.println("Please enter your review:");
        while(!sc.hasNext()){
            System.out.println("Invalid input type. Please try again. ");
            sc.next(); //remove new line
        }
        String comments = sc.nextLine();
        System.out.println("Please enter the number of stars for the review out of 5:");
        while(!sc.hasNextDouble()){
            System.out.println("Invalid input type. Please try again. ");
            sc.next(); //remove new line
        }
        double noOfStars = sc.nextDouble();

        MovieManager.getInstance().addReviewMovieUsingId(id, username, noOfStars, comments);
        System.out.println("Review added!\n");
    }
}
