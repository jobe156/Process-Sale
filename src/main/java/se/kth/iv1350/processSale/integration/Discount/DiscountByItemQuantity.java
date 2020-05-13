package se.kth.iv1350.processSale.integration.Discount;

import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;

/**
 * Is a implementation for <code>discount<code> that applies if the <code>sale<code> 
 * contain a certain amount of <code>item<code>s.
 *
 */
public class DiscountByItemQuantity implements Discount{
	private int numberOfItems;
	
	/**
	 * creates an instance of Discount by item quantity
	 * 
	 * @param numberOfItems		the quantity that is required to apply the discount.
	 */
	public DiscountByItemQuantity(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	@Override
	public boolean discountEligible(Sale sale, CustomerIdentificationDTO customerID) {
		return getTotalNumberOfItems(sale) >= numberOfItems;
	}
	
	@Override
	public Amount calculateDicount(Sale sale) {
		return sale.calculateTotalItemPrice().multiply(((double)getTotalNumberOfItems(sale))/500.0);
	}
	
	private int getTotalNumberOfItems(Sale sale) {
		int itemsInSale = 0;
		for(Item item: sale.getItems())
			itemsInSale += item.getQuantity();
		return itemsInSale;
	}
	
}
