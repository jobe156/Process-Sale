package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;

import java.lang.StringBuilder;
/**
 * 
 * Represent the item that is being bought and its quantity.
 *
 */
public class Item {
	private String itemName;
	private int quantity;
	private Amount itemPrice;
	private double itemVAT;

	/**
	 * Creates an instance of the class.
	 * 
	 * @param itemDTO	Provides information about an item.
	 */
	public Item(ItemDTO itemDTO) {
		this.itemName = itemDTO.getItemName();
		this.quantity = 1;
		this.itemPrice = itemDTO.getItemPrice();
		this.itemVAT = itemDTO.getVatRate();
	}
	
	
	public Item(Item item) {
		this.itemName = item.itemName;
		this.quantity = item.quantity;
		this.itemPrice = item.itemPrice;
		this.itemVAT = item.itemVAT;
	}
	
	public String getItemName() {
		return new String (itemName);
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Amount getItemPrice() {
		return new Amount (itemPrice);
	}
	
	public double getItemVat() {
		return itemVAT;
	}
	
	/**
	 * increase the quantity of an item.
	 * 
	 * @Param quantity	Is how much the quantity will be increased.
	 * 
	 */
	public void increaseQuantity() {
		this.quantity++;
	}
	
	/**
	 * Calculates the total price of on type of {@link item}.
	 * 
	 * @return	the <code>Amount</code> equal to the item price multiplied by its quantity.
	 */
	public Amount totalItemPrice() {
		return new Amount(itemPrice).multiply(quantity);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(itemName);
		builder.append( " x ");
		builder.append(Integer.toString(quantity));
		builder.append("\t");
		builder.append(itemPrice.toString());
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null || !(getClass() == other.getClass()))
			return false;
		Item otherItem = (Item) other;
		if(!(itemName.equals(otherItem.itemName)))
			return false;
		if(quantity != otherItem.quantity)
			return false;
		if(!(itemPrice.equals(otherItem.itemPrice)))
			return false;
		if(itemVAT != otherItem.itemVAT)
			return false;
		return true;
	}
	
}
