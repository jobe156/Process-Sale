package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.Discount;
import se.kth.iv1350.processSale.integration.DiscountFactors;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

/**
 * 
 * The handler is responsible for communicating with the discount system.
 */
public class DiscountRepositoryHandler {
	private List<Discount> discounts = new ArrayList<>();

	/**
	 * creates and instance of {@link DiscountRepositoryHandler}
	 */
	DiscountRepositoryHandler() {
		addDiscounts();

	} 

	private void addDiscounts() {
		Amount amount1 = new Amount(120);
		DiscountFactors discountFactors1 = new DiscountFactors(4, amount1, "Bread");
		Discount discount1 = new Discount(discountFactors1, 5);
		discounts.add(discount1);

		Amount amount2 = new Amount(120);
		DiscountFactors discountFactors2 = new DiscountFactors(7, amount2, "Apple");
		Discount discount2 = new Discount(discountFactors2, 10);
		discounts.add(discount2);
	}
/**
 * Finds a <code>discount</code> based on a given <code>discountDactor</code>.
 * 
 * @param discountFactors	Is factors that need to be 
 * @return
 */
	public Discount findDiscount(DiscountFactors discountFactors) {
		for(Discount disc: discounts) 
			if(disc.getDiscountFactors().equal(discountFactors))
				return disc;
		
		return null;
	}
}
