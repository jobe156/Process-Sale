package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.SaleInformationProvider;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.util.ItemIdentifier;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.model.DisplayTransactionDTO;
import se.kth.iv1350.processSale.util.Amount;

//asdasd

class ControllerTest {
	private Printer printer;
	private SaleInformationProvider SIProvider;
	private Controller cntrl;
	private ItemIdentifier itemID;

	private CustomerIdentificationDTO customerID;

	@BeforeEach
	public void setUp() {
		printer = new Printer();
		SIProvider = new SaleInformationProvider(printer);
		cntrl = new Controller(SIProvider);
		itemID = new ItemIdentifier("001");

		customerID = new CustomerIdentificationDTO("Billy Bob", 1982, 175, 12345, true);
	}

	@AfterEach
	public void tearDown() {
		printer = null;
		SIProvider = null;
		cntrl = null;
		itemID = null;

		customerID = null;
	}

	@Test
	public void testRegisterItem() {
		cntrl.startSale();
		int quantity = 3;
		ItemRegistrationDTO itmRegDTO = cntrl.registerItem(itemID, quantity);
		assertNotNull(itmRegDTO, "Item with valid identifier was not added to the sale");
	}

	@Test
	public void testRegisterItemInvalidIdentifer() {
		ItemIdentifier wrongItemID = new ItemIdentifier("£ü|");
		int quantity = 3;
		cntrl.startSale();
		ItemRegistrationDTO itmRegDTO = cntrl.registerItem(wrongItemID, quantity);
		assertNull(itmRegDTO, "Item with invalid identifier was added to the sale");
	}

	@Test
	public void testRegisterItemInvalidQunatity() {
		cntrl.startSale();
		int quantity = 0;
		ItemRegistrationDTO itmRegDTO = cntrl.registerItem(itemID, quantity);
		assertNull(itmRegDTO, "Item with null quantity was added to the sale");
	}

	@Test
	public void testDiscountRequest() {
		cntrl.startSale();
		ItemRegistrationDTO itmRegDTO = cntrl.registerItem(itemID, 10);
		Amount totalBeforDiscount = itmRegDTO.getRunningTotal();
		DisplayTransactionDTO disTraDTO = cntrl.DiscountRequest(customerID);
		Amount totalAfterDisocunt = disTraDTO.getTotalPrice();
		boolean expResult = false;
		boolean result = totalBeforDiscount.equal(totalAfterDisocunt);
		assertEquals(expResult, result, "Discount was not applied desipte the sale being eligible" + "for one.");
	}

	@Test
	public void testDiscountRequestNotEligibleForDiscount() {
		cntrl.startSale();
		ItemRegistrationDTO itmRegDTO = cntrl.registerItem(itemID, 1);
		Amount totalBeforDiscount = itmRegDTO.getRunningTotal();
		DisplayTransactionDTO disTraDTO = cntrl.DiscountRequest(customerID);
		Amount totalAfterDisocunt = disTraDTO.getTotalPrice();
		boolean expResult = true;
		boolean result = totalBeforDiscount.equal(totalAfterDisocunt);
		assertEquals(expResult, result, " Discount was applied despite the sale not being" + "applicable for one.");
	}

	@Test
	public void testDiscountRequestNullArgument() {
		cntrl.startSale();
		ItemRegistrationDTO itmRegDTO = cntrl.registerItem(itemID, 10);
		Amount totalBeforDiscount = itmRegDTO.getRunningTotal();
		DisplayTransactionDTO disTraDTO = cntrl.DiscountRequest(null);
		Amount totalAfterDisocunt = disTraDTO.getTotalPrice();
		boolean expResult = true;
		boolean result = totalBeforDiscount.equal(totalAfterDisocunt);
		assertEquals(expResult, result, "null argument aplied a discount to a sale");
	}
}
