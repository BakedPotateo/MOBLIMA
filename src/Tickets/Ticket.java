package Tickets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String typeOfMovie;
    private double price;
    // private User user; //get from userclass
    // private Cinema cinemaType; //get from cinemaclass
    private LocalDate dayOfWeek;
    private LocalTime timeOfMovie;


    public String getTypeOfMovie() {
        return typeOfMovie;
    }
    public void setTypeOfMovie(String typeOfMovie) {
        this.typeOfMovie = typeOfMovie;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
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

    
}
