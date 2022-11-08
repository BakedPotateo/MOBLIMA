package Managers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Cinema.*;
import utils.ProjectRootPathFinder;

public class CinemaManager {
    public static CinemaManager instance = null;
    private CinemaManager(){}
    
    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Cineplex/cineplexes.txt";

    public static CinemaManager getInstance()
    {
        if (instance == null)
            instance = new CinemaManager(); // instance is a static variable
        return instance;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Cineplex> read() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE));
            ArrayList<Cineplex> cineplexes = (ArrayList<Cineplex>) objectInputStream.readObject();
            objectInputStream.close();
            return cineplexes;
        } catch (ClassNotFoundException | IOException e) {}
        return new ArrayList<Cineplex>();
    }

    public void createCineplex(String name, ArrayList<Cinema> cinemas) {
        Cineplex cineplex = new Cineplex(name, cinemas);
        ArrayList<Cineplex> data = new ArrayList<Cineplex>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            data = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE));
            data.add(cineplex);
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {}
    }

    public Cineplex searchCineplexByName(String name) {
        ArrayList<Cineplex> data = read();
        for (int i=0; i<data.size(); i++) {
            Cineplex tempCineplex = data.get(i);
            if (tempCineplex.getName().equalsIgnoreCase(name))
                return tempCineplex;
        }
        return null;
    }

    public void deleteCineplexByName(String name) {
        ArrayList<Cineplex> data = read();

        for (int i=0; i<data.size(); i++) {
            Cineplex tempCineplex = data.get(i);
            if (tempCineplex.getName().equalsIgnoreCase(name)) {
                data.remove(tempCineplex);
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

    public void updateCineplexName(String oldName, String newName) {
        Cineplex cineplex = searchCineplexByName(oldName);
        cineplex.setName(newName);
        deleteCineplexByName(oldName);
        createCineplex(cineplex.getName(), cineplex.getCinemas());
    }

    public void createCinema(String cineplexName, String id, String classOfCinema, SeatingLayout layout) {
        Cinema cinema = new Cinema(id, classOfCinema, layout);
        ArrayList<Cineplex> data = read();
        for (int i=0; i<data.size(); i++) {
            Cineplex cineplex = data.get(i);
            data.remove(i);
            if (cineplex.getName().equalsIgnoreCase(cineplexName)) {
                ArrayList<Cinema> cinemas = cineplex.getCinemas();
                cinemas.add(cinema);
                cineplex.setCinemas(cinemas);
                createCineplex(cineplexName, cineplex.getCinemas());
                deleteCineplexByName(cineplexName);
                break;
            }
        }
    }

    public ArrayList<Cinema> readCinemas() {
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexes = read();
        
        for (int i=0; i<cineplexes.size(); i++) {
            Cineplex cineplex = cineplexes.get(i);
            for (Cinema cinema : cineplex.getCinemas())
                cinemas.add(cinema);
        }
        return cinemas;
    }

    public ArrayList<Cinema> getCinemasByCineplex(String name) {
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexes = read();
        Cineplex cineplex = null;

        for (int i=0; i<cineplexes.size(); i++) {
            cineplex = cineplexes.get(i);
            if (cineplex.getName().equalsIgnoreCase(name)) {
                for (Cinema cinema : cineplex.getCinemas()) {
                    cinemas.add(cinema);
                }
            }
        }

        return cinemas;
    }

    public Cinema searchCinema(String code) {
        ArrayList<Cineplex> data = read();
        for (int i=0; i<data.size(); i++) {
            Cineplex cineplex = data.get(i);
            ArrayList<Cinema> cinemas = cineplex.getCinemas();
            for (int j=0; j<cinemas.size(); j++) {
                Cinema cinema = cinemas.get(j);
                if (cinema.getId().equals(code)) {
                    return cinema;
                }
            }
        }
        return null;
    }
 
    public void deleteCinema(String code) {
        ArrayList<Cineplex> data = read();
        for (int i=0; i<data.size(); i++) {
            Cineplex cineplex = data.get(i);

            if (cineplex.getCinemas().size() > 3) {
                ArrayList<Cinema> cinemas = cineplex.getCinemas();
                
                for (int j=0; j<cinemas.size(); j++) {
                    Cinema cinema = cinemas.get(j);
                    if (cinema.getId().equals(code)) {
                        cinemas.remove(j);
                        cineplex.setCinemas(cinemas);
                        createCineplex(cineplex.getName(), cineplex.getCinemas());
                        deleteCineplexByName(cineplex.getName());
                        return;
                    }
                }
            }
        }
    }

}
