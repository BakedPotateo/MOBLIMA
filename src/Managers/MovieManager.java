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

    public void createNewMovie(Movie movie){
        int id = movie.getId();
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
            System.out.println("Movies with this title do not exist");
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
                                 " 4. Edit movie\n" +
                                 " 5. Exit");
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
                    break;
                case 3:
                    int movieID;
                    System.out.println("Please enter movie ID of movie to be deleted:");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input type. Please enter an integer value.");
                        sc.next(); // remove newline
                    }
                    movieID = sc.nextInt();
                    this.removeMovieById(movieID);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please enter an integer between 1-5");
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
                default:
                    System.out.println("Please enter an integer between 1-6");
                    break;
            }
        }
    }

    public void editMovie(){
        int choice = 0;
        while(choice != 3){
            System.out.println("----- EDIT MOVIE -----\n");
            System.out.println(  " 1. Search by title\n" +
                                 " 2. Search by ID\n" +
                                 " 3. Exit\n");
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
                    this.editByTitle();
                    break;
                case 2:
                    this.editByID();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3");
                    break;
            }
        }
    }

    private void editByID() {
        System.out.println("Please enter the movie ID:");

        /*
        * Check if input is an integer
        */
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // remove newline
        }
        int ID = sc.nextInt();
        Movie m = this.searchById(ID);

        int choice = 0;
        while(choice != 12){
            System.out.println("--------- EDIT MOVIE ---------\n");
            System.out.println(  "1.  Title\n"
                                +"2.  Type\n"
                                +"3.  Synopsis\n"
                                +"4.  Rating\n"
                                +"5.  Director\n"
                                +"6.  Cast\n"
                                +"7.  Duration\n"
                                +"8.  Release Date\n"
                                +"9.  End Date\n"
                                +"10. Reviews\n"
                                +"11. Preview movie\n"
                                +"12. Save and exit");
            System.out.println("----------------------------\n");

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
                    System.out.println("Please enter the new title");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newTitle = sc.nextLine();
                    m.setTitle(newTitle);
                    break;
                case 2:
                    System.out.println("Please enter the new type");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newType = sc.nextLine();
                    m.setMovieType(newType);
                    break;
                case 3:
                    System.out.println("Please enter the new synopsis");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newSynopsis = sc.nextLine();
                    m.setSynopsis(newSynopsis);
                    break;
                case 4:
                    System.out.println("Please enter the new rating");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newRating = sc.nextLine();
                    m.setRating(newRating);
                    break;
                case 5:
                    System.out.println("Please enter the new director");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newDirector = sc.nextLine();
                    m.setDirector(newDirector);;
                    break;
                case 6:
                // cast
                    break;
                case 7:
                    System.out.println("Please enter the new duration");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    double newDuration = sc.nextDouble();
                    m.setDuration(newDuration);
                    break;
                case 8:
                // release date
                    break;
                case 9:
                // end date
                    break;
                case 10:
                    System.out.println("Enter the username:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String username = sc.nextLine();
                    System.out.println("Enter the number of stars:");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    Double numberofstars = sc.nextDouble();
                    System.out.println("Enter the comments:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String comments = sc.nextLine();
                    Review newReview = new Review(username, numberofstars, comments);
                    m.addReview(newReview);
                    break;
                case 11:
                    System.out.println(m.makeString());
                    break;
                case 12:
                    System.out.println("Movie details updated!");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-13");
                    break;
            }
        }
        this.removeMovieById(ID);
        this.createNewMovie(m);
    }

    private void editByTitle() {
        System.out.println("Please enter the movie title:");

        /*
        * Check if input is an integer
        */
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // remove newline
        }
        String title = sc.nextLine();
        ArrayList<Movie> movieList = this.searchByTitle(title);
        Movie m;
        if(movieList.size() > 1){
            System.out.println("Multiple titles detected. Please choose which movie to edit:");
            int i = 1;
            for(Movie movie : movieList){
                System.out.println(i + ". " + movie.getTitle());
                i++;
            }
            System.out.println();

            int movieChoice = 0;
            while (true){
                System.out.println("Please enter your choice:");
                /*
                 * Check if input is an integer
                 */
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input type. Please enter an integer value.");
                    sc.next(); // remove newline
                }
    
                movieChoice = sc.nextInt();
                if (movieChoice <= i && movieChoice > 0)
                    break;
                else
                    System.out.println("Please enter a number between 1-" + i);
                sc.nextLine();
            }
            m = movieList.get(movieChoice - 1);
        }
        else
            m = movieList.get(0);
        int choice = 0;
        while(choice != 12){
            System.out.println("--------- EDIT MOVIE ---------\n");
            System.out.println(  "1.  Title\n"
                                +"2.  Type\n"
                                +"3.  Synopsis\n"
                                +"4.  Rating\n"
                                +"5.  Director\n"
                                +"6.  Cast\n"
                                +"7.  Duration\n"
                                +"8.  Release Date\n"
                                +"9.  End Date\n"
                                +"10. Reviews\n"
                                +"11. Preview movie\n"
                                +"12. Save and exit");
            System.out.println("----------------------------\n");

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
                    System.out.println("Please enter the new title");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newTitle = sc.nextLine();
                    m.setTitle(newTitle);
                    break;
                case 2:
                    System.out.println("Please enter the new type");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newType = sc.nextLine();
                    m.setMovieType(newType);
                    break;
                case 3:
                    System.out.println("Please enter the new synopsis");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newSynopsis = sc.nextLine();
                    m.setSynopsis(newSynopsis);
                    break;
                case 4:
                    System.out.println("Please enter the new rating");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newRating = sc.nextLine();
                    m.setRating(newRating);
                    break;
                case 5:
                    System.out.println("Please enter the new director");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String newDirector = sc.nextLine();
                    m.setDirector(newDirector);;
                    break;
                case 6:
                // cast
                    break;
                case 7:
                    System.out.println("Please enter the new duration");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    double newDuration = sc.nextDouble();
                    m.setDuration(newDuration);
                    break;
                case 8:
                // release date
                    break;
                case 9:
                // end date
                    break;
                case 10:
                    System.out.println("Enter the username:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String username = sc.nextLine();
                    System.out.println("Enter the number of stars:");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    Double numberofstars = sc.nextDouble();
                    System.out.println("Enter the comments:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String comments = sc.nextLine();
                    Review newReview = new Review(username, numberofstars, comments);
                    m.addReview(newReview);
                    break;
                case 11:
                    System.out.println(m.makeString());
                    break;
                case 12:
                    System.out.println("Movie details updated!");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-13");
                    break;
            }
        }
        this.removeMovieById(m.getId());
        this.createNewMovie(m);
    }

}

