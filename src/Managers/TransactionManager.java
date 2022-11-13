package Managers;

import java.util.ArrayList;

import Customer.Booking;
import Customer.Customer;
import Movies.Movie;
import Tickets.Ticket;

public class TransactionManager {
    /**
     * instance checks whether TransactionManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static TransactionManager instance = null;

    /**
     * Empty class constructor
     */
    private TransactionManager(){}
    
    // Public methods

    /**
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

    /**
     * Method to create transaction for a customer
     * @param customer Customer to create transaction for
     * For the purposes of this project, all transactions are assumed to be successful
     * Transactions also add to total sales of movie
     */
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
        System.out.println("Reference code: " + latestBooking.getTransactionID());
        double currentSales = m.getSales();
        m.setSales(totalPrice + currentSales);
        MovieManager.getInstance().removeMovieById(m.getId());
        MovieManager.getInstance().createNewMovie(m);
    }
}
