package se.kth.iv1350.processSale.integration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.util.Amount;

/**
 * Contains information about a <code>sale</code> and the corresponding <code>CashPayment</code>.
 */
public class SaleLogDTO {
	private String storeName;
	private String storeAddress;
	private LocalTime localTime;
	private List<Item> items;
	private double totalVatRate = 0;
	private Amount totalPrice;
	private Amount paidAmount;
	private Amount change;
	
	/**
	 * creates a new instance of a SaleLogDTO.
	 * @param currentSale	Provides information about the registered <code>Item</code>s and 
	 * 						static store information.
	 * @param cPayment		Provides information about the <code>CashPayment</code>.
	 */
	public SaleLogDTO(Sale currentSale, CashPayment cPayment) {
		this.storeName = currentSale.getStoreName();
		this.localTime = currentSale.getTimeOfSale();
		this.storeAddress = currentSale.getStoreAddress();
		this.items = currentSale.getItems();
		this.totalPrice = currentSale.CalculateFinalPrice();
		for(Item item: items)
			this.totalVatRate += item.getItemVat();
		this.change = cPayment.getChange();
		this.paidAmount = cPayment.getAmountPaid();
	}
	
	/**
	 * returns the store name.
	 * 
	 * @return The store name.
	 */
	public String getStoreName() {
		return new String(storeName);
	}
	
	/**
	 * Returns the store address.
	 * @return	The store address.
	 */
	public String getStoreAddress() {
		return new String(storeAddress);
	}
	
	/**
	 * Returns the time of sale.
	 * @return	The time of sale.
	 */
	public LocalTime getTimeOfSale() {
		return localTime;
	}
	
	/**
	 * Returns the list of registered <code>Item</code>s of the sale.
	 * @return	The list of registered <code>Items</code>s.
	 */
	public List<Item> getItems() {
		List<Item> itemsCopy = new ArrayList<Item>(items);
		return itemsCopy;
	}
	
	/**
	 * Returns the total vat rate.
	 * @return The total vat rate.
	 */
	public double getTotalVatRate() {
		return totalVatRate;
	}
	
	/**
	 * Returns the total price of the sale.
	 * @return The total price of the sale.
	 */
	public Amount getTotalPrice() {
		return new Amount(totalPrice);
	}
	
	/**
	 * Returns the paid amount.
	 * @return The paid amount.
	 */
	public Amount getPaidAmount() {
		return new Amount(paidAmount);
	}

	/**
	 * Returns the change.
	 * @return The change.
	 */
	public Amount getChange() {
		return new Amount(change);
	}
	
	/**
	 * Compares the current <code>SaleLogDTO</code> with a given to see if they are equal or not.
	 * 
	 * @param other A <code>SaleLogDTO</code> that the current one is compared with.
	 * @return		returns <code>True</code> if the two <code>SaleLogDTO</code>s are equal and <code>false</code> 
	 * 				if they are not. If the argument is null or not an instance of a <code>ItemDTO</code>, false 
	 */
	@Override 
	public boolean equals(Object other) {
		if(other == null || !(getClass() == other.getClass()))
			return false;
		SaleLogDTO otherSaleLogDTO = (SaleLogDTO) other;
		if(!(storeName.equals(otherSaleLogDTO.storeName)))
			return false;
		if(!(storeAddress.equals(otherSaleLogDTO.storeAddress)))
			return false;
		if(!(localTime.equals(otherSaleLogDTO.localTime)))
			return false;
		
		for(int i = 0; i < items.size()-1; i++)
			if(!(items.get(i).equals(otherSaleLogDTO.items.get(i))))
				return false;
		
		if(!(totalPrice.equals(otherSaleLogDTO.totalPrice)))
			return false;		
		if(totalVatRate != otherSaleLogDTO.totalVatRate)
			return false;
		if(!(change.equals(otherSaleLogDTO.change)))
			return false;
		if(!(paidAmount.equals(otherSaleLogDTO.paidAmount)))
			return false;
		return true;
	}
}
