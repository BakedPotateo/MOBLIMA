package Cinema;

import java.io.Serializable;

public class SeatingLayout implements Serializable {

    // Attributes

    private Seat[][] layout;
    private int row;
    private int column;

    // Public Methods

    /**
     * Class Constructor for the Seating Layout of the Cinema. A 2D array of the seats are also initialized
     * based on the the number of rows and columns the Cinema has.
     * @param row The number of rows of seats in that particular Cinema
     * @param column The number of columns of seats in that particular Cinema 
     */

    public SeatingLayout(int row, int column) {
        this.row = row;
        this.column = column;
        layout = new Seat [row][column];
        for (int i=0; i<row; i++) {
            for (int j=0; j<column; j++) {
                layout[i][j] = new Seat(i * this.row + j);
            }
        }
    }

    /**
     * Method to print out the Seating Layout of the Cinema
     */

    public void printLayout() {
        char rowNum = 'A';
        for (int i=0; i<column; i++) {
            if (i == 0) System.out.print("    ");
            System.out.print((i+1) + " ");
            if (i == column/2) System.out.print("\t");
        }
        System.out.println("\n");
        for (int i=0; i<row; i++) {
            for (int j=0; j<column; j++) {
                if (j == 0){
                    System.out.print(rowNum + "   ");
                    rowNum++;
                }
                if (layout[i][j].getSeatStatus())
                    System.out.print("X ");
                else System.out.print("O ");
                if (j == column/2) System.out.print("\t");
            }
            System.out.println();
        }
    }

    /**
     * getter method to get the number of rows in that particular Cinema
     * @return int The number of rows in the Cinema
     */

    public int getRow() {
        return row;
    }

    /**
     * getter method to get the number of Columns in that particular Cinema
     * @return int The number of columns in the Cinema
     */

    public int getColumn() {
        return column;
    }

    /**
     * getter method to get the total number of seats in that particular Cinema
     * @return int The total number of seats in the Cinema
     */

    public int getTotalNumberOfSeats() {
        return row * column;
    }

    /**
     * Method to locate and assign a seat to a particular Customer after they buy their tickets
     * @param id The ID of the seat that is being assigned
     */

    public void assignSeat(int id) {
        int i = id/column;
        layout[i][id % column].assign();
    }

    /**
     * Method to locate and unassign a seat from a particular Customer
     * @param id The ID of the seat that is being unassigned
     */

    public void unassignSeat(int id) {
        int i = id/column;
        layout[i][id % column].unassign();
    }

    /**
     * getter method to get a particular Seat using its ID
     * @param id ID of the Seat to get
     * @return Seat the Seat that is corresponding to the ID parameter passed into the method
     */

    public Seat getSeat(int id){
        int i = id/column;
        return layout[i][id % column];
    }

    /**
     * Method to check the availability of teh Seat using its ID
     * @param id ID of the Seat that is being queried
     * @return boolean If the seat is available or not
     */
    public boolean checkSeatStatus(int id) {
        int i = id/column;
        return layout[i][id % column].getSeatStatus();
    }
}
