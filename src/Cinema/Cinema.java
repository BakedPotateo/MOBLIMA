package Cinema;

import java.io.Serializable;

public class Cinema implements Serializable{

    //Attributes

    private String id;
    private String classOfCinema;
    private SeatingLayout layout;

    /**
     * Class constructor for the Cinema class
     * @param id The ID of the Cinema
     * @param classOfCinema The class of the Cinema
     * @param layout The seating layout of the Cinema
     */

    public Cinema(String id, String classOfCinema, SeatingLayout layout) {
        this.id = id;
        this.classOfCinema = classOfCinema;
        this.layout = layout;
    }

    // Public Methods

    /**
     * getter method to get the ID of the Cinema
     * @return int Returns the ID of the Cinema
     */
    
    public String getId() {
        return id;
    }

    /**
     * setter method to set the id of the Cinema
     * @param id The ID to assign to the Cinema 
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * getter method to get the seating layout of the Cinema
     * @return SeatingLayout Returns the seating layout of the Cinema
     */

    public SeatingLayout getLayout() {
        return layout;
    }

    /**
     * setter method to set the seating layout of the Cinema
     * @param layout The seating layout assigned to the Cinema
     */

    public void setLayout(SeatingLayout layout) {
        this.layout = layout;
    }

    /**
     * getter method to get the class of the Cinema
     * @return String Returns the class of the Cinema
     */

    public String getClassOfCinema() {
        return classOfCinema;
    }

    /**
     * setter method to set the class of the Cinema
     * @param classOfCinema the class to assign to the Cinema
     */

    public void setClassOfCinema(String classOfCinema) {
        this.classOfCinema = classOfCinema;
    }

    /**
     * This method is used to get a String of the specific details of the instance of the 
     * Cinema object that was created
     */

    public String makeString() {
        String cinemaDetails = "";
        cinemaDetails += "Cinema ID: " + getId() + "\n"
                 + "Cinema Type: " + getClassOfCinema() + "\n";
        return cinemaDetails;
    }
   
}
