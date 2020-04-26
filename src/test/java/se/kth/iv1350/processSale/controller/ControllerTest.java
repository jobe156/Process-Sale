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
import se.kth.iv1350.processSale.model.DisplayTransactionDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;



class ControllerTest {
	private Printer printer;
	private SaleInformationProvider SIProvider;
	private Controller cntrl;
	private ItemIdentifier itemID;

	@BeforeEach
	public void setUp() {
		printer = new Printer();
		SIProvider = new SaleInformationProvider(printer);
		cntrl = new Controller(SIProvider);
		itemID = new ItemIdentifier("001");

	}

	@AfterEach
	public void tearDown() {
		printer = null;
		SIProvider = null;
		cntrl = null;
		itemID = null;
	}

	@Test
	public void testRegisterItem() {
		Sale sale = new Sale();
		Amount itemPrice1 = new Amount(50);
		ItemIdentifier itemID1 = new ItemIdentifier("001");
		ItemDTO itemDTO1 = new ItemDTO(itemID1, "Bread",itemPrice1, "It´s whole grain!", 0);
		sale.addItem(itemDTO1);
		ItemRegistrationDTO expResult = new ItemRegistrationDTO(itemDTO1, sale.CalculateFinalPrice());
		cntrl.startSale();
		ItemRegistrationDTO result = cntrl.registerItem(itemID);
		assertEquals(expResult.getItemName(), result.getItemName(), "invalid itemName");
		assertEquals(expResult.getItemDescription(), result.getItemDescription(), "invalid item description");
		assertEquals(expResult.getItemPrice(), result.getItemPrice(), "invalid item price");
		assertEquals(expResult.getRunningTotal(), result.getRunningTotal(), "invalid irunningTotal");
	}
	
	@Test
	public void testProcessCashPayment() {
		Sale sale = new Sale();
		Amount itemPrice1 = new Amount(50);
		ItemIdentifier itemID1 = new ItemIdentifier("001");
		ItemDTO itemDTO1 = new ItemDTO(itemID1, "Bread",itemPrice1, "It´s whole grain!", 0);
		sale.addItem(itemDTO1);
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		DisplayTransactionDTO expResult = new DisplayTransactionDTO(saleLog);
		cntrl.startSale();
		cntrl.registerItem(itemID1);
		DisplayTransactionDTO result = cntrl.processCashPayment(amountPaid);
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
		assertEquals(expResult.getAmountPaid(), result.getAmountPaid(), "invalid amount paid");
		assertEquals(expResult.getChange(), result.getChange(), "invalid change");
	}
}
