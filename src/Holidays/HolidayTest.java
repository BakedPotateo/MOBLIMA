package Holidays;

import Managers.HolidayManager;

public class HolidayTest {
    public static void main(String args[]){
        HolidayManager instance = HolidayManager.getInstance();
        instance.printHolidays();

        instance.deleteHoliday();
        instance.printHolidays();
    }
    
}
