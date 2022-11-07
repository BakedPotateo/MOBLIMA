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

    public String getStartDate(){
        return this.startDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public String getEndDate(){
        return this.endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public String getHolidayName(){
        return this.holidayName;
    }
}
