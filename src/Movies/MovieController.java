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

    public void createNewMovie(String title, String movieType, String synopsis, int rating, String director, ArrayList<String> cast, double duration, LocalDate releaseDate, LocalDate endDate){
        Movie movie = new Movie(title, movieType, synopsis, rating, director, cast, duration, releaseDate, endDate);
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
}

