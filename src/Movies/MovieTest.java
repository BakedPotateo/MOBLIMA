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
        cast.add("Kevin");
        cast.add("Yen");
        MovieManager.getInstance().createNewMovie(1, "Nanyang Delinquents", "3D", "How to drop out of NTU", "R21", "Clement Liang Tian", cast, 6.9, today, someOtherDay);
        
        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Hashil");
        cast.add("Ryan");   
        MovieManager.getInstance().createNewMovie(2, "SC2002: The Movie", "BlockBuster", "This assignment very hard", "PG13", "Aloy", cast, 2.5, today, someOtherDay); 
        
        
        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Aloysius");
        cast.add("Jonathan");   
        MovieManager.getInstance().createNewMovie(3, "Roommates", "Horror", "Life in NTU", "NC16", "Aloy", cast, 4, today, someOtherDay);
        
        cast.clear();
        cast.add("Jimmy");
        cast.add("Samantha");
        MovieManager.getInstance().createNewMovie(4, "People in Paris", "Hood Classic", "Some people of color visiting Paris", "NC16", "Kanye East", cast, 3.0, somePastDay.minusDays(10), somePastDay);

        cast.add("Guy");
        cast.add("Girl");
        MovieManager.getInstance().createNewMovie(4, "SCSE", "Comedy", "", "R21", "Clement Liang Tian", cast, 1, today, someOtherDay);

        MovieManager.getInstance().addReviewMovieUsingId(4, "Kanye East", 5, "People In Paris Part 2");
        movieList = MovieManager.getInstance().read();
        for (Movie movie : movieList) {
            System.out.println(movie.makeString());
        }

    }
}

