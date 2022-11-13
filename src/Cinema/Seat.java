package Cinema;

import java.io.Serializable;

public class Seat implements Serializable {

    // Attributes

    private int seatId; 
    private String seatName;   
    private boolean occupied = false;

    /**
     * Class constructor for the Seat Class with only the seat ID being initialized
     * @param seatId The ID of the seat 
     */

    public Seat(int seatId) {
        this.seatId = seatId;
    }

    /**
     * Class constructor for the Seat Class with both the seat ID and availability of the seat being 
     * initialized
     * @param seatId The ID of the seat
     * @param occupied If the seat if available to be booked or not
     */

    public Seat(int seatId, boolean occupied) {
        this.seatId = seatId;
        this.occupied = occupied;
    }

    /**
     * getter method to get the ID of the Seat
     * @return int Returns the Seat ID
     */

    public int getSeatId() {
        return seatId;
    } 

    /**
     * setter method to set the ID of the Seat
     * @param seatId The ID of the seat
     */

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    /**
     * getter method to get the name of the Seat
     * @return String Returns the name of the seat
     */

    public String getSeatName() {
        return seatName;
    } 

    /**
     * setter method to set the name of the Seat
     * @param seatName The name of the Seat
     */

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    /**
     * getter method to get the availability of the Seat
     * @return boolean Returns the availability of the seat(True = Available, False = Not Available)
     */

    public boolean getSeatStatus() {
        return occupied;
    }

    /**
     * Method to assign a seat to a particular customer when they purchase a ticket and the seat becomes
     * unavailable to others 
     */

    public void assign() {
        occupied = true;
    }

    /**
     * Method to unassign a seat from a particular customer and the seat becomes available to others again
     */
    
    public void unassign() {
        occupied = false;
    }
}
