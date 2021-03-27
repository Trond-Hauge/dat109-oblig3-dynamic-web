package utils;

public class Validator {
	
	public static boolean validatePhoneNumber(String phoneNumber) {
		return phoneNumber != null && phoneNumber.matches("^[1-9]{1}[0-9]{7}$");
	}

}
