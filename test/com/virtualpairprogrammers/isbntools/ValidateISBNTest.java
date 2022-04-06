package com.virtualpairprogrammers.isbntools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	public void checkAValidTenDigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue(result, "first value");
		result = validator.checkISBN("0140177396");
		assertTrue(result, "second value");
	}
	
	@Test
	public void tenDigitISBNNumbersEndingInXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}
	
	@Test
	public void checkAValidThirteenDigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780765377135");
		assertTrue(result, "First value");
		result = validator.checkISBN("9781250318572");
		assertTrue(result, "second value");
	}
	
	@Test
	public void checkAnInvalidThirteenDigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780765377136");
		assertFalse(result);
	}
	
	@Test
	public void checkAnInvalidTenDigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test
	public void nineDigitISDNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		
		assertThrows(NumberFormatException.class, 
				() -> {validator.checkISBN("123456789"); } );
	}
	
	@Test
	public void nonNumericISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		
		assertThrows(NumberFormatException.class, 
				() -> {validator.checkISBN("helloworld"); } );
	}
	
	
}
