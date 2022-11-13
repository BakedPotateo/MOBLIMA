package Movies;
import java.io.Serializable;

public class Review implements Serializable{

    // Attributes

    private String username;
    private double numberOfStars;
    private String comments;

    /**
     * Constructor for the Review class
     * @param username The username of the person giving the review
     * @param numberOfStars The number of stars out of 5 that the user gave to rate the movie
     * @param comments Additional comments that the user made on the movie
     */

    public Review(String username, double numberOfStars, String comments) {
        this.username = username;
        this.numberOfStars = numberOfStars;
        this.comments = comments;
    }

    /**
     * getter method to get the username of the person giving the Review
     * @return String Returns the username of the person giving the Review
     */

    public String getUsername() {
        return username;
    }

    /**
     * setter method to set the username of the person giving the Review
     * @param username The username of the person giving the Review
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter method to get the number of stars that the user gave the movie
     * @return double Returns the number of stars that the user gave the movie
     */

    public double getNumberOfStars() {
        return numberOfStars;
    }

    /**
     * setter method to set the number of stars the user gave the movie
     * @param numberOfStars The number of stars the user gave the movie
     */

    public void setNumberOfStars(double numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    /**
     * getter method to get the comments the user made on the movie
     * @return String Returns the comments the user made on the movie
     */

    public String getComments() {
        return comments;
    }

    /**
     * setter method to set the comments the user made on the movie
     * @param comments The comments that the user made on the movie
     */

    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Method to get the specific details of a Review that a user left on the movie
     * @return String Returns the specific details of a Review that a user left on the movie
     */

    public String makeString() {
        String myString = "";
        myString += "\tUsername: " + getUsername() + "\n" 
        + "\tStar Rating: " + String.valueOf(getNumberOfStars()) + "\n"
        + "\tComments: " + getComments();
        return myString;
    }
}