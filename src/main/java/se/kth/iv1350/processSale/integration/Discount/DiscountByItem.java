package se.kth.iv1350.processSale.integration.Discount;

import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;

import se.kth.iv1350.processSale.model.Item;

/**
 * Is a implementation for <code>discount<code> that applies if the <code>sale<code> 
 * contain a specific quantity of a certain <code>item<code>.
 */
public class DiscountByItem implements Discount{
	private Item discItem;
	
	/**
	 * creates an instance of discount by item.
	 * 
	 * @param discItem	the necessary item.
	 * @param quantity	the quantity of the item.
	 */
	public DiscountByItem(Item discItem, int quantity) {
		this.discItem = discItem;
		for(int i = 1; i < quantity; i++)
			this.discItem.increaseQuantity();
	}
	
	@Override
	public boolean discountEligible(Sale sale, CustomerIdentificationDTO customerID) {
		for(Item item: sale.getItems())
			if(item.equals(discItem))
				return true;
		return false;
	}

	@Override
	public Amount calculateDicount(Sale sale) {
		return new Amount(discItem.getItemPrice().multiply(0.5));
	}
	
}
