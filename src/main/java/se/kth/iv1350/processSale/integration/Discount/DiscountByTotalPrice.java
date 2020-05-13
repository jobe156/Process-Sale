package se.kth.iv1350.processSale.integration.Discount;

import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;

/**
 * Is a implementation for <code>discount<code> that applies if the <code>sale<code> 
 * cost more or equal to a certain <code>amount<code>.
 */
public class DiscountByTotalPrice implements Discount{
	private Amount totalPrice;
	
	/**
	 * creates an instance of the Discount by total price.
	 * 
	 * @param totalPrice	the total <code>Amount<code> that is required for the 
	 * 						discount to apply.
	 */
	public DiscountByTotalPrice(Amount totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public boolean discountEligible(Sale sale, CustomerIdentificationDTO customerID) {
		return totalPrice.lessOrEqualThan(sale.calculateTotalItemPrice());
	}

	@Override
	public Amount calculateDicount(Sale sale) {
		return sale.calculateTotalItemPrice().multiply(0.07);
	}
}
