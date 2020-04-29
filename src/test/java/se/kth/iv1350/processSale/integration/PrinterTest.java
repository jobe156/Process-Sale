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
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		double paidValue = 200;
		Amount amountPaid = new Amount(paidValue);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		Receipt receipt = new Receipt(saleLog);
		printer.printReceipt(receipt);
		StringBuilder builder = new StringBuilder();
		builder.append("Store name: \t"+ saleLog.getStoreName() + "\n");
		builder.append("Store adress: \t" + saleLog.getStoreAddress() + "\n");
		builder.append("Time of sale: \t" + saleLog.getTimeOfSale().toString() + "\n");
		builder.append("Bought items: \t" + "\n\n" );
		for(Item item: saleLog.getItems()) 
			builder.append(item.toString() + "\n");
		builder.append("\nTotalVatAmount:\t" + String.valueOf(saleLog.getTotalVatAmount()) + "\n");
		builder.append("Total price: \t" + saleLog.getTotalPrice().toString() + "\n");
		builder.append("Paid amount: \t" + saleLog.getPaidAmount().toString() + "\n");
		builder.append("Change: \t" + saleLog.getChange().toString() + "\n");
		String expResult = builder.toString();
		String result = outContent.toString();
		assertTrue(result.contains(expResult));
	}
}
