package se.kth.iv1350.processSale.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.controller.UnsuccessfulOperationException;
import se.kth.iv1350.processSale.integration.ItemRegistrationException;

class LogHandlerTest {
	private ByteArrayOutputStream outContent;
	private PrintStream originalSysOut;
	
	@BeforeEach
	public void setUp() {
		originalSysOut = System.out;
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
	
	@AfterEach
	public void tearDown() {
		outContent = null;
		System.setOut(originalSysOut);
	}
	
	@Test
	public void testLogException() {
		LogHandler lHandler = new LogHandler();
		lHandler.LogException(new UnsuccessfulOperationException("The given task couldn´t be carried out.", 
																	new ItemRegistrationException(
																			"The Inventory System is´t responding.")));
		String errorMessageString = "The given task couldn´t be carried out.";
		StringBuilder builder = new StringBuilder();
		builder.append("\n----------------------------------------Log----------------------------------------");
		builder.append("\nError message: \n");
		builder.append(errorMessageString + "\n");
		builder.append("\nException stack trace:");
		String result = outContent.toString();
		assertTrue(result.contains(builder.toString()), "Incorrect Log was printed.");
	}

}
