package Staff;


public class Settings{
    // Attributes

    /*
     * instance checks whether Settings has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static Settings instance = null;

    /*
     * Empty class constructor
     */
    private Settings(){}

    // Public methods

    /*
     * getInstance checks if Settings has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static Settings getInstance()
    {
        if (instance == null)
            instance = new Settings(); // instance is a static variable
        return instance;
    }

    public void configTicketPrices(){}

    public void configHolidays(){}


    
}
