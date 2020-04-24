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
	public Item(ItemDTO itemDTO, int quantity) {
		this.itemName = itemDTO.getItemName();
		this.quantity = quantity;
		this.itemPrice = itemDTO.getItemPrice();
		this.itemVAT = itemDTO.getVatRate();
	}
	
	
	public Item(Item item) {
		this.itemName = item.getItemName();
		this.quantity = item.getQuantity();
		this.itemPrice = item.getItemPrice();
		this.itemVAT = item.getItemVat();
	}
	
	public String getItemName() {
		String itemNameCopy = new String(itemName);
		return itemNameCopy;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Amount getItemPrice() {
		Amount itemPriceCopy = new Amount(itemPrice);
		return itemPriceCopy;
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
	public void increaseQuantity(int quantity) { // have to concider negative quantity
		if(quantity < 0)
			return;
		this.quantity += quantity;
	}
	
	
	/**
	 * Calculates the total price of on type of {@link item}.
	 * 
	 * @return	the <code>Amount</code> equal to the item price multiplied by its quantity.
	 */
	public Amount totalItemPrice() {
		Amount totalItemPrice = new Amount(itemPrice);
		totalItemPrice.multiply(quantity);
		return totalItemPrice;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(itemName + " x " + quantity + "  " + itemPrice.toString());
		return builder.toString();
	}
}
