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

    public Booking(){}

    public Booking(Movie movie, Showtime showtime, ArrayList<Ticket> tickets){
        this.movie = movie;
        this.showtime = showtime;
        this.tickets = tickets;
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

    public void makeString(){
        System.out.println("Movie title: " + movie.getTitle());
        showtime.makeString();
        for(Ticket ticket : tickets){
            ticket.makeString();
        }
    }
}
