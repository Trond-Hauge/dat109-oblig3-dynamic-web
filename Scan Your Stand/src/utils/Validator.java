package utils;

public class Validator {
	
	public static boolean validPhoneNumber(String phoneNumber) {
		return phoneNumber != null && phoneNumber.matches("^[1-9]{1}[0-9]{7}$");
	}
	
	public static boolean validProjectName(String projectName) {
		return projectName != null && projectName.matches("^[a-zæøåA-ZÆØÅ0-9]{1}[a-zæøåA-ZÆØÅ0-9 ]{2,19}$");
	}
	
	public static boolean validProjectNumber(String projectNumber) {
		return projectNumber != null && projectNumber.matches("^[A-Z]{1}[0-9]{2}$");
	}

}
