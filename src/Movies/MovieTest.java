package Movies;
import java.time.LocalDate;
// import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.Random;
import java.util.Random;

import Managers.MovieManager;


public class MovieTest {
    public static void main(String[] args) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        LocalDate today = LocalDate.now();
        Random random = new Random();
        LocalDate someOtherDay = today.plusDays(random.nextInt(30));
        LocalDate somePastDay = today.minusDays(random.nextInt(30));
        ArrayList<String> cast = new ArrayList<String>();
        cast.add("Tom Hollandaise");
        cast.add("Michelle Yo");
        MovieManager.getInstance().createNewMovie(1, "Nanyang Delinquents", "3D", "How to drop out of NTU.", "R21", "Clement Liang Tian", cast, 6.9, today, someOtherDay);
        MovieManager.getInstance().addReviewMovieUsingId(1, "Jon Gan", 4, "Really great movie, no spoilers here ;)");
        MovieManager.getInstance().addReviewMovieUsingId(1, "Nicky Lim", 5, "Movie of the year!!");
        MovieManager.getInstance().addReviewMovieUsingId(1, "Vik", 4.5, "Reminds me of that time when I thought I should drop out :')");

        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Dwayne \"The Rock\" Johnson");
        cast.add("Ryan Reynolds");   
        MovieManager.getInstance().createNewMovie(2, "SC2002: The Movie", "BlockBuster", "This assignment very hard.", "PG13", "Aloy", cast, 2.5, today, someOtherDay); 
        
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye North", 5, "Best movie I've ever seen!");
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye South", 5, "10/10 would watch again.");
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye East", 4.5, "Amazing movie!");
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye Wast", 4.0, "Reminds me of my time in school :')");
        
        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Alexander Ham");
        cast.add("Keanu Reeverse");   
        MovieManager.getInstance().createNewMovie(3, "Roommates", "Horror", "Life in NTU.", "NC16", "Aloy", cast, 4, today, someOtherDay);
        
        MovieManager.getInstance().addReviewMovieUsingId(3, "Jimmy Ho", 4, "Super scary, I was screaming!!!");
        MovieManager.getInstance().addReviewMovieUsingId(3, "Rachel Tan", 5, "10/10 would recommend this to my friends.");
        MovieManager.getInstance().addReviewMovieUsingId(3, "Lucas Lim", 4.5, "Reminds me of my roommate :')");
        
        cast.clear();
        cast.add("Chris Maple");
        cast.add("Reese Witherfork");
        MovieManager.getInstance().createNewMovie(4, "People in Paris", "Hood Classic", "Some people of color visiting Paris.", "NC16", "Kanye East", cast, 3.0, somePastDay.minusDays(10), somePastDay);

        MovieManager.getInstance().addReviewMovieUsingId(4, "Fabian Tan", 3, "It was ok");
        MovieManager.getInstance().addReviewMovieUsingId(4, "Joshua Lim", 4, "Really good!!!");
        MovieManager.getInstance().addReviewMovieUsingId(4, "Samantha Ng", 3.5, "Reminds me of when i visited Paris :')");

        cast.add("Kevin Heart");
        cast.add("Jennifer Lorenzo");
        MovieManager.getInstance().createNewMovie(5, "Dynamic Programming for Dummies", "Comedy", "THe adventures of 2 best friends learning OODP.", "R21", "Clement Liang Tian", cast, 1, today, someOtherDay);

        MovieManager.getInstance().addReviewMovieUsingId(5, "Jeffrey Smith", 5, "Great movie!");
        MovieManager.getInstance().addReviewMovieUsingId(5, "Nicole Lee", 5, "ROFL");
        MovieManager.getInstance().addReviewMovieUsingId(5, "Bobby Mohd", 5, "Reminds of of when I *laughing emoji* :')");

        movieList = MovieManager.getInstance().read();
        for (Movie movie : movieList) {
            MovieManager.getInstance().editSales(movie);
            System.out.println(movie.makeString());
        }

    }
}

