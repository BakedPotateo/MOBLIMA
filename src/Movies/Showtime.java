package Movies;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Cinema.Cinema;

public class Showtime implements Serializable{

    // Attributes

    private String showtimeID;
    private LocalDateTime dateTime;
    private int movieID;
    private Cinema cinema;
    private String cineplex;

    /**
     * Empty Showtime class constructor
     */
    public Showtime(){}

    /**
     * Constructor for the Showtime class
     * @param showtimeID ID of the showtime of the movie
     * @param dateTime Date and time of the showtime of the movie
     * @param movieID ID of the movie corresponding to this Showtime
     * @param cinema Cinema that the movie is available in
     * @param cineplex Cineplex that the movie is available in
     */

    public Showtime(String showtimeID, LocalDateTime dateTime, int movieID, Cinema cinema, String cineplex){
        this.showtimeID = showtimeID;
        this.dateTime = dateTime;
        this.movieID = movieID;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }

    // Public Methods

    /**
     * getter method to get the Showtime ID movie
     * @return String Showtime ID of the movie
     */

    public String getShowtimeID(){
        return this.showtimeID;
    }

    /**
     * getter method to get the date and time of the movie Showtime
     * @return LocalDateTime The date and time of the movie Showtime
     */

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

    /**
     * getter method to get the ID of the movie corresponding to the Showtime
     * @return int ID of the movie corresponding to the Showtime
     */

    public int getMovieID(){
        return this.movieID;
    }

    /**
     * getter method to get the Cinema the movie is available in
     * @return Cinema The Cinema that the movie is available in
     */

    public Cinema getCinema(){
        return this.cinema;
    }

    /**
     * getter method to get the Cineplex the movie is available in
     * @return Cineplex The Cineplex that the movie is available in
     */
    
     public String getCineplex(){
        return this.cineplex;
    }

    /**
     * setter method to set the ID of the Showtime of the movie
     * @param showtimeID The ID of the Showtime of the movie
     */

    public void setShowtimeID(String showtimeID){
        this.showtimeID = showtimeID;
    }

    /**
     * setter method to set the date and time of the showtime of the movie
     * @param dateTime The date and time of the showtime of the movie
     */

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    /**
     * setter method to set the ID of the movie corresponding to its Showtime
     * @param movieID The ID of the movie correspoding to its Showtime
     */

    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    /**
     * setter method to set the Cinema the movie is available in 
     * @param cinema Cinema the movie is available in
     */

    public void setCinema(Cinema cinema){
        this.cinema = cinema;
    }

    /**
     * setter method to set the Cineplex the movie is available in
     * @param cineplex Cineplex the movie is available in
     */

    public void setCineplex(String cineplex){
        this.cineplex = cineplex;
    }

    /**
     * Method to print out the specific details of an instance of a Showtime of a movie
     */

    public void makeString(){
        System.out.println("Showtime ID: " + showtimeID);
        System.out.println("Date | Time: " + this.dateTime.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy | HH:mm")));
        System.out.println("Cinema     : " + cineplex + " | " + cinema.getId() + " | " + cinema.getClassOfCinema());
    }
}
