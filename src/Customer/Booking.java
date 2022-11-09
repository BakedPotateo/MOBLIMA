package Customer;

import java.util.ArrayList;

import Movies.Movie;
import Movies.Showtime;
import Tickets.Ticket;

public class Booking {
    // Attributes
    private Movie movie;
    private Showtime showtime;
    private ArrayList<Ticket> tickets;

    public void makeString(){
        System.out.println("Movie title: " + movie.getTitle());
        showtime.makeString();
        for(Ticket ticket : tickets){
            ticket.makeString();
            System.out.println();
        }
    }
}
