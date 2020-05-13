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

import java.util.ArrayList;
import java.util.List;

class DiscountForStoreMembersTest {
	private ItemDTO breadItemDTO;
	private Sale sale;
	
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
	}
	
	@AfterEach
	public void tearDown() {
		breadItemDTO = null;
		sale = null;
	}
	
	@Test
	public void testDiscountEligible() {
		String customerName = "Bob";
		int customerYearOfBirth = 1994;
		CustomerIdentificationDTO customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
		
		List<CustomerIdentificationDTO> storeMembers = new ArrayList<>();
		storeMembers.add(customerID);
		DiscountForStoreMembers discStorMemb = new DiscountForStoreMembers(storeMembers);
		assertTrue(discStorMemb.discountEligible(sale, customerID), "eligible discount returns false");
	}
	
	@Test
	public void testNotDiscountEligible() {
		String customerName = "Paul";
		int customerYearOfBirth = 1994;
		CustomerIdentificationDTO customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
		
		List<CustomerIdentificationDTO> storeMembers = new ArrayList<>();
		storeMembers.add(customerID);
		DiscountForStoreMembers discStorMemb = new DiscountForStoreMembers(storeMembers);
		assertTrue(discStorMemb.discountEligible(sale, customerID), "sale not eligible for discount returns true.");
	}
	
	@Test
	public void testCalculateDscount() {
		String customerName = "Bob";
		int customerYearOfBirth = 1994;
		CustomerIdentificationDTO customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
		List<CustomerIdentificationDTO> storeMembers = new ArrayList<>();
		storeMembers.add(customerID);
		DiscountForStoreMembers discStorMemb = new DiscountForStoreMembers(storeMembers);
		
		for(int i = 0; i < 3; i++)
			sale.addItem(breadItemDTO);	
		
		Amount expResult = new Amount(sale.calculateTotalItemPrice().multiply(0.10));
		Amount result = discStorMemb.calculateDicount(sale);
		assertTrue(expResult.equals(result), "eligible discount returns false");
	}
}
