package se.kth.iv1350.processSale.integration.Discount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.util.Amount;

class DiscountByItemQuantityTest {
	private ItemDTO breadItemDTO;
	private Sale sale;
	private CustomerIdentificationDTO customerID;

	@BeforeEach
	public void startUp() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		
		String customerName = "Bob";
		int customerYearOfBirth = 1994;
		customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
		
		sale = new Sale();		
	}
	
	@AfterEach
	public void tearDown() {
		breadItemDTO = null;
		sale = null;
		customerID = null;
	}
	
	@Test
	public void testDiscountEligible() {
		DiscountByItemQuantity discItmQty = new DiscountByItemQuantity(5);
		for(int i = 0; i < 5; i++)
			sale.addItem(breadItemDTO);
		assertTrue(discItmQty.discountEligible(sale, customerID), "eligible discount returns false");
	}
	
	@Test
	public void testNotDiscountEligible() {
		DiscountByItemQuantity discItmQty = new DiscountByItemQuantity(5);
		for(int i = 0; i < 4; i++)
			sale.addItem(breadItemDTO);
		assertTrue(!discItmQty.discountEligible(sale, customerID), "eligible discount returns false");
	}
	
	@Test
	public void testCalculateDicount() {
		DiscountByItemQuantity discItmQty = new DiscountByItemQuantity(5);
		for(int i = 0; i < 5; i++)
			sale.addItem(breadItemDTO);
		int itemsInSale = 0;
		for(Item item : sale.getItems())
			itemsInSale += item.getQuantity();
		Amount expResult = sale.calculateTotalItemPrice().multiply(((double)itemsInSale)/500.0);
		Amount result = discItmQty.calculateDicount(sale);
		assertTrue(expResult.equals(result), "eligible discount returns wrong amount");
	}
}
