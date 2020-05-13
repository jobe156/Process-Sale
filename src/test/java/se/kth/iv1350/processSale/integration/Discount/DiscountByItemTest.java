package se.kth.iv1350.processSale.integration.Discount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.Sale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class DiscountByItemTest {
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
		sale = new Sale();	
		
		String customerName = "Bob";
		int customerYearOfBirth = 1994;
		customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
	}
	
	@AfterEach
	public void tearDown() {
		breadItemDTO = null;
		sale = null;
		customerID = null;
	}
	
	@Test
	public void testDiscountEligible() {
		for(int i = 0; i < 3; i++)
			sale.addItem(breadItemDTO);		
		Item breadItem = new Item(breadItemDTO);
		DiscountByItem discItm = new DiscountByItem(breadItem, 3);
		assertTrue(discItm.discountEligible(sale, customerID), "eligible discount returns false");
	}
	
	@Test
	public void testNotDiscountEligible() {
		for(int i = 0; i < 2; i++)
			sale.addItem(breadItemDTO);		
		Item breadItem = new Item(breadItemDTO);
		 DiscountByItem discItm = new DiscountByItem(breadItem, 3);
		 assertTrue(!discItm.discountEligible(sale, customerID), "sale not eligible for discount returns true.");
	}
	
	@Test void testCalculateDicount() {
		for(int i = 0; i < 3; i++)
			sale.addItem(breadItemDTO);		
		Item breadItem = new Item(breadItemDTO);
		DiscountByItem discItm = new DiscountByItem(breadItem, 3);
		Amount expResult = breadItemDTO.getItemPrice();
		Amount result = discItm.calculateDicount(sale);
		assertTrue(expResult.equals(result), "eligible discount returns wrong amount ");
	}
}
