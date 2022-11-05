package Cinema;

import java.io.Serializable;

public class Seat implements Serializable {
    private int seatId;    
    private boolean occupied = false;

    public Seat(int seatId) {
        this.seatId = seatId;
    }

    public Seat(int seatId, boolean occupied) {
        this.seatId = seatId;
        this.occupied = occupied;
    }

    public int getSeatId() {
        return seatId;
    } 

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean getSeatStatus() {
        return occupied;
    }

    public void assign() {
        occupied = true;
    }
    
    public void unassign() {
        occupied = false;
    }
}
