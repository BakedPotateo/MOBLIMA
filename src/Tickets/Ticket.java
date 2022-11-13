package Tickets;

import java.io.Serializable;
import Cinema.Seat;
/**
 * Ticket class holds all the information about different types of tickets
 */

public class Ticket implements Serializable{
    // Attributes
    private Seat seat;
    private String ticketType;
    private String movieType;
    private double ticketPrice;
    
    // Constructors
    public Ticket(){};

    public Ticket(String ticketType, String movieType, double ticketPrice) {
        this.ticketType = ticketType;
        this.movieType = movieType;
        this.ticketPrice = ticketPrice;
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

    public double getTicketPrice(){
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    public void makeString() {
        System.out.printf("Ticket Type: %-25s MovieType: %s     Ticket Price: $%5.2f\n", getTicketType(), getMovieType(), getTicketPrice());
    }

    public void makeStringCustomer(){
        System.out.printf("Seat: %s\nTicket Type: %-30s\nTicket Price: $%5.2f\n", getSeat().getSeatName(), getTicketType(), getTicketPrice());
    }

    
}
