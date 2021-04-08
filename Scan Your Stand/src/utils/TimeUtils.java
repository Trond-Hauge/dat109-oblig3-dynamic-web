package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtils {

	public static boolean validDate(String dateString) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; //2011-12-03
		
		boolean valid = true;
		
		try {
			
			LocalDate.parse(dateString, formatter);
			
		}catch(DateTimeParseException e) {
			valid = false;
		}
		
		return valid;
	}
	
	public static boolean validTime(String timeString) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME; //10:15
		
		boolean valid = true;
		
		try {
			
			LocalTime.parse(timeString, formatter);
			
		}catch(DateTimeParseException e) {
			valid = false;
		}
		
		return valid;
	}
}
