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
    
    /**
     * Empty constructor for the Ticket class
     */

    public Ticket(){};

    /**
     * Constructor for the Ticket class
     * @param ticketType Type of Ticket 
     * @param movieType Type of movie that corresponds to the Ticket 
     * @param ticketPrice Price of the ticket 
     */
    
    public Ticket(String ticketType, String movieType, double ticketPrice) {
        this.ticketType = ticketType;
        this.movieType = movieType;
        this.ticketPrice = ticketPrice;
    }  
    
    // Public Methods

    /**
     * getter method to get the Seat the Ticket corresponds to
     * @return Seat Returns the Seat the Ticket corresponds to
     */

    public Seat getSeat() {
        return seat;
    }

    /**
     * setter method ot set the Seat the Ticket corresponds to
     * @param seat the Seat the ticket corresponds to
     */

    public void setSeat(Seat seat) {
        this.seat = seat;
    } 

    /**
     * getter method to get the type of Ticket
     * @return String Returns the Type of Ticket
     */

    public String getTicketType() {
        return ticketType;
    }

    /**
     * setter method to set the type of Ticket
     * @param ticketType The type of Ticket
     */

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * getter method to get the type of Movie the Ticket corresponds to
     * @return String Returns the type of Movie the Ticket corresponds to
     */

    public String getMovieType() {
        return movieType;
    }

    /**
     * setter method to set the type of Movie the Ticket corresponds to
     * @param movieType Type of Movie that the Ticket corresponds to
     */

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    /**
     * getter method to get the price of the Ticket
     * @return double Price of the Ticket
     */

    public double getTicketPrice(){
        return ticketPrice;
    }

    /**
     * setter method to set the price of the Ticket
     * @param ticketPrice The price of the Ticket
     */

    public void setTicketPrice(double ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    /**
     * Method to print out the details of the Ticket
     */

    public void makeString() {
        System.out.printf("Ticket Type: %-25s MovieType: %s     Ticket Price: $%5.2f\n", getTicketType(), getMovieType(), getTicketPrice());
    }


    /**
     * Method to print out the details of the ticket that is important to the Customer
     */

    public void makeStringCustomer(){
        System.out.printf("Seat: %s\nTicket Type: %-30s\nTicket Price: $%5.2f\n", getSeat().getSeatName(), getTicketType(), getTicketPrice());
    }

    
}
