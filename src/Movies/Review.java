package Movies;
import java.io.Serializable;

public class Review implements Serializable{
    private String username;
    private double numberOfStars;
    private String comments;

    public Review(String username, double numberOfStars, String comments) {
        this.username = username;
        this.numberOfStars = numberOfStars;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(double numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String makeString() {
        String myString = "";
        myString += "\tUsername: " + getUsername() + "\n" 
        + "\tStar Rating: " + String.valueOf(getNumberOfStars()) + "\n"
        + "\tComments: " + getComments();
        return myString;
    }
}