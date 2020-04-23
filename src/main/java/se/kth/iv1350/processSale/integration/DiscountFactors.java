package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.Item;

public class DiscountFactors {
	private int numberOfItems;
	private Amount totalAmount;
	private	String itemName;
	
	public DiscountFactors(int numberOfItems, Amount totalAmount, String itemName) {
		this.numberOfItems = numberOfItems;
		this.totalAmount = totalAmount;
		this.itemName = itemName;
	}
	
	public DiscountFactors(Sale sale, Item item) {
		this.numberOfItems = sale.getTotalNumberOfItems();
		this.totalAmount = sale.CalculateFinalPrice();
		if(item != null)
			this.itemName = item.getItemName();
	}
	
	public DiscountFactors(DiscountFactors discountFactors) {
		this.numberOfItems = discountFactors.numberOfItems;
		this.totalAmount = discountFactors.totalAmount;
		this.itemName = discountFactors.itemName;
	}
	
	public int getNumberOfItems() {
		return numberOfItems;
	}
	
	public Amount getTotalAmount() {
		Amount totalAmountCopy = new Amount(totalAmount);
		return totalAmountCopy;
	}
	
	public String getItemName() {
		String itemNameCopy = new String(itemName);
		return itemNameCopy;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * Checks if the current <code>DiscountFactors</code> fulfill the given
	 * <code>DiscountFactors</code>.
	 * 
	 * @param discountFactors	The factors that are begin compared with.
	 * @return					True if the factors are fulfilled.
	 */
	public boolean equal(DiscountFactors discountFactors){
		if(discountFactors != null) {
			if(	this.numberOfItems <= discountFactors.numberOfItems &&
				this.totalAmount.compare(discountFactors.totalAmount) <= 0 &&
				this.itemName.equals(discountFactors.itemName))
					return true;
			}
		return false;
	}
	
	

}
