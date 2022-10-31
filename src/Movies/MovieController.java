package Movies;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MovieController {
    public final static String FILE = "C:/Users/User/SC2002 Project/SC2002-Project-MOBLIMA/Database/movies.txt";

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
                Movie m = data.get(i);
                data.remove(i);
                System.out.println("MOVIE REMOVED: " + m.getTitle() + " (ID: " + m.getId() + ")"); 
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


}

