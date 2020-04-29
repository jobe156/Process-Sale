package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;

import java.lang.StringBuilder;
/**
 * Represent the item that is being bought and its quantity.
 */
public class Item {
	private String itemName;
	private int quantity;
	private Amount itemPrice;
	private double itemVAT;

	/**
	 * Creates a new instance of a item from a <code>ItemDTO</code>.
	 * 
	 * @param itemDTO	Provides information about an item.
	 */
	Item(ItemDTO itemDTO) {
		this.itemName = itemDTO.getItemName();
		this.quantity = 1;
		this.itemPrice = itemDTO.getItemPrice();
		this.itemVAT = itemDTO.getVatRate();
	}
	
	/**
	 * Creates a new instance of a item from another <code>Item</code>.
	 * 
	 * @param itemDTO	Provides information about an item.
	 */
	Item(Item item) {
		this.itemName = item.itemName;
		this.quantity = item.quantity;
		this.itemPrice = item.itemPrice;
		this.itemVAT = item.itemVAT;
	}
	
	/**
	 * returns the item name.
	 * @return	The item name.
	 */
	public String getItemName() {
		return new String (itemName);
	}
	
	/**
	 * returns the quantity of the items. 
	 * @return	The quantity of the items.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Returns the item price.
	 * @return	The item price.
	 */
	public Amount getItemPrice() {
		return new Amount (itemPrice);
	}
	
	/**
	 * Returns the item vat rat.
	 * @return	The item vat rat.
	 */
	public double getItemVat() {
		return itemVAT;
	}
	
	/**
	 * increase the quantity of an item by one.
	 */
	public void increaseQuantity() {
		this.quantity++;
	}
	
	/**
	 * Calculates the total price of one type of {@link item}.
	 * 
	 * @return	the <code>Amount</code> equal to the item price multiplied by its quantity.
	 */
	public Amount totalItemPrice() {
		return new Amount(itemPrice).multiply(quantity);
	}
	
	/**
	 * returns the total vat amount of the items.
	 * @return	the total vat amount of the items.
	 */
	public Amount totalItemVatPrice() {
		return new Amount (this.totalItemPrice().multiply(itemVAT));
	}
	
	/**
	 * Creates a string of the item and returns it.
	 * @return the corresponding string of the item.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(itemName);
		builder.append( " x ");
		builder.append(Integer.toString(quantity));
		builder.append("\t");
		builder.append(itemPrice.add(itemPrice.multiply(itemVAT)).toString());
		return builder.toString();
	}
	
	/**
	 * Compares the current <code>Item</code> with a given to see if they are equal or not.
	 * 
	 * @param other A <code>Item</code> that the current one is compared with.
	 * @return		returns <code>True</code> if the two <code>SaleLogDTO</code>s are equal and <code>false</code> 
	 * 				if they are not. If the argument is null or not an instance of a <code>Item</code>, false 
	 * 				is returned.
	 */
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
