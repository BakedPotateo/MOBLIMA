package Movies;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.Serializable;

public class Movie implements Serializable{

    // Attributes

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
    private double sales = 0;
    private ArrayList<Showtime> showtimes;

    /**
     * Constructor method of the Movie class to initialize the Reviews, Cast, and Showtimes of the Movie
     */

    public Movie(){
        this.reviews = new ArrayList<Review>();
        this.cast = new ArrayList<String>();
        this.showtimes = new ArrayList<Showtime>();
    }

    /**
     * Constructor method of the Movie class
     * @param id ID of the Movie
     * @param title Title of the Movie
     * @param movieType Type of the Movie
     * @param synopsis Short description of what the Movie is about
     * @param rating Rating that the Movie has
     * @param director Director of the Movie
     * @param cast Cast that are starring in the Movie
     * @param duration Length of the Movie
     * @param releaseDate The date that the Movie is available for viewing to the public
     * @param endDate The date that the Movie becomes unavailable to view 
     */

    public Movie(int id, String title, String movieType, String synopsis, 
                 String rating, String director, ArrayList<String> cast, 
                 double duration, LocalDate releaseDate, LocalDate endDate) {
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
        this.showtimes = new ArrayList<Showtime>();
    }

    // Public Methods

    /**
     * getter method to get the ID of the Movie
     * @return int The ID of the Movie
     */

    public int getId() {
        return id;
    }

    /**
     * setter method to set the ID of the Movie
     * @param id The ID of the Movie
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter method to get the title of the Movie 
     * @return String The title of the Movie
     */

    public String getTitle() {
        return title;
    }

    /**
     * setter method to set the title of the Movie
     * @param title The title of the Movie
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to get the showing status of the movie
     * Showing Status a Movie can have: 
     * - END OF SHOWING: the Movie is unavailable for viewing anymore
     * - PREVIEW: a preview of the Movie is available for viewing
     * - COMING SOON: the Movie is unreleased yet
     * - NOW SHOWING: the Movie is currently airing
     * @return String Returns the showing status of the movie
     */

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

    /**
     * getter method to get the type of the Movie
     * @return String Returns the type of the Movie
     */

    public String getMovieType() {
        return movieType;
    }

    /**
     * setter method to set the type of Movie
     * @param movieType The type of Movie
     */

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    /**
     * getter method to get the synopsis of the Movie
     * @return String Returns the synopsis of the Movie
     */

    public String getSynopsis() {
        return synopsis;
    }

    /**
     * setter method to set the synopsis of the Movie
     * @param synopsis The synopsis of the Movie
     */

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * getter method to get the rating of the Movie
     * @return String Returns the rating of the Movie
     */

    public String getRating() {
        return rating;
    }

    /**
     * setter method to set the rating of the Movie
     * @param rating Rating of the Movie
     */

    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * getter method to get the reviews that the Movie has
     * @return ArrayList<Review> list of reviews the Movie has
     */

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * setter method to set the list of reviews that the Movie has
     * @param reviews List of reviews that the Movie has
     */

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Method to add a review to the list of reviews that the Movie has
     * @param review Review of the movie
     */
    
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * getter method to get the director of the Movie
     * @return String The director of the Movie
     */

    public String getDirector() {
        return director;
    }

    /**
     * setter method to set the director of the Movie
     * @param director The director of the Movie
     */

    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * getter method to get the list of the cast starring in the Movie
     * @return ArrayList<String> Returns the list of the cast starring in the Movie
     */

    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * setter method to set the list of the cast starring in the Movie
     * @param cast List of all the cast starring in the Movie
     */

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * getter method to get the duration of the Movie
     * @return double Returns the duration of the Movie
     */

    public double getDuration() {
        return duration;
    }

    /**
     * setter method to set the duration of the Movie
     * @param duration The duration of the Movie
     */

    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * getter method to get the release date of the Movie in the 'EEEE, dd/MM/yyyy' format
     * @return String Returns the release date of the Movie
     */

    public String getReleaseDate() {
        return releaseDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    /**
     * setter method to set the release date of the Movie
     * @param releaseDate The release day of the Movie 
     */
    
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

     /**
     * getter method to get the end date of the Movie in the 'EEEE, dd/MM/yyyy' format
     * @return String Returns the end date of the Movie
     */

    public String getEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    /**
     * setter method to set the end date of the Movie
     * @param endDate The end date of the Movie
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * getter method to get the end sales of the Movie
     * @return double Returns the sales of the Movie
     */

    public double getSales() {
        return sales;
    }

    /**
     * setter method to set the sales of the Movie
     * @param sales The amount of sales that the Movie has made
     */

    public void setSales(Double sales) {
        this.sales = sales;
    }

    /**
     * getter method to get the list of showtimes of the Movie
     * @return ArrayList<Showtime> Returns the list of showtimes for the Movie
     */

    public ArrayList<Showtime> getShowtimes(){
        return showtimes;
    }

    /**
     * setter method to set the list of showtimes for the Movie
     * @param showtimes The list of showtimes for that particular Movie
     */

    public void setShowtimes(ArrayList<Showtime> showtimes){
        this.showtimes = showtimes;
    }

    /**
     * Method to get the specific details of the instance of a Movie
     * @return String Returns the details of the instance of a Movie
     */

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

