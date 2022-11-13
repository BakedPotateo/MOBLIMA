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

    public Customer(String email, int mobileNumber, String firstName, String lastName, ArrayList<Booking> bookings){
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookings = bookings;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setBookings(ArrayList<Booking> bookings){
        this.bookings = bookings;
    }

    public void makeString(){
        System.out.println("Name:  " + this.firstName + " " + this.lastName);
        System.out.println("Email: " + this.email);
        System.out.println("Mobile number: " + this.mobileNumber);
        System.out.println("Bookings: ");
        for(Booking booking : bookings)
            booking.makeString();
    }

}
