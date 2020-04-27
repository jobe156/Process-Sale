package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

class SaleInformationProviderTest {
	private Printer printer;
	private SaleInformationProvider SIProvider;
	private Sale sale;
	private ItemDTO breadItemDTO;

	@BeforeEach
	public void startUp() {
		printer = new Printer();
		SIProvider = new SaleInformationProvider(printer);
		sale = new Sale();
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		breadItemDTO = new ItemDTO(itemID, "Bread", itemPrice, "It´s whole grain!", 0.1);
	}

	@AfterEach
	public void tearDown() {
		printer = null;
		SIProvider = null;
		sale = null;
		breadItemDTO = null;
	}

	@Test
	public void testGenerateItemRegistrationDTO() {
		sale.addItem(breadItemDTO);
		Amount totalAmount = sale.CalculateFinalPrice();
		ItemRegistrationDTO expResult = new ItemRegistrationDTO(breadItemDTO, totalAmount);
		ItemRegistrationDTO result = SIProvider.generateItemRegistrationDTO(sale, breadItemDTO);
		assertEquals(expResult.getItemName(), result.getItemName(), "Wrong item name.");
		assertEquals(expResult.getItemDescription(), result.getItemDescription(), "Wrong item description.");
		assertEquals(expResult.getItemPrice(), result.getItemPrice(), "Wrong item price.");
		assertEquals(expResult.getRunningTotal(), result.getRunningTotal(), "Wrong item running total.");
	}
	
	/*
	@Test
	public void testGenerateItemRegistrationDTONullItemDTO() {
		ItemDTO nullItemDTO = null;
		sale.addItem(nullItemDTO);
		ItemRegistrationDTO resultItmRegDto = SIProvider.generateItemRegistrationDTO(sale, nullItemDTO);
		assertNull(resultItmRegDto, "ItemDTO is null");
	}
	*/

	@Test
	public void testGenerateTransactionResultDTO() {
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO sLog = cashPayment.processPayment(sale);
		TransactionResultDTO expResult = new TransactionResultDTO(sLog);
		TransactionResultDTO result = SIProvider.generateTransactionResultDTO(sLog);
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
		assertEquals(expResult.getAmountPaid(), result.getAmountPaid(), "invalid amount paid");
		assertEquals(expResult.getChange(), result.getChange(), "invalid change");
	}

	/*
	@Test
	public void testGenerateTransactionResultDTONullArg() {
		SaleLogDTO nullSaleLog = null;
		DisplayTransactionDTO result = SIProvider.generateDisplayTransactionDTO(nullSaleLog);
		assertNull(result, "null argument generated a sale Log");
	}
	*/

}
