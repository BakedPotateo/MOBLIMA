package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidDateChecker {
    /*
     * Helper tool to check if inputted date is in the correct format
     */
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
