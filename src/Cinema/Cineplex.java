package Cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {

    // Attributes

    private String name;
    private ArrayList<Cinema> cinemas;

    /**
     * Class constructor of the Cineplex
     * @param name Name of the Cineplex
     * @param cinemas A list of the Cinemas that are located within the Cineplex
     */

    public Cineplex (String name, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.cinemas = cinemas;
    }

    // Public Methods

    /**
     * getter method to get the name of the Cineplex
     * @return String Returns the name of the Cineplex
     */

    public String getName() {
        return name;
    }

    /**
     * setter method to set the name of the Cineplex
     * @param name Name of the Cineplex
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method to get the list of Cinemas in the Cineplex
     * @return ArrayList<Cinema> list of all the Cinemas in the Cineplex
     */

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    /**
     * setter method to set the list of Cinemas in the Cineplex
     * @param cinemas list of the Cinemas to set to be located in the Cineplex
     */

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    /**
     * This method is used to get a String of the specific details of the instance of the 
     * Cineplex object that was created
     * @return String Returns a String of the specific details of the Cinema
     */

    public String makeString() {
        String cinemaString = "";
        for (int i=0; i<getCinemas().size(); i++) {
            cinemaString += getCinemas().get(i).getId() + ",\n";
        }
        cinemaString = cinemaString.substring(0, cinemaString.length()-2);

        String details = "";
        details += "Name: " + getName() + "\nCinemas:\n" + cinemaString;
        return details;
    }
}
