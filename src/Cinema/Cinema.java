package Cinema;

import java.io.Serializable;
/**
 * Cinema class 
 */
public class Cinema implements Serializable{
    private String id;
    private String classOfCinema;
    private SeatingLayout layout;

    public Cinema(String id, String classOfCinema, SeatingLayout layout) {
        this.id = id;
        this.classOfCinema = classOfCinema;
        this.layout = layout;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SeatingLayout getLayout() {
        return layout;
    }

    public void setLayout(SeatingLayout layout) {
        this.layout = layout;
    }

    public String getClassOfCinema() {
        return classOfCinema;
    }
    public void setClassOfCinema(String classOfCinema) {
        this.classOfCinema = classOfCinema;
    }

    public String makeString() {
        String cinemaDetails = "";
        cinemaDetails += "Cinema ID: " + getId() + "\n"
                 + "Cinema Type: " + getClassOfCinema() + "\n";
        return cinemaDetails;
    }
   
}
