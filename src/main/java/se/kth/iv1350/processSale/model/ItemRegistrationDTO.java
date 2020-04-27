package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.util.Amount;

/**
 * Provides information about the <code>Sale</code> and an registered Item.
 * 
 */
public class ItemRegistrationDTO {
	private String itemName;
	private String itemDescription;
	private Amount itemPrice;
	private Amount runningTotal;
	
	/**
	 * Creates a new instance of a {@link ItemRegistrationDTO}.
	 * 
	 * @param itemDTO			Provides information about an item.
	 * @param runningTotal		Is the total <code>Amount</code> the have to be paid.
	 */
	public ItemRegistrationDTO(ItemDTO itemDTO, Amount runningTotal){
		this.itemName = itemDTO.getItemName();
		this.itemDescription = itemDTO.getItemDescription();
		this.itemPrice = itemDTO.getItemPrice();
		this.runningTotal= runningTotal;
	}
	
	/**
	 * Returns the item name.
	 * @return	The Item name.
	 */
	public String getItemName() {
		String itemNameCopy = new String(itemName);
		return itemNameCopy;
	}
	
	/**
	 * Returns the item description.
	 * @return	The item description.
	 */
	public String getItemDescription() {
		String itemDescriptionCopy = new String(itemDescription);
		return itemDescriptionCopy;
	}
	
	/**
	 * Returns the item price.
	 * @return The item price.
	 */
	public Amount getItemPrice() {
		Amount itemPriceCopy = new Amount(itemPrice);
		return itemPriceCopy;
	}
	
	/**
	 * Returns the running total. 
	 * @return	The running total.
	 */
	public Amount getRunningTotal() {
		Amount runningTotalCopy = new Amount(runningTotal);
		return runningTotalCopy;
	}
	
	/**
	 * Creates a string of the item registration dto and returns it.
	 * @return the corresponding string of the item registration dto.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item name: " + itemName + "\n");
		builder.append("Item description: " + itemDescription + "\n");
		builder.append("item price: " + itemPrice.toString() + "\n");
		builder.append("runningTotal: " + runningTotal.toString() + "\n");
		return builder.toString();
	}
}
