package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.model.SaleInformationProvider;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.TransactionResultDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;

class ControllerTest {
	private ItemDTO breadItemDTO;
	private ItemIdentifier breadItemID;
	private Printer printer;
	private SaleInformationProvider SIProvider;
	private Controller cntrl;
	private Sale sale;
	
	@BeforeEach
	public void setUp() {
		printer = new Printer();
		SIProvider = new SaleInformationProvider(printer);
		cntrl = new Controller(SIProvider);
		sale = new Sale();
		
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		breadItemID = new ItemIdentifier(breadStringIdentifier);
		breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
	}

	@AfterEach
	public void tearDown() {
		printer = null;
		SIProvider = null;
		cntrl = null;
		sale = null;
		
		breadItemID = null;
		breadItemDTO = null;
	}

	@Test
	public void testRegisterItem() {
		sale.addItem(breadItemDTO);
		ItemRegistrationDTO expResult = new ItemRegistrationDTO(breadItemDTO, sale.CalculateFinalPrice());
		cntrl.startSale();
		String identifierString = "001";
		ItemIdentifier itemID = new ItemIdentifier(identifierString);
		ItemRegistrationDTO result = cntrl.registerItem(itemID);
		assertEquals(expResult.getItemName(), result.getItemName(), "invalid itemName");
		assertEquals(expResult.getItemDescription(), result.getItemDescription(), "invalid item description");
		assertEquals(expResult.getItemPrice(), result.getItemPrice(), "invalid item price");
		assertEquals(expResult.getRunningTotal(), result.getRunningTotal(), "invalid irunningTotal");
	}
	
	@Test
	public void testEndSale() {
		sale.addItem(breadItemDTO);
		cntrl.startSale();
		String identifierString = "001";
		ItemIdentifier itemID = new ItemIdentifier(identifierString);
		cntrl.registerItem(itemID);
		TransactionResultDTO expResult = new TransactionResultDTO(sale);
		TransactionResultDTO result = cntrl.endSale();
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
	}
	
	@Test
	public void testProcessCashPayment() {
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		double paidValue = 200;
		Amount amountPaid = new Amount(paidValue);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		TransactionResultDTO expResult = new TransactionResultDTO(saleLog);
		cntrl.startSale();
		cntrl.registerItem(breadItemID);
		TransactionResultDTO result = cntrl.processCashPayment(amountPaid);
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
		assertEquals(expResult.getAmountPaid(), result.getAmountPaid(), "invalid amount paid");
		assertEquals(expResult.getChange(), result.getChange(), "invalid change");
	}
}
