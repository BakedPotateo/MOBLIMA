package Movies;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import utils.ProjectRootPathFinder;

public class MovieController {

    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/SC2002-Project-MOBLIMA/Database/Movies/movies.txt";

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

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            data.add(movie);
            output.writeObject(data);
            output.flush();
            output.close();
        } catch (IOException e) {}
        // gotta make sure conflicting IDs don't happen TODO
    }

    public Movie searchById(int id) {
        ArrayList<Movie> data = new ArrayList<Movie>();
        data = read();

        for (int i=0; i<data.size(); i++) {
            Movie m = data.get(i);
            if (m.getId() == id)
                return m;
        }
        System.out.println("Movie ID entered does not exist...\n");
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
}

