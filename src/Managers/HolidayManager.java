package Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Holidays.Holiday;
import Movies.Showtime;
import utils.ProjectRootPathFinder;
import utils.ValidDateChecker;
/**
 * Manager class to manage holidays
 */
public class HolidayManager {
    /**
     * instance checks whether HolidayManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static HolidayManager instance = null;

    /**
     * The file name of the database file that this manager will access
     */
    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Settings/holidays.txt";
    public Scanner sc = new Scanner(System.in);

    /**
     * Class constructor creates base data for holidays
     */
    private HolidayManager(){
        this.createHoliday(LocalDate.parse("2022-12-25"), LocalDate.parse("2022-12-25"), "Christmas Day (2022)");
        this.createHoliday(LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-01"), "New Year's Day");
        this.createHoliday(LocalDate.parse("2023-01-22"), LocalDate.parse("2023-01-22"), "Chinese New Year");
        this.createHoliday(LocalDate.parse("2023-01-23"), LocalDate.parse("2023-01-24"), "Chinese New Year holiday");
        this.createHoliday(LocalDate.parse("2023-04-07"), LocalDate.parse("2023-04-07"), "Good Friday");
        this.createHoliday(LocalDate.parse("2023-04-22"), LocalDate.parse("2023-04-22"), "Hari Raya Puasa");
        this.createHoliday(LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-01"), "Labour Day");
        this.createHoliday(LocalDate.parse("2023-06-02"), LocalDate.parse("2023-06-02"), "Vesak Day");
        this.createHoliday(LocalDate.parse("2023-06-29"), LocalDate.parse("2023-06-29"), "Hari Raya Haji");
        this.createHoliday(LocalDate.parse("2023-08-09"), LocalDate.parse("2023-08-09"), "National Day");
        this.createHoliday(LocalDate.parse("2023-11-12"), LocalDate.parse("2023-11-12"), "Deepavali");
        this.createHoliday(LocalDate.parse("2023-11-13"), LocalDate.parse("2023-11-13"), "Deepavali holiday");
        this.createHoliday(LocalDate.parse("2023-12-25"), LocalDate.parse("2023-12-25"), "Christmas Day (2023)");
    }

    /*
     * getInstance checks if HolidayManager has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static HolidayManager getInstance() {
        if (instance == null) 
            instance = new HolidayManager(); 
        return instance;
    }

    /**
     * Function to read database for holidays
     * @return  ArrayList of holidays
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Holiday> read() {
        try {
            ObjectInputStream objectinputstream = new ObjectInputStream(new FileInputStream(FILE));
            ArrayList<Holiday> holidayList = (ArrayList<Holiday>) objectinputstream.readObject();
            objectinputstream.close();
            return holidayList;
        } catch (ClassNotFoundException | IOException e) {}
        return new ArrayList<Holiday>();
    }

    /**
     * Method to create a new holiday
     * @param startDate    Start date of holiday
     * @param endDate      End date of holiday
     * @param holidayName  Name of holiday
     * New holiday is written directly to database
     */
    public void createHoliday(LocalDate startDate, LocalDate endDate, String holidayName) {
        Holiday newHoliday = new Holiday(startDate, endDate, holidayName);
        ArrayList<Holiday> holidays = new ArrayList<Holiday>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            holidays = read();
        if (!holidayExists(holidays, holidayName)) try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            holidays.add(newHoliday);
            output.writeObject(holidays);
            output.flush();
            output.close();
        } catch (IOException e) {}
    }

    /**
     * Method to check if holiday already exists to avoid duplicates
     * @param holidays        Existing holiday
     * @param newHolidayName  New holiday
     * @return true if holiday already exists, else false
     */
    private boolean holidayExists(ArrayList<Holiday> holidays, String newHolidayName){
        for(Holiday holiday : holidays){
            if(holiday.getHolidayName().equals(newHolidayName) && holiday.getHolidayName().equals(newHolidayName)) 
                return true;
        }
        return false;
    }

    /**
     * Method to print all holidays, including their name, start and end date
     */
    public void printHolidays(){
        ArrayList<Holiday> holidays = read();
        for(Holiday holiday : holidays){
            holiday.makeString();
            System.out.println();
        }
    }

    /**
     * Method to add a holiday
     * Data written directly to database
     */
    public void addHoliday(){
        System.out.println("Enter name of holiday to add: ");
        while (!sc.hasNext()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        String holidayName = sc.nextLine();
        String startDate;
        String endDate;
        System.out.println("Enter start date (YYYY-MM-DD): ");
        while(true){
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // Remove newline character
            }
            startDate = sc.nextLine();
            if(ValidDateChecker.isValidDate(startDate)) break;
        }
        
        while(true){
            System.out.println("Enter end date (YYYY-MM-DD): ");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // Remove newline character
            }
            endDate = sc.nextLine();
            if(ValidDateChecker.isValidDate(endDate)) break;
        }
        
        ArrayList<Holiday> holidaysArr = new ArrayList<Holiday>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            holidaysArr = read();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            holidaysArr.add(new Holiday(LocalDate.parse(startDate), LocalDate.parse(endDate), holidayName));
            output.writeObject(holidaysArr);
            output.flush();
            output.close();
        } catch (IOException e) {}
    }

    /**
     * Method to delete a holiday
     */
    public void deleteHoliday(){
        System.out.println("Enter name of holiday to delete: ");
        while (!sc.hasNext()) {
            System.out.println("Invalid input type. Please try again!");
            sc.next(); // Remove newline character
        }
        String holidayName = sc.nextLine();
        ArrayList<Holiday> holidaysArr = new ArrayList<Holiday>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            holidaysArr = read();
        int i;
        for(i = 0; i < holidaysArr.size(); i++){
            if(holidaysArr.get(i).getHolidayName().equals(holidayName)){
                holidaysArr.remove(i);
                break;
            }
        }
        if (i == holidaysArr.size())
            System.out.println("Holiday not found!");
        else try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            output.writeObject(holidaysArr);
            output.flush();
            output.close();
        } catch (IOException e) {}
    }

    /**
     * Checks if a Showtime is on a public holiday
     * @param showtime  Showtime to be checked
     * @return true if showtime is on public holiday, else false
     */
    public boolean isPublicHoliday(Showtime showtime){
        LocalDateTime dateTime = showtime.getDateTime();
        LocalDate date = dateTime.toLocalDate();
        ArrayList<Holiday> holidays = this.read();
        for(Holiday holiday : holidays){
            LocalDate startDate = holiday.getStartDate();
            LocalDate endDate = holiday.getEndDate();
            if(date.isAfter(startDate.minusDays(1)) && date.isBefore(endDate.plusDays(1))) return true;
        }
        return false;
            
    }
}
