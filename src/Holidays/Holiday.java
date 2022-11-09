package Holidays;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Holiday implements Serializable{
    // Attributes
    private LocalDate startDate;
    private LocalDate endDate;
    private String holidayName;


    // Constructor
    public Holiday(LocalDate startDate, LocalDate endDate, String holidayName){
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setHolidayName(holidayName);
    }

    // Methods
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setHolidayName(String holidayName){
        this.holidayName = holidayName;
    }

    public String getStartDateString(){
        return this.startDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }

    public String getEndDateString(){
        return this.endDate.format(DateTimeFormatter.ofPattern("EE, dd/MM/yyyy"));
    }

    public LocalDate getStartDate(){
        return this.startDate;
    }

    public LocalDate getEndDate(){
        return this.endDate;
    }

    public String getHolidayName(){
        return this.holidayName;
    }

    public void makeString(){
        System.out.println("Holiday name: " + this.getHolidayName());
        System.out.println("Start date:   " + this.getStartDateString());
        System.out.println("End date:     " + this.getEndDateString());
    }
}
