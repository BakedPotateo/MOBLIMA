package Movies;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.Serializable;

public class Movie implements Serializable{
    private int id;
    private String title;
    private String showingStatus;
    private String movieType;
    private String synopsis;
    private String rating;
    private ArrayList<Review> reviews; 
    private String director;
    private ArrayList<String> cast;
    private double duration;
    private LocalDate releaseDate;
    private LocalDate endDate;
    private int sales = 0;

    public Movie(int id, String title, String movieType, String synopsis, String rating, String director, ArrayList<String> cast, double duration, LocalDate releaseDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.movieType = movieType;
        this.synopsis = synopsis;
        this.rating = rating;
        this.director = director;
        this.cast = cast;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.reviews = new ArrayList<Review>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowingStatus() {
        LocalDate today = LocalDate.now();
        if (today.isAfter(endDate))
            showingStatus = "END OF SHOWING";
        else {
            float numberOfDaysBet = Duration.between(today.atStartOfDay(), releaseDate.atStartOfDay()).toDays();
            if (numberOfDaysBet > 0 && numberOfDaysBet <= 7)
                showingStatus = "PREVIEW";
            else if (numberOfDaysBet > 7)
                showingStatus = "COMING SOON";
            else showingStatus = "NOW SHOWING";
        }
        return showingStatus;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String makeString() {
        String castString = "";
        for (int i=0; i<getCast().size(); i++) {
            castString += getCast().get(i) + ", ";
        }
        castString = castString.substring(0, castString.length()-2);

        String reviews = "";
        for (int i=0; i<getReviews().size(); i++) {
            reviews += getReviews().get(i).makeString() + "\n";
        }

        if (reviews == "") reviews = "N/A";

        String movieDetails = "";
        movieDetails += "ID: " + getId() + "\n"
                     +"Title: " + getTitle() + "\n"
                     + "Type: " + getMovieType() + "\n"
                     + "Status: " + getShowingStatus() + "\n"
                     + "Synopsis: " + getSynopsis() + "\n"
                     + "Rating: " + getRating() + "\n"
                     + "Director: " + getDirector() + "\n"
                     + "Cast: " + castString + "\n"
                     + "Duration: " + getDuration() + "hour(s)\n"
                     + "Release Date: " + getReleaseDate() + "\n"
                     + "End Date: " + getEndDate() + "\n"
                     + "Reviews:\n" + reviews + "\n";
        
        return movieDetails;
    }

    
}

