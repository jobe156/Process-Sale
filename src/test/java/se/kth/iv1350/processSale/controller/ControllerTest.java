package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.integration.Discount.DiscountByItem;
import se.kth.iv1350.processSale.model.SaleInformationProvider;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.model.InvalidItemIdentifierException;
import se.kth.iv1350.processSale.model.TransactionResultDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.util.Amount;

class ControllerTest {
	private ItemDTO breadItemDTO;
	private ItemIdentifier breadItemID;
	//private Printer printer;
	//private SaleInformationProvider SIProvider;
	private Controller cntrl;
	private Sale sale;
	
	@BeforeEach
	public void setUp() {
		//printer = new Printer();
		//SIProvider = new SaleInformationProvider(printer);
		cntrl = new Controller(); //SIProvider
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
		//printer = null;
		//SIProvider = null;
		cntrl = null;
		sale = null;
		
		breadItemID = null;
		breadItemDTO = null;
	}

	@Test
	public void testRegisterItem() throws UnsuccessfulOperationException{
		sale.addItem(breadItemDTO);
		ItemRegistrationDTO expResult = new ItemRegistrationDTO(breadItemDTO, sale.CalculateFinalPrice());
		cntrl.startSale();
		String identifierString = "001";
		ItemIdentifier itemID = new ItemIdentifier(identifierString);
		try {
		ItemRegistrationDTO result = cntrl.registerItem(itemID);
		assertEquals(expResult.getItemName(), result.getItemName(), "invalid itemName");
		assertEquals(expResult.getItemDescription(), result.getItemDescription(), "invalid item description");
		assertEquals(expResult.getItemPrice(), result.getItemPrice(), "invalid item price");
		assertEquals(expResult.getRunningTotal(), result.getRunningTotal(), "invalid irunningTotal");
		}catch(InvalidItemIdentifierException exp){
			fail("An unintentional exception was caught");
		}
	}
	
	@Test
	public void testRegisterUnsuccessfulOperation() throws InvalidItemIdentifierException{
		String notRespStringIdentifier = "abc";
		ItemIdentifier notRespItemIdentifier = new ItemIdentifier(notRespStringIdentifier);
		cntrl.startSale();
		try {
			cntrl.registerItem(notRespItemIdentifier);
		}catch(UnsuccessfulOperationException exp) {
			assertTrue(exp.getMessage().equals("The given task couldn´t be carried out."));
		}
		
	}
	
	@Test
	public void testEndSale() throws UnsuccessfulOperationException{
		sale.addItem(breadItemDTO);
		cntrl.startSale();
		String identifierString = "001";
		ItemIdentifier itemID = new ItemIdentifier(identifierString);
		try {
		cntrl.registerItem(itemID);
		}catch(InvalidItemIdentifierException exp) {
			fail("An unintentional exception was caught");
		}
		TransactionResultDTO expResult = new TransactionResultDTO(sale);
		TransactionResultDTO result = cntrl.endSale();
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
	}
	
	@Test
	public void testProcessCashPayment()throws UnsuccessfulOperationException{
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		double paidValue = 200;
		Amount amountPaid = new Amount(paidValue);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		TransactionResultDTO expResult = new TransactionResultDTO(saleLog);
		cntrl.startSale();
		try {
			cntrl.registerItem(breadItemID);
		}catch(InvalidItemIdentifierException exp) {
				fail("An unintentional exception was caught");
		}
		TransactionResultDTO result = cntrl.processCashPayment(amountPaid);
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
		assertEquals(expResult.getAmountPaid(), result.getAmountPaid(), "invalid amount paid");
		assertEquals(expResult.getChange(), result.getChange(), "invalid change");
	}
	
	@Test
	public void testCheckForDiscounts() throws UnsuccessfulOperationException{
		cntrl.startSale();
		try {
			for(int i = 3; i< 3; i++)
				cntrl.registerItem(breadItemID);
				sale.addItem(breadItemDTO);
		}
		catch(InvalidItemIdentifierException exp) {
			fail("An unintentional exception was caught");
		}
		String customerName = "Paul";
		int customerYearOfBirth = 1994;
		CustomerIdentificationDTO customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
		TransactionResultDTO trnResDto = cntrl.CheckForDiscounts(customerID);
		Item breadItem = new Item(breadItemDTO);
		DiscountByItem discByItm = new DiscountByItem(breadItem, 3);
		sale.addDiscount(discByItm);
		Amount expResult = new Amount(sale.CalculateFinalPrice());
		Amount result = new Amount(trnResDto.getTotalPrice());
		assertTrue(expResult.equals(result), "Discount wasn´t applied properly");
	}
}
