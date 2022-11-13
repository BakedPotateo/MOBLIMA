package utils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import Cinema.Cinema;
import Cinema.SeatingLayout;
import Managers.CinemaManager;
import Managers.MovieManager;
import Movies.Movie;
import Movies.Showtime;

public class Initialiser {
    /*
     * instance checks whether Initialiser has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static Initialiser instance = null;

    /*
     * Empty class constructor
     */
    private Initialiser(){this.Initialise();}
    
    // Public methods

    /*
     * getInstance checks if Initialiser has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static Initialiser getInstance()
    {
        if (instance == null)
            instance = new Initialiser(); // instance is a static variable
        return instance;
    }

    private void Initialise(){
        // Initialise cineplexes

        CinemaManager manager = CinemaManager.getInstance();
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>(); 
        SeatingLayout layout1 = new SeatingLayout(5, 10);
        SeatingLayout layout2 = new SeatingLayout(3, 15);
        SeatingLayout layout3 = new SeatingLayout(7, 8);
        Cinema cinema1 = new Cinema("E1", "Normal", layout1);
        Cinema cinema2 = new Cinema("E2", "Normal", layout2);
        Cinema cinema3 = new Cinema("E3", "Gold Class", layout3);
        cinemas.add(cinema1);
        cinemas.add(cinema2);
        cinemas.add(cinema3);
        manager.createCineplex("Shaws Theatre", cinemas);
        manager.createCineplex("Golden Village", cinemas);
        manager.createCineplex("EagleWings", cinemas);

        // Initialise movies

        ArrayList<Movie> movieList = new ArrayList<Movie>();
        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        Movie m;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
        LocalDate today = LocalDate.now();
        Random random = new Random();
        LocalDate someOtherDay = LocalDate.parse("2023-01-01");
        LocalDate somePastDay = today.minusDays(random.nextInt(30));
        ArrayList<String> cast = new ArrayList<String>();
        cast.add("Tom Hollandaise");
        cast.add("Michelle Yo");
        MovieManager.getInstance().createNewMovie(1, "Nanyang Delinquents", "3D", "How to drop out of NTU.", "R21", "Clement Liang Tian", cast, 6.9, today, someOtherDay);
        MovieManager.getInstance().addReviewMovieUsingId(1, "Jon Gan", 4, "Really great movie, no spoilers here ;)");
        MovieManager.getInstance().addReviewMovieUsingId(1, "Nicky Lim", 5, "Movie of the year!!");
        MovieManager.getInstance().addReviewMovieUsingId(1, "Vicky Leow", 4.5, "Reminds me of that time when I thought I should drop out :')");
        showtimes.add(new Showtime("S1", LocalDateTime.parse("2022-11-25 12:00", formatter), 1, cinemas.get(0) , "Shaws Theatre"));
        showtimes.add(new Showtime("S2", LocalDateTime.parse("2022-11-31 15:45", formatter), 1, cinemas.get(2) , "Golden Village"));
        showtimes.add(new Showtime("S3", LocalDateTime.parse("2022-12-25 09:30", formatter), 1, cinemas.get(1) , "EagleWings"));
        m =  MovieManager.getInstance().searchById(1);
        m.setShowtimes(showtimes);
        MovieManager.getInstance().removeMovieById(m.getId());
        MovieManager.getInstance().createNewMovie(m);

        showtimes.clear();

        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Dwayne \"The Rock\" Johnson");
        cast.add("Ryan Reynolds");   
        MovieManager.getInstance().createNewMovie(2, "SC2002: The Movie", "BlockBuster", "This assignment very hard.", "PG13", "Aloy", cast, 2.5, today, someOtherDay); 
        
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye North", 5, "Best movie I've ever seen!");
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye South", 5, "10/10 would watch again.");
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye East", 4.5, "Amazing movie!");
        MovieManager.getInstance().addReviewMovieUsingId(2, "Kanye Wast", 4.0, "Reminds me of my time in school :')");
        showtimes.add(new Showtime("S1", LocalDateTime.parse("2022-12-15 15:00", formatter), 2, cinemas.get(0) , "Golden Village"));
        showtimes.add(new Showtime("S2", LocalDateTime.parse("2022-11-26 11:00", formatter), 2, cinemas.get(1) , "Golden Village"));
        showtimes.add(new Showtime("S3", LocalDateTime.parse("2022-12-22 14:30", formatter), 2, cinemas.get(2) , "EagleWings"));
        m =  MovieManager.getInstance().searchById(2);
        m.setShowtimes(showtimes);
        MovieManager.getInstance().removeMovieById(m.getId());
        MovieManager.getInstance().createNewMovie(m);

        showtimes.clear();

        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Alexander Ham");
        cast.add("Keanu Reeverse");   
        MovieManager.getInstance().createNewMovie(3, "Roommates", "Horror", "Life in NTU.", "NC16", "Aloy", cast, 4, today, someOtherDay);
        
        MovieManager.getInstance().addReviewMovieUsingId(3, "Jimmy Ho", 4, "Super scary, I was screaming!!!");
        MovieManager.getInstance().addReviewMovieUsingId(3, "Rachel Tan", 5, "10/10 would recommend this to my friends.");
        MovieManager.getInstance().addReviewMovieUsingId(3, "Lucas Lim", 4.5, "Reminds me of my roommate :')");
        showtimes.add(new Showtime("S1", LocalDateTime.parse("2022-12-17 11:15", formatter), 3, cinemas.get(1) , "Shaws Theatre"));
        showtimes.add(new Showtime("S2", LocalDateTime.parse("2022-11-26 11:45", formatter), 3, cinemas.get(1) , "Shaws Theatre"));
        showtimes.add(new Showtime("S3", LocalDateTime.parse("2023-01-01 16:30", formatter), 3, cinemas.get(0) , "EagleWings"));
        m =  MovieManager.getInstance().searchById(3);
        m.setShowtimes(showtimes);
        MovieManager.getInstance().removeMovieById(m.getId());
        MovieManager.getInstance().createNewMovie(m);

        showtimes.clear();

        cast.clear();
        cast.add("Chris Maple");
        cast.add("Reese Witherfork");
        MovieManager.getInstance().createNewMovie(4, "People in Paris", "Hood Classic", "Some people of color visiting Paris.", "NC16", "Kanye East", cast, 3.0, somePastDay.minusDays(10), somePastDay);

        MovieManager.getInstance().addReviewMovieUsingId(4, "Fabian Tan", 3, "It was ok");
        MovieManager.getInstance().addReviewMovieUsingId(4, "Joshua Lim", 4, "Really good!!!");
        MovieManager.getInstance().addReviewMovieUsingId(4, "Samantha Ng", 3.5, "Reminds me of when i visited Paris :')");
        // end of showing, not showtimes available

        someOtherDay = someOtherDay.plusDays(random.nextInt(30));
        cast.clear();
        cast.add("Kevin Heart");
        cast.add("Jennifer Lorenzo");
        MovieManager.getInstance().createNewMovie(5, "Dynamic Programming for Dummies", "Comedy", "The adventures of 2 best friends learning OODP.", "R21", "Clement Liang Tian", cast, 1, someOtherDay.minusDays(30), someOtherDay);

        showtimes.add(new Showtime("S1", LocalDateTime.parse("2022-12-31 16:00", formatter), 5, cinemas.get(2) , "EagleWings"));
        showtimes.add(new Showtime("S2", LocalDateTime.parse("2023-01-08 17:50", formatter), 5, cinemas.get(1) , "Golden Village"));
        showtimes.add(new Showtime("S3", LocalDateTime.parse("2023-01-16 14:45", formatter), 5, cinemas.get(2) , "EagleWings"));
    m =  MovieManager.getInstance().searchById(5);
        m.setShowtimes(showtimes);
        MovieManager.getInstance().removeMovieById(m.getId());
        MovieManager.getInstance().createNewMovie(m);

        showtimes.clear();

        movieList = MovieManager.getInstance().read();
        for (Movie movie : movieList) {
            MovieManager.getInstance().setInitialSales(movie, random.nextDouble(100000));;
        }
    }
}
