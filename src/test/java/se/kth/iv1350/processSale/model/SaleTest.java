package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.ItemDTO;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.SaleInformationProvider;

import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.ItemIdentifier;

class SaleTest {
	private Sale sale;
	private ItemDTO breadItemDTO;

	private Controller cntrl;

	private CustomerIdentificationDTO customerID;

	@BeforeEach
	public void setUp() {
		sale = new Sale();
		// läg til timeDTOs här då det används på flera ställen.
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		breadItemDTO = new ItemDTO(itemID, "Bread", itemPrice, "It´s whole grain!", 0.1);

		Printer printer = new Printer();
		SaleInformationProvider SIProvider = new SaleInformationProvider(printer);
		cntrl = new Controller(SIProvider);

		customerID = new CustomerIdentificationDTO("Billy Bob", 1982, 175, 12345, true);

	}

	@AfterEach
	public void tearDown() {
		sale = null;
		breadItemDTO = null;

		cntrl = null;

		customerID = null;

	}

	@Test
	public void testAddItemNullItemDto() {
		ItemDTO itemDTO = null;
		int quantity = 1;
		sale.addItem(itemDTO, quantity);
		boolean expResult = true;
		boolean result = sale.getItems().isEmpty();
		assertEquals(expResult, result, "list contains a object when a null items was used as arguemnt");
	}

	@Test
	public void testAddItemZeroQuantity() {
		int quantity = 0;
		sale.addItem(breadItemDTO, quantity);
		boolean expResult = true;
		boolean result = sale.getItems().isEmpty();
		assertEquals(expResult, result, "list contains added object despite having a quantity of 0");
	}

	@Test
	public void testAddItem() {
		int quantity = 1;
		sale.addItem(breadItemDTO, quantity);
		boolean expResult = true;
		boolean result = sale.getItems().get(0).getItemName().equals(breadItemDTO.getItemName()); // super enkelt att
																									// läsa
		assertEquals(expResult, result, "added item is not i not in sale item list");
	}

	@Test
	public void testCalculatefinalPriceNoItems() {
		Amount zeroAmount = new Amount();
		Amount totalAmount = sale.CalculateFinalPrice();
		boolean expResult = true;
		boolean result = zeroAmount.equal(totalAmount);
		assertEquals(expResult, result, "sale with no items has i non zero price");
	}

	@Test
	public void testCalculatefinalPrice() {
		int quantity = 1;
		sale.addItem(breadItemDTO, quantity);
		Amount totalAmount = sale.CalculateFinalPrice();
		Amount expAmount = new Amount(breadItemDTO.getItemPrice());
		double totalVat = 1 + breadItemDTO.getVatRate();
		expAmount.multiply(totalVat);
		boolean expResult = true;
		boolean result = expAmount.equal(totalAmount);
		assertEquals(expResult, result, "total price was not correct.");
	}

	/*
	@Test
	public void testLookForDiscount() {
		sale.addItem(breadItemDTO, 10);
		Amount totalBeforeDiscount = sale.CalculateFinalPrice();
		sale.LookForDiscounts(cntrl.getDiscountRepositoryHandler(), customerID);
		Amount totalAfterDiscount = sale.CalculateFinalPrice();
		boolean expResult = false;
		boolean result = totalBeforeDiscount.equal(totalAfterDiscount);
		assertEquals(expResult, result, "Amount before applied discount is the same as " + "Amount after Discount.");
	}

	@Test
	public void testLookForDiscountNotDiscountEligible() {
		sale.addItem(breadItemDTO, 1);
		Amount totalBeforeDiscount = sale.CalculateFinalPrice();
		sale.LookForDiscounts(cntrl.getDiscountRepositoryHandler(), customerID);
		Amount totalAfterDiscount = sale.CalculateFinalPrice();
		boolean expResult = true;
		boolean result = totalBeforeDiscount.equal(totalAfterDiscount);
		assertEquals(expResult, result,
				"Discount has been aplied despite the sale" + " not being eligible for a discount.");
	}

	@Test
	public void testLookForDiscountNotEligibilCustomerIdentification() {
		CustomerIdentificationDTO invalidCustomerID = new CustomerIdentificationDTO("Billy Bob", 1982, 175, 12345,
				false);
		sale.addItem(breadItemDTO, 10);
		Amount totalBeforeDiscount = sale.CalculateFinalPrice();
		sale.LookForDiscounts(cntrl.getDiscountRepositoryHandler(), invalidCustomerID);
		Amount totalAfterDiscount = sale.CalculateFinalPrice();
		boolean expResult = true;
		boolean result = totalBeforeDiscount.equal(totalAfterDiscount);
		assertEquals(expResult, result,
				"Discount applied despite customerIDentification not being" + "eligible for discount.");
	}

	@Test
	public void testLookForDiscountNullcustomerIDArg() {
		CustomerIdentificationDTO nullCustomerID = null;
		sale.addItem(breadItemDTO, 10);
		Amount totalBeforeDiscount = sale.CalculateFinalPrice();
		sale.LookForDiscounts(cntrl.getDiscountRepositoryHandler(), nullCustomerID);
		Amount totalAfterDiscount = sale.CalculateFinalPrice();
		boolean expResult = true;
		boolean result = totalBeforeDiscount.equal(totalAfterDiscount);
		assertEquals(expResult, result,
				"Discount has been aplied despite the sale" + " not being eligible for a discount.");
	}

	@Test
	public void testLookForDiscountNullDRHandlerArg() {
		sale.addItem(breadItemDTO, 10);
		Amount totalBeforeDiscount = sale.CalculateFinalPrice();
		sale.LookForDiscounts(null, customerID);
		Amount totalAfterDiscount = sale.CalculateFinalPrice();
		boolean expResult = true;
		boolean result = totalBeforeDiscount.equal(totalAfterDiscount);
		assertEquals(expResult, result, "Amount before applied discount is the same as " + "Amount after Discount.");
	}
	*/
}
