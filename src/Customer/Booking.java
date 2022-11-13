package Customer;

import java.io.Serializable;
import java.util.ArrayList;

import Movies.Movie;
import Movies.Showtime;
import Tickets.Ticket;

public class Booking implements Serializable {

    // Attributes
    
    private Movie movie;
    private Showtime showtime;
    private ArrayList<Ticket> tickets;
    private String transactionID;

    /**
     * Empty Class Contructor of the Booking class
     */

    public Booking(){}

    /**
     * Class constructor of the Booking class
     * @param movie Movie that this Booking corresponds to
     * @param showtime Showtime of the movie of this booking
     * @param tickets List of tickets that corresponds to this booking
     * @param transactionID The transaction ID of the booking
     */

    public Booking(Movie movie, Showtime showtime, ArrayList<Ticket> tickets, String transactionID){
        this.movie = movie;
        this.showtime = showtime;
        this.tickets = tickets;
        this.transactionID = transactionID;
    }

    // Public Methods

    /**
     * getter method to get the Movie that this Booking corresponds to 
     * @return Movie The movie that this booking corresponds to 
     */

    public Movie getMovie(){
        return this.movie;
    }

    /**
     * getter method to get the showtime of the Movie that this booking corresponds to
     * @return Showtime of the movie that this booking corresponds to
     */

    public Showtime getShowtime(){
        return this.showtime;
    }

    /**
     * getter method to get the list of Tickets that corresponds to this Booking
     * @return ArrayList<Ticket> List of the Tickets that corresponds to this Booking
     */

    public ArrayList<Ticket> getTickets(){
        return this.tickets;
    }

    /**
     * getter method to get the transaction ID of this Booking
     * @return String The transaction ID of the Booking
     */

    public String getTransactionID(){
        return this.transactionID;
    }

    /**
     * Method to print out the specific details of the instance of the Booking that is being made
     */

    public void makeString(){
        System.out.println("Booking reference: " + this.getTransactionID());
        System.out.println("Movie title: " + movie.getTitle());
        showtime.makeString();
        for(Ticket ticket : tickets){
            ticket.makeStringCustomer();
        }
    }
}
