package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.integration.Discount.*;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;

/**
 * Represent an external system that keeps track of the <code>Discounts<code>. 
 */
public class DiscountSystemHandler {
	private List<Discount> discounts = new ArrayList<>();
	
	/**
	 * Creates an instance of the discount system handler.
	 */
	DiscountSystemHandler(){
		addDiscounts();
	}
	
	private void addDiscounts() {
		discounts.add(new DiscountByItemQuantity(5));
		discounts.add(new DiscountByTotalPrice(new Amount(400)));
		
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		Item breadItem = new Item(breadItemDTO);
		discounts.add(new DiscountByItem(breadItem, 3));
		
		List<CustomerIdentificationDTO> storeMembers = new ArrayList<>();
		storeMembers.add(new CustomerIdentificationDTO("Bob", 1994));
		
		discounts.add(new DiscountForStoreMembers(storeMembers));	
	}
	
	/**
	 * Returns a list of the discounts that a sale is eligible for.
	 * @param sale			contains information about <code>Sale<code>.
	 * @param customerID	contains information about a customer.
	 * @return				discounts that the sale is eligible for.
	 */
	public List<Discount> findDiscounts(Sale sale, CustomerIdentificationDTO customerID) {
		List<Discount> foundDiscounts = new ArrayList<>();
		for(Discount discount: discounts) 
			if(discount.discountEligible(sale, customerID))
				foundDiscounts.add(discount);
		return foundDiscounts;
	}
}
