package Tickets;

import java.io.Serializable;
import Cinema.Seat;
import Movies.Movie;
/*
 * Ticket class holds all the information about different types of tickets
 */

public class Ticket implements Serializable{
    // Attributes
    private Seat seat;
    private String ticketType;
    private String movieType;
    private String[] ticketTypes = {"Senior Citizens (Mon - Fri Before 6pm)",
                                    "Students (Mon - Fri Before 6pm)",
                                    "Mon - Wed",
                                    "Thu",
                                    "Fri (Before 6pm)",
                                    "Fri (After 6pm)",
                                    "Sat & Sun",
                                    "Public holiday"};

    private double[] ticketPrices = {4.00, 7.00, 8.50, 9.50, 9.50, 11.00, 11.00, 12.00};
    private double[] ticketPrices3D = {9.00, 9.00, 11.00, 11.00, 15.00, 15.00, 15.00, 16.00};
    
    // Constructors
    public Ticket(){};

    public Ticket(String ticketType, String movieType) {
        this.ticketType = ticketType;
        this.movieType = movieType;
    }    

    // Methods
    public double getPrice() {
        if (this.movieType.equals("2D"))
            for (int i=0; i<ticketTypes.length; i++) {
                if (this.ticketType.equals(ticketTypes[i]))
                    return ticketPrices[i];
            }

        else if (this.movieType.equals("3D")) {
            for (int i=0; i<ticketTypes.length; i++) 
                if (this.ticketType.equals(ticketTypes[i]))
                    return ticketPrices3D[i];
        }
        
        return -1;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    } 

    public String getTicketType() {
        return ticketType;
    }
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    public String getMovieType() {
        return movieType;
    }
    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public void makeString() {
        System.out.printf("Ticket Type: %-40s MovieType: %s Ticket Price: $%5.2f\n", getTicketType(), getMovieType(), getPrice());
    }

    
}
