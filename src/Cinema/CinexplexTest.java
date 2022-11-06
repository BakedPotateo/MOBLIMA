package Cinema;

import java.util.ArrayList;
import Managers.CinemaManager;

public class CinexplexTest {
    public static void main(String[] args) {
        CinemaManager manager = CinemaManager.getInstance();
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>(); 
        SeatingLayout layout = new SeatingLayout(5, 10);
        Cinema cinema1 = new Cinema("H1", "Normal", layout);
        Cinema cinema2 = new Cinema("H2", "Normal", layout);
        Cinema cinema3 = new Cinema("H3", "IMAX", layout);
        cinemas.add(cinema1);
        cinemas.add(cinema2);
        cinemas.add(cinema3);
        manager.createCineplex("Shaws Theatre", cinemas);
        // manager.createCinema("Golden Village", "G1", "Gold Class", layout);
        manager.createCinema("Shaw Theatres", "S1", "IMAX", layout);
        ArrayList<Cineplex> cineplexes = manager.read();

        for (Cineplex cineplex : cineplexes) {
            System.out.println(cineplex.makeString());
        }
    }
}
