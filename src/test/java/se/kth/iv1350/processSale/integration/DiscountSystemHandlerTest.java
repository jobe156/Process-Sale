package se.kth.iv1350.processSale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.integration.Discount.Discount;
import se.kth.iv1350.processSale.integration.Discount.DiscountByItem;

import java.util.List;



class DiscountSystemHandlerTest {
	
	@Test
	public void testFindItemDisocunts() {
		DiscountSystemHandler DSHandler = new DiscountSystemHandler();
		
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		
		String customerName = "Bob";
		int customerYearOfBirth = 1994;
		CustomerIdentificationDTO customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
		
		Sale sale = new Sale();
		for(int i = 0; i < 3; i++)
			sale.addItem(breadItemDTO);
		
		List<Discount> disc = DSHandler.findDiscounts(sale, customerID);
		
		assertTrue(!disc.isEmpty(), "No discounts were found");
		assertTrue((disc.get(0) instanceof DiscountByItem), "wrong disocunt");
	}

}
