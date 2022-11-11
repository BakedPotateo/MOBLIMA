package Managers;

import java.util.ArrayList;

import Customer.Booking;
import Customer.Customer;
import Movies.Movie;
import Tickets.Ticket;

public class TransactionManager {
    public static TransactionManager instance = null;

    /*
     * Empty class constructor
     */
    private TransactionManager(){}
    
    // Public methods

    /*
     * getInstance checks if TransactionManager has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static TransactionManager getInstance()
    {
        if (instance == null)
            instance = new TransactionManager(); // instance is a static variable
        return instance;
    }

    public void transaction(Customer customer){
        ArrayList<Booking> bookings = customer.getBookings();

        int latestBookingIndex = bookings.size() - 1;
        Booking latestBooking = bookings.get(latestBookingIndex);

        Movie m = latestBooking.getMovie();
        ArrayList<Ticket> tickets = latestBooking.getTickets();

        double totalPrice = 0;
        for(Ticket t : tickets){
            t.makeString();
            totalPrice += t.getTicketPrice();
        }

        System.out.printf("Total ticket price: $%5.2f\n", totalPrice);
        System.out.println("Transaction successful! An email with your ticket details has been sent to the email account " + customer.getEmail() +".");

        m.setSales(totalPrice);
        MovieManager.getInstance().removeMovieById(m.getId());
        MovieManager.getInstance().createNewMovie(m);
    }
}
