package se.kth.iv1350.processSale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.Receipt;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PrinterTest {
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
	public void testPrintReceipt() {
		Printer printer = new Printer();
		Sale sale = new Sale();
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO breadItemDTO = new ItemDTO(itemID, "Bread", itemPrice, "It´s whole grain!", 0.1);
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		Receipt receipt = new Receipt(saleLog);
		printer.printReceipt(receipt);
		
		StringBuilder builder = new StringBuilder();
		builder.append("Store name: "+ saleLog.getStoreName());
		builder.append("Store adress: " + saleLog.getStoreAdress() + "/n");
		builder.append("Time of sale: " + saleLog.getTimeOfSale().toString() + "/n");
		builder.append("Bought items: " + "/n" );
		for(Item item: saleLog.getItems()) 
			builder.append(item.toString() + "/n");
		builder.append("/nTotalVatRate: " + String.valueOf(saleLog.getTotalVatRate()) + "/n");
		builder.append("Total price: " + saleLog.getTotalPrice().toString() + "/n");
		builder.append("Paid amount: " + saleLog.getPaidAmount().toString() + "/n");
		builder.append("Total price: " + saleLog.getChange().toString() + "/n");
		
		String expResult = builder.toString();
		String result = outContent.toString();
		assertTrue(result.contains(expResult));
	}
}
