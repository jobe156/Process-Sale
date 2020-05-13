package se.kth.iv1350.processSale.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import se.kth.iv1350.processSale.util.Amount;

class TotalRevenueViewTest {
	private ByteArrayOutputStream outContent;
	private PrintStream originalSysOut;
	
	@BeforeEach
	public void setUpStreams() {
		originalSysOut = System.out;
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
	
	@AfterEach
	public void cleanUpStreams() {
		outContent = null;
		System.setOut(originalSysOut);
	}
	
	@Test
	public void testCurrentState() {
		TotalRevenueView totalRevenueView = new TotalRevenueView();
		double totalPriceValue1 = 200;
		double totalPriceValue2 = 535;
		double totalPriceValue3 = 470.45;
		Amount totalPrice1 = new Amount(totalPriceValue1);
		Amount totalPrice2 = new Amount(totalPriceValue2);
		Amount totalPrice3 = new Amount(totalPriceValue3);
		Amount finalPrice = new Amount(totalPriceValue1 + totalPriceValue2 + totalPriceValue3);
		StringBuilder builder = new StringBuilder();
		builder.append("\n----------Total Revenue----------\n\n");
		builder.append("\t" + totalPrice1 + "\n");
		builder.append("\t" + totalPrice2 + "\n");
		builder.append("\t" + totalPrice3 + "\n");
		builder.append("+\n");
		builder.append("---------------\n");
		builder.append("\t" + finalPrice + "\n\n");
		builder.append("\n---------------------------------\n");
		
		totalRevenueView.newPayment(totalPrice1);
		totalRevenueView.newPayment(totalPrice2);
		totalRevenueView.newPayment(totalPrice3);
		
		String finalString = builder.toString();
		String result = outContent.toString();
		assertTrue(result.contains(finalString), "Wrong printout");
	}

}
