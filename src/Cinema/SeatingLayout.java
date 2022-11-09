package Cinema;

import java.io.Serializable;

public class SeatingLayout implements Serializable {
    private Seat[][] layout;
    private int row;
    private int column;

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

    public void printLayout() {
        for (int i=0; i<row; i++) {
            for (int j=0; j<column; j++) {
                if (layout[i][j].getSeatStatus())
                    System.out.print("X ");
                else System.out.print("O ");
                if (j == column/2) System.out.print("\t");
            }
            System.out.println();
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getTotalNumberOfSeats() {
        return row * column;
    }

    public void assignSeat(int id) {
        int i = id/column;
        layout[i][id % column].assign();
    }

    public void unassignSeat(int id) {
        int i = id/column;
        layout[i][id % column].unassign();
    }

    public Seat getSeat(int id){
        int i = id/column;
        return layout[i][id % column];
    }
    public boolean checkSeatStatus(int id) {
        int i = id/column;
        return layout[i][id % column].getSeatStatus();
    }
}
