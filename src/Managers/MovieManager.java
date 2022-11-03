package Managers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Movies.Movie;
import Movies.Review;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Identity;

import utils.ProjectRootPathFinder;

public class MovieManager {

    // Attributes

    private Scanner sc = new Scanner(System.in);
    /*
     * instance checks whether MovieManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static MovieManager instance = null;

    /*
     * Empty class constructor
     */
    private MovieManager(){}

    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Movies/movies.txt";
    
    // Public methods

    /*
     * getInstance checks if MovieManager has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static MovieManager getInstance()
    {
        if (instance == null)
            instance = new MovieManager(); // instance is a static variable
        return instance;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Movie> read() {
        try {
            ObjectInputStream objectinputstream = new ObjectInputStream(new FileInputStream(FILE));
            ArrayList<Movie> movieList = (ArrayList<Movie>) objectinputstream.readObject();
            objectinputstream.close();
            return movieList;
        } catch (ClassNotFoundException | IOException e) {}
        return new ArrayList<Movie>();
    }

    public void createNewMovie(int id, String title, String movieType, String synopsis, String rating, String director, ArrayList<String> cast, double duration, LocalDate releaseDate, LocalDate endDate){
        Movie movie = new Movie(id, title, movieType, synopsis, rating, director, cast, duration, releaseDate, endDate);
        ArrayList<Movie> data = new ArrayList<Movie>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            data = read();

        Movie temp = searchById(id);
        if (temp != null) {
            System.out.println("Movie with conflicting ID already exists...");
            return;
        }

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            data.add(movie);
            output.writeObject(data);
            output.flush();
            output.close();
        } catch (IOException e) {}
    }

    public Movie searchById(int id) {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();

        for (int i=0; i<data.size(); i++) {
            Movie m = data.get(i);
            if (m.getId() == id)
                return m;
        }
        // System.out.println("Movie ID entered does not exist...\n");
        return null;
    }

    public ArrayList<Movie> getAvailableMovies() {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();
        ArrayList<Movie> availableMovies = new ArrayList<Movie>();

        for (int i=0; i<data.size(); i++) {
            if (data.get(i).getShowingStatus() == "PREVIEW" || data.get(i).getShowingStatus() == "NOW SHOWING")
                availableMovies.add(data.get(i));
        }

        return availableMovies;
    }

    public void removeMovieById(int id) {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();
        for (int i=0; i<data.size(); i++) {
            if (data.get(i).getId() == id) {
                // Movie m = data.get(i);
                data.remove(i);
                // System.out.println("MOVIE REMOVED: " + m.getTitle() + " (ID: " + m.getId() + ")"); 
                break;
            }
        }

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            output.writeObject(data);
            output.flush();
            output.close();
        } catch (IOException e) {}
    } 

    public ArrayList<Movie> searchByRating(String rating) {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();
        ArrayList<Movie> ratingList = new ArrayList<Movie>();

        for (int i=0; i<data.size(); i++) {
            Movie m = data.get(i);
            if (m.getRating().equalsIgnoreCase(rating))
                ratingList.add(m);
        }
        if (ratingList.size() == 0) 
            System.out.println("Movies with such a rating does not exist");
        return ratingList;
    }

    public ArrayList<Movie> searchByTitle(String title) {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();
        ArrayList<Movie> titleList = new ArrayList<Movie>();

        for (int i=0; i<data.size(); i++) {
            Movie m = data.get(i);
            if (m.getTitle().equalsIgnoreCase(title))
                titleList.add(m);
        }
        if (titleList.size() == 0) 
            System.out.println("Movies with this title does not exist");
        return titleList;
    }

    public void addReviewMovieUsingId(int id, String username, double noOfStars, String comments) {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();
        Movie movie = searchById(id);
        data.remove(movie);
        Review newReview = new Review(username, noOfStars, comments);
        movie.addReview(newReview);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            data.add(movie);
            output.writeObject(data);
            output.flush();
            output.close();
        } catch (IOException e) {}
        removeMovieById(id);
    }

    public double getAverageStarRating(int id) {
        double averageStarRating = 0;
        double size = 0;
        Movie movie = searchById(id);
        if (movie.getReviews() != null) {
            ArrayList<Review> reviews = new ArrayList<Review>();
            reviews = movie.getReviews();
            for (Review review : reviews) {
                averageStarRating += review.getNumberOfStars();
                size++;
            }
        }
        averageStarRating /= size;
        return averageStarRating;
    }

    public ArrayList<Movie> searchByMovieType(String movieType) {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();
        ArrayList<Movie> typeList = new ArrayList<Movie>();

        for (int i=0; i<data.size(); i++) {
            Movie m = data.get(i);
            if (m.getMovieType().equalsIgnoreCase(movieType))
                typeList.add(m);
        }
        if (typeList.size() == 0) 
            System.out.println("Movies of this type does not exist");
        return typeList;

    }

    public void MovieMenuStaff(){
        int choice = 0;
        while(choice != 4){
            System.out.println("----- MOBLIMA STAFF APP -----\n");
            System.out.println(  " 1. View movies\n" +
                                 " 2. Add movie\n" +
                                 " 3. Delete movie\n" +
                                 " 4. Exit");
            System.out.println("-----------------------------\n");

            System.out.println("Please enter your choice:");

            /*
             * Check if input is an integer
             */
            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // remove newline
            }

            choice = sc.nextInt();

            switch(choice){
                case 1:
                    this.viewMovies();
                    break;
                case 2:
                    int movieID;
                    System.out.println("Please enter movie ID of movie to be deleted:");

                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input type. Please enter an integer value.");
                        sc.next(); // remove newline
                    }
                    movieID = sc.nextInt();
                    sc.nextLine();
                    this.removeMovieById(movieID);
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }

    }

    public void viewMovies(){
        int choice = 0;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        while(choice != 6){
            System.out.println("----- View Movies -----\n");
            System.out.println(  " 1. View all movies\n" +
                                 " 2. View movies by rating\n" +
                                 " 3. View movies by type\n" +
                                 " 4. Search by title\n" +
                                 " 5. Search by ID\n" + 
                                 " 6. Exit");
            System.out.println("-----------------------------\n");

            System.out.println("Please enter your choice:");

            /*
             * Check if input is an integer
             */
            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // remove newline
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    movieList = this.read();
                    for (Movie movie : movieList) {
                        System.out.println(movie.makeString());
                    }
                    break;
                case 2:
                    System.out.println("Please enter the rating:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String rating = sc.nextLine();
                    movieList = this.searchByRating(rating);
                    for (Movie movie : movieList) {
                        System.out.println(movie.makeString());
                    }
                    break;
                case 3:
                    System.out.println("Please enter the type:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String type = sc.nextLine();
                    movieList = this.searchByMovieType(type);
                    for (Movie movie : movieList) {
                        System.out.println(movie.makeString());
                    }
                    break;
                case 4:
                    System.out.println("Please enter the title:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String titleString = sc.nextLine();;
                    movieList = this.searchByTitle(titleString);
                    for (Movie movie : movieList) {
                        System.out.println(movie.makeString());
                    }
                    break;
                case 5:
                    System.out.println("Please enter the movie ID:");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    int ID = sc.nextInt();
                    Movie movie = this.searchById(ID);
                    System.out.println(movie.makeString());
                    break;
                case 6:
                    break;
            }
        }
    }
}

