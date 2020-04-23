package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.DiscountFactors;

/**
 * 
 * Discount contains factors which describes when the discount i valid. It also
 * contains a discount amount attribute which describes and a method called
 * applyDiscount which is needed to apply the discount to given amount. o
 */

public class Discount {
	private DiscountFactors discountFactors;
	private double discountAmount;	//misledande namn

	/**
	 * creates an instance of {@link Discount}.
	 * 
	 * @param discountFactors	The factors that need to reached for the discount to be aplied.
	 * @param discountAmount	The factor that is going to be multiplide with the total <code>Amount</code>
	 */
	public Discount(DiscountFactors discountFactors, double discountAmount) {
		this.discountFactors = discountFactors;
		this.discountAmount = discountAmount;
	}
	
	/**
	 * creates an instance of {@link Discount} from an instance of discount.
	 * 
	 * @param disc	
	 */
	
	public Discount(Discount disc) {
		this.discountFactors = disc.discountFactors;
		this.discountAmount = disc.discountAmount;
	}
	
	public DiscountFactors getDiscountFactors() {
		DiscountFactors discountFactorsCopy = new DiscountFactors(discountFactors);
		return discountFactorsCopy;
	}
	
	/**
	 * apply the current discount amount to a given <code>amount</code>.
	 * 
	 * @param amount the <code>amount</code> to witch the discount is applied.
	 */
	public void applyDiscount(Amount amount) {
	 amount.multiply(1 - discountAmount/100);
	}
}
