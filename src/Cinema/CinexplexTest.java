package Cinema;

import java.util.ArrayList;
import Managers.CinemaManager;

public class CinexplexTest {
    public static void main(String[] args) {
        CinemaManager manager = CinemaManager.getInstance();
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>(); 
        SeatingLayout layout1 = new SeatingLayout(5, 10);
        SeatingLayout layout2 = new SeatingLayout(3, 15);
        SeatingLayout layout3 = new SeatingLayout(7, 8);
        Cinema cinema1 = new Cinema("E1", "Normal", layout1);
        Cinema cinema2 = new Cinema("E2", "Normal", layout2);
        Cinema cinema3 = new Cinema("E3", "Normal", layout3);
        cinemas.add(cinema1);
        cinemas.add(cinema2);
        cinemas.add(cinema3);
        // manager.createCineplex("Shaws Theatre", cinemas);
        // manager.createCineplex("Golden Village", cinemas);
        manager.createCineplex("EagleWings", cinemas);
        // manager.createCinema("Golden Village", "G1", "Gold Class", layout3);
        // manager.createCinema("Shaws Theatre", "S1", "IMAX", layout3);
        // ArrayList<Cineplex> cineplexes = manager.read();
        // for (Cineplex cineplex : cineplexes) {
        //     System.out.println(cineplex.makeString());
        // }
        manager.viewCineplexes();
    }
}
