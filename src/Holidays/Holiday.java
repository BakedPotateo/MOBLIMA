package Holidays;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Holiday implements Serializable{
    
    // Attributes
    
    private LocalDate startDate;
    private LocalDate endDate;
    private String holidayName;


    /**
     * Constructor of the Holiday class
     * @param startDate The day the Holiday starts
     * @param endDate The day the Holiday ends
     * @param holidayName Name of the Holiday
     */
    public Holiday(LocalDate startDate, LocalDate endDate, String holidayName){
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setHolidayName(holidayName);
    }

    // Public Methods

    /**
     * setter method to set the day the Holiday starts on
     * @param startDate The day the Holiday starts on
     */

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    /**
     * setter method to set the day the Holiday ends
     * @param endDate The day the holiday ends
     */

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    /**
     * setter method to set the name of the Holiday
     * @param holidayName The name of the Holiday
     */

    public void setHolidayName(String holidayName){
        this.holidayName = holidayName;
    }

    /**
     * getter method to get the day the Holiday starts
     * @return String The day the Holiday starts in the 'EE, dd/MM/yyyy' format 
     */

    public String getStartDateString(){
        return this.startDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }

    /**
     * getter method to get the day the Holiday ends
     * @return String The day the Holiday ends in the 'EE, dd/MM/yyyy' format
     */

    public String getEndDateString(){
        return this.endDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }

    /**
     * getter method to get the day the Holiday starts
     * @return LocalDate The day the Holiday starts
     */

    public LocalDate getStartDate(){
        return this.startDate;
    }

    /**
     * getter method to get the day the Holiday ends
     * @return LocalDate The day the Holiday ends
     */

    public LocalDate getEndDate(){
        return this.endDate;
    }

    /**
     * getter method to get the name of the Holiday
     * @return String The name of the Holiday
     */

    public String getHolidayName(){
        return this.holidayName;
    }

    /**
     * Method to print out the specific details of the instance of the Holiday
     */

    public void makeString(){
        System.out.println("Holiday name: " + this.getHolidayName());
        System.out.println("Start date:   " + this.getStartDateString());
        System.out.println("End date:     " + this.getEndDateString());
    }
}
