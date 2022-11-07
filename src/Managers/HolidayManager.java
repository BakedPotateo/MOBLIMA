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

    private HolidayManager(){}

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
            output.writeObject(newHoliday);
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

    public void printHolidays(){
        ArrayList<Holiday> holidays = new ArrayList<Holiday>();
        holidays = read();
        for(Holiday holiday : holidays){
            System.out.println("Name: " + holiday.getHolidayName());
            System.out.println("Start: " + holiday.getStartDate());
            System.out.println("End: " + holiday.getEndDate());
            System.out.println();
        }
    }
}
