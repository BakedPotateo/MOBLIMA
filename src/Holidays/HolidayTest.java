package Holidays;

import java.time.LocalDate;
import java.util.Scanner;

import Managers.HolidayManager;

public class HolidayTest {

    public static void main(String args[]){
        HolidayManager instance = HolidayManager.getInstance();
        Scanner sc = new Scanner(System.in);
    
        while(true){
            String holidayName;
            LocalDate start;
            LocalDate end;
            System.out.println("hol name:");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // Remove newline character
            }
            holidayName = sc.nextLine();
            System.out.println("start:");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // Remove newline character
            }
            start = LocalDate.parse(sc.nextLine());
            System.out.println("end:");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again!");
                sc.next(); // Remove newline character
            }
            end = LocalDate.parse(sc.nextLine());
            instance.createHoliday(start, end, holidayName);
            int breaker;
            breaker = sc.nextInt();
            if(breaker == 1)
                break;
        }

        instance.printHolidays();
    }
    
}
