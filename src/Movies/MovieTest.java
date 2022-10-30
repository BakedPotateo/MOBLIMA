package Movies;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class MovieTest {
    public static void main(String[] args) {
        // ArrayList<String> myCast = new ArrayList<String>();
        // myCast.add("Your mother");
        // myCast.add("Your father");
        // myCast.add("Your sister");
        // myCast.add("Your brother");
        // Review test1 = new Review("Person1", 4.8, "Loved it would go watch again definitely.");
        // Review test2 = new Review("Person2", 0.9, "Hated it it was like watching a dead rat");
        // ArrayList<Review> myReviews = new ArrayList<Review>();
        // myReviews.add(test1);
        // myReviews.add(test2);
        // LocalDate today = LocalDate.now();
        // LocalDate endDate = today.plusDays(14);
        // Movie movie = new Movie("A", "BlockBuster", "About your mother", 5, "Your Mother", myCast, 2.5, today, endDate);
        // movie.setReviews(myReviews);
        // System.out.println(movie.makeString());
        MovieController movieController = new MovieController();
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        LocalDate today = LocalDate.now();
        Random random = new Random();
        LocalDate someOtherDay = today.plusDays(random.nextInt(30));
        ArrayList<String> cast = new ArrayList<String>();
        cast.add("Cast1Person1");
        cast.add("Cast1Person2");
        movieController.createNewMovie("Movie1", "3D", "How to drop out of NTU", "R21", "Clement Liang Tian", cast, 6.9, today, someOtherDay);
        
        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Cast2Person1");
        cast.add("Cast2Person2");   
        movieController.createNewMovie("Movie2", "BlockBuster", "This assignment very hard", "PG13", "Aloy", cast, 2.5, today, someOtherDay); 
        
        
        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Cast3Person1");
        cast.add("Cast3Person2");   
        movieController.createNewMovie("Movie3", "Horror", "Life in NTU", "NC16", "Aloy", cast, 4, today, someOtherDay);
        movieList = movieController.read();
        for (Movie movie : movieList) {
            System.out.println(movie.makeString());
        }
    }
}

