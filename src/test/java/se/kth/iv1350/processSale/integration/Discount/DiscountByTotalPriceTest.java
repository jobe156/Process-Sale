package se.kth.iv1350.processSale.integration.Discount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

class DiscountByTotalPriceTest {
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
	 DiscountByTotalPrice discTotPrc = new DiscountByTotalPrice(new Amount(400));
		for(int i = 0; i < 8; i++)
			sale.addItem(breadItemDTO);
		assertTrue(discTotPrc.discountEligible(sale, customerID), "eligible discount returns false");
	}
	
	@Test
	public void testNotDiscountEligible() {
	 DiscountByTotalPrice discTotPrc = new DiscountByTotalPrice(new Amount(400));
		for(int i = 0; i < 7; i++)
			sale.addItem(breadItemDTO);
		assertTrue(!discTotPrc.discountEligible(sale, customerID), "eligible discount returns false");
	}
	
	@Test
	public void testCalculateDiscount() {
		DiscountByTotalPrice discTotPrc = new DiscountByTotalPrice(new Amount(400));
		for(int i = 0; i < 8; i++)
			sale.addItem(breadItemDTO);
		Amount expResult = sale.calculateTotalItemPrice().multiply(0.07);
		Amount result = discTotPrc.calculateDicount(sale);
		assertTrue(expResult.equals(result), "eligible discount returns wrong amount" );
	}

}
