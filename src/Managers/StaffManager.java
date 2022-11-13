package Managers;

import utils.ProjectRootPathFinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Manager class to handle staff login issues
 */
public class StaffManager {
    // Attributes

    /**
     * instance checks whether StaffManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static StaffManager instance = null;

    /**
     * Empty class constructor
     */
    private StaffManager(){}

    // Public methods

    /**
     * getInstance checks if StaffManager has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static StaffManager getInstance()
    {
        if (instance == null)
            instance = new StaffManager(); // instance is a static variable
        return instance;
    }

    /**
     * Method to check if login credentials are correct
     * @param username  inputted username
     * @param password  inputted password
     * @return true if username and password are correct, false if either is wrong
     */
    public boolean login(String username, String password){
        try{
            String filepath = ProjectRootPathFinder.findProjectRootPath() + "/Database/Staff/StaffAccounts.csv";
            // System.out.println(filepath);
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String StaffAccountLine;
            while((StaffAccountLine = br.readLine()) != null){
                String[] lineValues = StaffAccountLine.split(",");
                if(lineValues[0].equals(username) && lineValues[1].equals(password)){
                    br.close();
                    return true;
                }
            }
            br.close();
	        return false;
        } 
        catch(FileNotFoundException e){
			System.out.println( "Error opening the input file!" + e.getMessage() );
			System.exit( 0 );
		} 
        
        catch (IOException e) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
		}	
        return false;
    }
}
