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

    public Booking(){}

    public Booking(Movie movie, Showtime showtime, ArrayList<Ticket> tickets, String transactionID){
        this.movie = movie;
        this.showtime = showtime;
        this.tickets = tickets;
        this.transactionID = transactionID;
    }

    public Movie getMovie(){
        return this.movie;
    }

    public Showtime getShowtime(){
        return this.showtime;
    }

    public ArrayList<Ticket> getTickets(){
        return this.tickets;
    }

    public String getTransactionID(){
        return this.transactionID;
    }

    public void makeString(){
        System.out.println("Booking reference: " + this.getTransactionID());
        System.out.println("Movie title: " + movie.getTitle());
        showtime.makeString();
        for(Ticket ticket : tickets){
            ticket.makeString();
        }
    }
}
