package Cinema;

public class Cinema {
    private String classOfCinema;
    private int numberOfRows;
    private int numberOfColumns;
    private int[][] occupiedSeats;

    public Cinema(String classOfCinema, int numberOfRows, int numberOfColumns, int[][] occupiedSeats) {
        this.classOfCinema = classOfCinema;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.occupiedSeats = occupiedSeats;
    }
    public String getClassOfCinema() {
        return classOfCinema;
    }
    public void setClassOfCinema(String classOfCinema) {
        this.classOfCinema = classOfCinema;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
    public int getNumberOfColumns() {
        return numberOfColumns;
    }
    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }
    public int[][] getOccupiedSeats() {
        return occupiedSeats;
    }
    public void setOccupiedSeats(int[][] occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }
}
