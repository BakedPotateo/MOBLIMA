package Tickets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Cinema.Cinema;
import Movies.Movie;

public class Ticket {
    private Movie movie;
    private double price;
    private Cinema cinema; //by right should get from cinemaclass
    private int[] seatNumber;
    private LocalDate dayOfWeek;
    private LocalTime timeOfMovie;

    public Ticket(Movie movie, double price, Cinema cinema,int[] seatNumber, LocalDate dayOfWeek, LocalTime timeOfMovie) {
        this.movie = movie;
        this.price = price;
        this.cinema = cinema;
        this.seatNumber = seatNumber;
        this.dayOfWeek = dayOfWeek;
        this.timeOfMovie = timeOfMovie;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int[] getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int[] seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getDayOfWeek() {
        return dayOfWeek.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }
    public void setDayOfWeek(LocalDate dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public String getTimeOfMovie() {
        return timeOfMovie.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public void setTimeOfMovie(LocalTime timeOfMovie) {
        this.timeOfMovie = timeOfMovie;
    }

    public String makeString() {
        String ticketDetails = "";
        ticketDetails += "Movie: " + getMovie().getTitle() + "\n"
                       + "Movie Type: " + getMovie().getMovieType()
                       + "Price: " + getPrice()
                       + "Cinema Type: " + cinema.getClassOfCinema()
                       + "Seat Number: " + getSeatNumber()
                       + "Date: " + getDayOfWeek()
                       + "Time: " + getTimeOfMovie();
        return ticketDetails;
    }

    
}
