package Movies;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class MovieTest {
    public static void main(String[] args) {
        MovieController movieController = new MovieController();
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        LocalDate today = LocalDate.now();
        Random random = new Random();
        LocalDate someOtherDay = today.plusDays(random.nextInt(30));
        LocalDate somePastDay = today.minusDays(random.nextInt(30));
        ArrayList<String> cast = new ArrayList<String>();
        // cast.add("Cast1Person1");
        // cast.add("Cast1Person2");
        // movieController.createNewMovie(1, "Movie1", "3D", "How to drop out of NTU", "R21", "Clement Liang Tian", cast, 6.9, today, someOtherDay);
        
        // someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        // cast.clear();
        // cast.add("Cast2Person1");
        // cast.add("Cast2Person2");   
        // movieController.createNewMovie(2, "Movie2", "BlockBuster", "This assignment very hard", "PG13", "Aloy", cast, 2.5, today, someOtherDay); 
        
        
        // someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        // cast.clear();
        // cast.add("Cast3Person1");
        // cast.add("Cast3Person2");   
        // movieController.createNewMovie(3, "Movie3", "Horror", "Life in NTU", "NC16", "Aloy", cast, 4, today, someOtherDay);
        
        // cast.clear();
        // cast.add("Cast4Person1");
        // cast.add("Cast4Person2");
        // movieController.createNewMovie(4, "People in Paris", "Hood Classic", "Some people of color visiting Paris", "NC16", "Kanye East", cast, 3.0, somePastDay.minusDays(10), somePastDay);
        
        movieController.addReviewMovieUsingId(4, "Kanye South", 5, "TesttestTestTest");
        movieList = movieController.read();
        for (Movie movie : movieList) {
            System.out.println(movie.makeString());
        }

        // movieController.readReviewMovieUsingId(4);

        // System.out.println("\n\n =======================Searching for People in Paris=======================");
        // ArrayList<Movie> titleSearching = movieController.searchByTitle("people in paris");
        // for (Movie movie : titleSearching) {
        //     System.out.println(movie.makeString());
        // }


        // System.out.println("\n\n=================Searching for NC16 movies===============");

        // ArrayList<Movie> nc16Movies = movieController.searchByRating("nc16");
        // for (Movie movie : nc16Movies) {
        //     System.out.println(movie.makeString());
        // }


        // ArrayList<Movie> availableMovies = movieController.getAvailableMovies();
        // for (Movie movie : availableMovies) {
        //     System.out.println(movie.makeString());
        // }
        // System.out.println("TESTING REMOVING MOVIE2");
        // movieController.removeMovieById(2);
        // movieList = movieController.read();
        // for (Movie movie : movieList) {
        //     System.out.println(movie.makeString());
        // }
    }
}

