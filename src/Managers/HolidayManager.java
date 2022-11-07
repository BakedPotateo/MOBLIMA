package Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import Holidays.Holiday;
import utils.ProjectRootPathFinder;

public class HolidayManager {
    public static HolidayManager instance = null;
    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Settings/holidays.txt";

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

    public static HolidayManager getInstance() {
        if (instance == null) 
            instance = new HolidayManager(); 
        return instance;
    }

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

    // holidayExists checks if holiday already exists, used to make sure duplicate holidays are not created
    
    private boolean holidayExists(ArrayList<Holiday> holidays, String newHolidayName){
        for(Holiday holiday : holidays){
            if(holiday.getHolidayName().equals(newHolidayName) && holiday.getHolidayName().equals(newHolidayName)) 
                return true;
        }
        return false;
    }

    public ArrayList<Holiday> printHolidays(){
        ArrayList<Holiday> holidays = read();
        return holidays;
    }
}
