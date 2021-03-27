package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
	
	@Test
	public void validatePhoneNumberTest() {
		
		Assertions.assertTrue(Validator.validatePhoneNumber("12345678"));
		Assertions.assertTrue(Validator.validatePhoneNumber("10474749"));
		Assertions.assertFalse(Validator.validatePhoneNumber(null));
		Assertions.assertFalse(Validator.validatePhoneNumber(""));
		Assertions.assertFalse(Validator.validatePhoneNumber("123456789"));
		Assertions.assertFalse(Validator.validatePhoneNumber("1234567"));
		Assertions.assertFalse(Validator.validatePhoneNumber("1234s678"));
		Assertions.assertFalse(Validator.validatePhoneNumber("1234567j"));
		Assertions.assertFalse(Validator.validatePhoneNumber("d2345678"));
		Assertions.assertFalse(Validator.validatePhoneNumber("01234567"));
		Assertions.assertFalse(Validator.validatePhoneNumber("12345 78"));
		
	}

}
