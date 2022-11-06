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
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            System.out.println("Movie with conflicting ID already exists.");
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
            System.out.println("Movie with conflicting ID already exists.");
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
            System.out.println("Movies with such a rating do not exist.");
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
            System.out.println("Movies with this title do not exist.");
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
            System.out.println("Movies of this type do not exist.");
        return typeList;

    }

    public void MovieMenuStaff(){
        int choice = 0;
        while(choice != 4){
            System.out.println("----- MOBLIMA STAFF APP -----\n"
                              +" 1. View movies\n"
                              +" 2. Add movie\n"
                              +" 3. Delete movie\n"
                              +" 4. Edit movie\n"
                              +" 5. Exit\n"
                              +"-----------------------------");

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
                this.editMovie();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-5.");
                    break;
            }
        }

    }

    public void viewMovies(){
        int choice = 0;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        while(choice != 6){
            System.out.println("-------- View Movies --------\n"
                              +" 1. View all movies\n"
                              +" 2. View movies by rating\n"
                              +" 3. View movies by type\n"
                              +" 4. Search by title\n"
                              +" 5. Search by ID\n" 
                              +" 6. Exit\n"
                              +"-----------------------------");

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
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-6.");
                    break;
            }
        }
    }

    public void editMovie(){
        int choice = 0;
        while(choice != 3){
            System.out.println("----- EDIT MOVIE -----\n"
                              +" 1. Search by title\n"
                              +" 2. Search by ID\n"
                              +" 3. Exit\n"
                              +"-----------------------------");

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
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3.");
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
        System.out.println("Selected movie:\n");
        System.out.println(m.makeString());
        System.out.println();

        int choice = 0;
        while(choice != 12){
            System.out.println("-------- EDIT MOVIE --------\n"
                              +"1.  Edit Title\n"
                              +"2.  Edit Type\n"
                              +"3.  Edit Synopsis\n"
                              +"4.  Edit Rating\n"
                              +"5.  Edit Director\n"
                              +"6.  Edit Cast\n"
                              +"7.  Edit Duration\n"
                              +"8.  Edit Release Date\n"
                              +"9.  Edit End Date\n"
                              +"10. Edit Reviews\n"
                              +"11. Preview movie\n"
                              +"12. Save and exit\n"
                              +"----------------------------");

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
                    m.setTitle(this.editTitle());
                    break;
                case 2:
                    m.setMovieType(this.editType());
                    break;
                case 3:
                    m.setSynopsis(this.editSynopsis());
                    break;
                case 4:
                    m.setRating(this.editRating());
                    break;
                case 5:
                    m.setDirector(this.editDirector());;
                    break;
                case 6:
                    m.setCast(this.editCast(m));
                    break;
                case 7:
                    m.setDuration(editDuration());
                    break;
                case 8:
                    m.setReleaseDate(this.editReleaseDate());
                    break;
                case 9:
                    m.setEndDate(this.editEndDate());
                    break;
                case 10:
                    m.addReview(this.editReviews());
                    break;
                case 11:
                    System.out.println(m.makeString());
                    break;
                case 12:
                    System.out.println("Movie details updated!");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-12.");
                    break;
            }
        }
        this.removeMovieById(ID);
        this.createNewMovie(m);
    }

    private void editByTitle() {
        ArrayList<Movie> movieList;
        sc.nextLine();
        while(true){
            System.out.println("Please enter the movie title:");
            /*
            * Check if input is a string
            */
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // remove newline
            }
            String title = sc.nextLine();
            
            movieList = this.searchByTitle(title);
            if(movieList.size() != 0)
                break;
            else
                continue;
        }
        Movie m;
        /*
         * Check for multiple similar titles (Same title but different movie type)
         */
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

        System.out.println("Selected movie:\n");
        System.out.println(m.makeString());
        System.out.println();
        int choice = 0;
        while(choice != 12){
            System.out.println("-------- EDIT MOVIE --------\n"
                              +"1.  Edit Title\n"
                              +"2.  Edit Type\n"
                              +"3.  Edit Synopsis\n"
                              +"4.  Edit Rating\n"
                              +"5.  Edit Director\n"
                              +"6.  Edit Cast\n"
                              +"7.  Edit Duration\n"
                              +"8.  Edit Release Date\n"
                              +"9.  Edit End Date\n"
                              +"10. Edit Reviews\n"
                              +"11. Preview movie\n"
                              +"12. Save and exit\n"
                              +"----------------------------");

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
                    m.setTitle(this.editTitle());
                    break;
                case 2:
                    m.setMovieType(this.editType());
                    break;
                case 3:
                    m.setSynopsis(this.editSynopsis());
                    break;
                case 4:
                    m.setRating(this.editRating());
                    break;
                case 5:
                    m.setDirector(this.editDirector());;
                    break;
                case 6:
                    m.setCast(this.editCast(m));
                    break;
                case 7:
                    m.setDuration(editDuration());
                    break;
                case 8:
                    m.setReleaseDate(this.editReleaseDate());
                    break;
                case 9:
                    m.setEndDate(this.editEndDate());
                    break;
                case 10:
                    m.addReview(this.editReviews());
                    break;
                case 11:
                    System.out.println(m.makeString());
                    break;
                case 12:
                    System.out.println("Movie details updated!");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-12");
                    break;
            }
        }
        this.removeMovieById(m.getId());
        this.createNewMovie(m);
    }

    /*
     * Functions to edit movies
     */

    private String editTitle(){
        System.out.println("Please enter the new title:");
        while (!sc.hasNext()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        String newTitle = sc.nextLine();
        return newTitle;
    }

    private String editType(){
        System.out.println("Please enter the new type:");
        while (!sc.hasNext()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        String newType = sc.nextLine();
        return newType;
    }

    private String editSynopsis(){
        System.out.println("Please enter the new synopsis:");
        while (!sc.hasNext()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        String newSynopsis = sc.nextLine();
        return newSynopsis;
    }

    private String editRating(){
        System.out.println("Please enter the new rating:");
        while (!sc.hasNext()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        String newRating = sc.nextLine();
        return newRating;
    }

    private String editDirector(){
        System.out.println("Please enter the new director:");
        while (!sc.hasNext()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        String newDirector = sc.nextLine();
        return newDirector;
    }

    private ArrayList<String> editCast(Movie movie){
        ArrayList<String> Cast = movie.getCast();
        System.out.println("Current cast members:");
        for(String c : Cast)
            System.out.println(c);
        
        System.out.println();
        System.out.println("--------- EDIT CAST ---------\n"
                          +"1.  Add cast member\n"
                          +"2.  Delete cast member\n"
                          +"3.  Save\n"
                          +"----------------------------");
        System.out.println("Please enter your choice:");

        /*
        * Check if input is an integer
        */
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // remove newline
        }

        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice){
            case 1:
                System.out.println("Please enter the new cast member:");
                while (!sc.hasNext()) {
                    System.out.println("Invalid input type. Please try again!");
                    sc.next(); // Remove newline character
                }
                String newCastString = sc.nextLine();
                Cast.add(newCastString);
                movie.setCast(Cast);
                break;
            case 2:
                boolean loop = true;
                while(loop){
                    System.out.println("Please enter the cast member to delete:");
                    while (!sc.hasNext()) {
                        System.out.println("Invalid input type. Please try again!");
                        sc.next(); // Remove newline character
                    }
                    String castToDel = sc.nextLine();
                    int i = 0;
                    for(String cast : Cast){
                        if(cast.equals(castToDel))
                            break;
                        else i++;
                    }
                    if (i < Cast.size()){
                        Cast.remove(i);
                        loop = false;
                    }
                    else
                        System.out.println("Cast member not found.");
                }
                break;
            case 3:
                System.out.println("Saved successfully.");
                break;
            default:
                System.out.println("Please enter an integer between 1-3.");
                break;
        }
        return Cast;
    }

    private Double editDuration(){
        System.out.println("Please enter the new duration:");
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        double newDuration = sc.nextDouble();
        return newDuration;
    }

    private LocalDate editReleaseDate(){
        String newReleaseDateString;
        while(true){
            System.out.println("Enter the new release date in the format yyyy-mm-dd:");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // Remove newline character
            }
            newReleaseDateString = sc.nextLine();
            if(isValidDate(newReleaseDateString))
                break;
            else
                System.out.println("Invalid date format!");
        }
        LocalDate newReleaseDate = LocalDate.parse(newReleaseDateString);
        return newReleaseDate;
    }

    private LocalDate editEndDate(){
        String newEndDateString;
        while(true){
            System.out.println("Enter the new release date in the format yyyy-mm-dd:");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // Remove newline character
            }
            newEndDateString = sc.nextLine();
            if(isValidDate(newEndDateString))
                break;
            else
                System.out.println("Invalid date format!");
        }
        LocalDate newEndDate = LocalDate.parse(newEndDateString);
        return newEndDate;
    }

    private Review editReviews(){
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
        return newReview;
    }

    /*
     * Helper tool to check if inputted date is in the correct format
     */
    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public void showTop5(){
        int choice = 0;
        while(choice != 3){
            System.out.println("------- TOP 5 MOVIES -------\n"
                              +" 1. Get top 5 by sales\n"
                              +" 2. Get top 5 by ratings\n"
                              +" 3. Exit\n"
                              +"----------------------------\n");
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
                    this.topSales();
                    break;
                case 2:
                    this.topRatings();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3.");
                    break;
            }
        }
    }

    private void topSales(){
        System.out.println("Top 5 movies by sales:");
        ArrayList<Movie> m = this.read();
        m.sort((m1, m2) -> Integer.compare(m1.getSales(),m2.getSales()));
        int i = 0;
        for(Movie movie : m){
            if (i < 5)
                System.out.println(movie.makeString());
            i++;
        } 
    }

    private void topRatings(){
        System.out.println("Top 5 movies by ratings:");
        ArrayList<Movie> m = this.read();
        m.sort((m1, m2) -> Double.compare(this.getAverageStarRating(m1.getId()),(this.getAverageStarRating(m2.getId()))));
        int i = 0;
        for(Movie movie : m){
            if (i < 5)
                System.out.println(movie.makeString());
            i++;
        }
    }
}

