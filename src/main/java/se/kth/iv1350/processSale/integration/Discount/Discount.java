package se.kth.iv1350.processSale.integration.Discount;

import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
/**
 * A interface used to create discounts.
 *
 */
public interface Discount {

	/**
	 * is used to see if the given <code>sale<code> is eligible for a discount.
	 * 
	 * @param sale			the sale to be checked
	 * @param customerID	the identification of the customer.
	 * @return				true if the discount can be added to the sale, and false if not.
	 */
	public boolean discountEligible(Sale sale, CustomerIdentificationDTO customerID);
	
	/**
	 * is used to calculate the discount based on the sale and returns the amount that can
	 * be subtracted form the total price.
	 * 
	 * @param sale	the sale used to calculate the discount
	 * @return		the amount the discount removes from the total sale.
	 */
	public Amount calculateDicount(Sale sale);
}
