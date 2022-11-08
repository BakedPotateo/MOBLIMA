package Movies;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Cinema.Cinema;

public class Showtime {
    private String showtimeID;
    private LocalDateTime dateTime;
    private int movieID;
    private Cinema cinema;
    private String cineplex;

    public Showtime(String showtimeID, LocalDateTime dateTime, int movieID, Cinema cinema, String cineplex){
        this.showtimeID = showtimeID;
        this.dateTime = dateTime;
        this.movieID = movieID;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }

    public String getShowtimeID(){
        return this.showtimeID;
    }

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

    public int getMovieID(){
        return this.movieID;
    }

    public Cinema getCinema(){
        return this.cinema;
    }

    public String getCineplex(){
        return this.cineplex;
    }

    public void setShowtimeID(String showtimeID){
        this.showtimeID = showtimeID;
    }

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public void setMovieID(int movieID){
        this.movieID = movieID;
    }

    public void setCinema(Cinema cinema){
        this.cinema = cinema;
    }

    public void setCineplex(String cineplex){
        this.cineplex = cineplex;
    }

    public void makeString(){
        System.out.println("Showtime ID: " + showtimeID);
        System.out.println("Date | Time: " + this.dateTime.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy | HH:mm")));
        System.out.println("Cineplex | Cinema: " + cineplex + " | " + cinema.getId());
    }
}
