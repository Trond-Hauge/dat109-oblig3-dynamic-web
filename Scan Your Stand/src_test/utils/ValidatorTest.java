package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
	
	@Test
	public void validPhoneNumberTest() {
		
		Assertions.assertTrue(Validator.validPhoneNumber("12345678"));
		Assertions.assertTrue(Validator.validPhoneNumber("10474749"));
		Assertions.assertFalse(Validator.validPhoneNumber(null));
		Assertions.assertFalse(Validator.validPhoneNumber(""));
		Assertions.assertFalse(Validator.validPhoneNumber("123456789"));
		Assertions.assertFalse(Validator.validPhoneNumber("1234567"));
		Assertions.assertFalse(Validator.validPhoneNumber("1234s678"));
		Assertions.assertFalse(Validator.validPhoneNumber("1234567j"));
		Assertions.assertFalse(Validator.validPhoneNumber("d2345678"));
		Assertions.assertFalse(Validator.validPhoneNumber("01234567"));
		Assertions.assertFalse(Validator.validPhoneNumber("12345 78"));
		
	}
	
	@Test
	public void validProjectNameTest() {
		
		Assertions.assertTrue(Validator.validProjectName("Project 3"));
		Assertions.assertTrue(Validator.validProjectName("1 K 2"));
		Assertions.assertTrue(Validator.validProjectName("Prøsjæktå ØÆÅ0"));
		Assertions.assertFalse(Validator.validProjectName("123456789123456789123456789"));
		Assertions.assertFalse(Validator.validProjectName("PP"));
		
	}
	
	@Test
	public void validProjectNumberTest() {
		
		Assertions.assertTrue(Validator.validProjectNumber("A01"));
		Assertions.assertTrue(Validator.validProjectNumber("Z00"));
		Assertions.assertTrue(Validator.validProjectNumber("K99"));
		Assertions.assertFalse(Validator.validProjectNumber("A001"));
		Assertions.assertFalse(Validator.validProjectNumber("B1"));
		Assertions.assertFalse(Validator.validProjectNumber("a01"));
		
	}

}
