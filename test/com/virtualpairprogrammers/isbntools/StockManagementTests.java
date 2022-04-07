package com.virtualpairprogrammers.isbntools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class StockManagementTests {
	
	ExternalISBNDataService testWebService;
	StockManager stockManager;
	ExternalISBNDataService testDatabaseService;
	
	@BeforeEach
	public void setup() {
		testWebService = mock(ExternalISBNDataService.class);
		testDatabaseService = mock(ExternalISBNDataService.class);
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
	}

	@Test
	public void canGetCorrectLocatorCode() {
		
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "Of Mice and Men", "J. Steinbeck"));
		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	public void databaseIsUsedIfDataIsPresent() {
		
		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService, never()).lookup(anyString());
	}
	
	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		
		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService).lookup("0140177396");
	}


}
