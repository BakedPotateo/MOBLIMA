package Customer;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{

    // Attributes

    private String email;
    private int mobileNumber;
    private String firstName;
    private String lastName;
    private ArrayList<Booking> bookings;

    /**
     * Class constructor of the Customer class
     * @param email Email address of the particular customer
     * @param mobileNumber Mobile number of the particular customer
     * @param firstName First name of the customer
     * @param lastName Last name of the customer
     * @param bookings List of all the bookings that this particular customer has made
     */

    public Customer(String email, int mobileNumber, String firstName, String lastName, ArrayList<Booking> bookings){
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookings = bookings;
    }

    // Public Methods

    /**
     * getter method to get the email address of the Customer
     * @return String Returns the email address of the Customer
     */

    public String getEmail(){
        return this.email;
    }

    /**
     * getter method to get the first name of the Customer
     * @return String Returns the first name of the Customer
     */

    public String getFirstName(){
        return this.firstName;
    }

    /**
     * getter method to get the last name of the Customer
     * @return String Returns the last name of the Customer
     */

    public String getLastName(){
        return this.lastName;
    }

    /**
     * getter method to get the list of bookings that the Customer has made
     * @return ArrayList<Booking> Returns the list of bookings that the Customer has made
     */

    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }

    /**
     * setter method to set the email address of the Customer
     * @param email The email address of the customer
     */

    public void setEmail(String email){
        this.email = email;
    }

    /**
     * setter method to set the first name of the Customer
     * @param firstName first name of the Customer
     */

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * setter method to set the last name of the Customer
     * @param lastName last name of the Customer
     */

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * setter method to set the all the bookings that the Customer has made
     * @param bookings list of bookings that the Customer has made
     */

    public void setBookings(ArrayList<Booking> bookings){
        this.bookings = bookings;
    }

    /**
     * Method to print out all the details of a particular instance of the Customer
     */

    public void makeString(){
        System.out.println("Name:  " + this.firstName + " " + this.lastName);
        System.out.println("Email: " + this.email);
        System.out.println("Mobile number: " + this.mobileNumber);
        System.out.println("Bookings: ");
        for(Booking booking : bookings)
            booking.makeString();
    }

}
