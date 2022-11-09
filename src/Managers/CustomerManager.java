package Managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Customer.Booking;
import Customer.Customer;
import utils.ProjectRootPathFinder;

public class CustomerManager {
    // Attributes

    /*
     * instance checks whether CustomerManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static CustomerManager instance = null;

    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Customer/customer.txt";

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

    @SuppressWarnings("unchecked")
    public ArrayList<Customer> read() {
        try {
            ObjectInputStream objectinputstream = new ObjectInputStream(new FileInputStream(FILE));
            ArrayList<Customer> customerList = (ArrayList<Customer>) objectinputstream.readObject();
            objectinputstream.close();
            return customerList;
        } catch (ClassNotFoundException | IOException e) {}
        return new ArrayList<Customer>();
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

    public Customer getCustomer(String email){
        ArrayList<Customer> customers = this.read();
        for(Customer c : customers){
            if(c.getEmail().equals(email)) return c;
        }
        return null;
    }

    public Customer createCustomer(String email, ArrayList<Booking> bookings){
        System.out.println("Please enter your first name:");
        while(!sc.hasNext()){
            System.out.println("Invalid input type. Please try again. ");
            sc.next(); //remove new line
        }
        String firstName = sc.nextLine();

        System.out.println("Please enter your last name:");
        while(!sc.hasNext()){
            System.out.println("Invalid input type. Please try again. ");
            sc.next(); //remove new line
        }
        String lastName = sc.nextLine();

        Customer c = new Customer(email, firstName, lastName, bookings);
        ArrayList<Customer> data = this.read();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            data.add(c);
            output.writeObject(data);
            output.flush();
            output.close();
        } catch (IOException e) {}
        
        return c;
    }
}
