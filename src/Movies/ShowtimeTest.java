package Movies;

import java.util.ArrayList;

import Managers.MovieManager;
import Managers.ShowtimeManager;

public class ShowtimeTest {
    public static void main(String[] args) {
        ArrayList<Movie> movies = MovieManager.getInstance().read();
        ArrayList<Showtime> showtimes = ShowtimeManager.getInstance().addShowtime(movies.get(0));
        for(Showtime s : showtimes){
            s.makeString();
        }
    }
}
