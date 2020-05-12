package se.kth.iv1350.processSale.view;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ErrorMessageHandlerTest {
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
	public void testdisplayErrorMessage(){
		ErrorMessageHandler EMHandler = new ErrorMessageHandler();
		String errorMessageString = "operation was unsuccessful";
		StringBuilder builder = new StringBuilder();
		builder.append("A problem has occured:\n");
		builder.append(errorMessageString);
		builder.append("\n\n");
		String expResult = new String(builder.toString());
		EMHandler.displayErrorMessage(errorMessageString);
		String result = outContent.toString();
		assertEquals(expResult, result, "Incorrect message was printed");	
	}

}
