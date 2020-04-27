package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;

/**
 * Contains information about an <code>item</code>.
 */
public class ItemDTO {
	private ItemIdentifier itemID;
	private String itemName;
	private Amount itemPrice;
	private String itemDescription;
	private double vatRate;
	
	/**
	 * Creates a new instance of a itemDTO.
	 * 
	 * @param itemID			The items identification.
	 * @param itemName			The name of the item.
	 * @param itemPrice			The <code>Amount</code> that is being charged for the item.
	 * @param itemDescription	An short description of the item.
	 * @param vatRate			The vat rate of the item.
	 */
	public ItemDTO(ItemIdentifier itemID, String itemName, Amount itemPrice, String itemDescription, double vatRate){
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDescription = itemDescription;
		this.vatRate = vatRate;
	}
	
	/**
	 * returns the items <code>ItemIdentifier</code>.
	 * 
	 * @return The items <code>ItemIdentifier</code>.
	 */
	public ItemIdentifier getItemIdentifier() {
		return new ItemIdentifier(itemID);
	}
	
	/**
	 * returns the item name.
	 * 
	 * @return	the item name.
	 */
	public String getItemName() {
		return new String (itemName);
	}
	
	/**
	 * Returns the  the item price.
	 * 
	 * @return	The item price.
	 */
	public Amount getItemPrice() {
		return new Amount(itemPrice);
	}
	
	/**
	 *Returns the item description.
	 *
	 * @return The item description.
	 */
	public String getItemDescription() {
		return new String(itemDescription);
	}
	
	/**
	 * Returns the items vat rate.
	 * 
	 * @return	The items vat rate.
	 */
	public double getVatRate() {
		return vatRate;
	}
	
	/**
	 * Compares the current <code>ItemDTO</code> with a given to see if they are equal or not.
	 * 
	 * @param other A <code>ItemDTO</code> that the current one is compared with.
	 * @return		returns <code>True</code> if the two <code>ItemDTO</code>s are equal and <code>false</code> 
	 * 				if they are not. If the argument is null or not an instance of a <code>ItemDTO</code>, false 
	 * 				is returned.
	 */
	@Override
	public boolean equals(Object other) {
		if(other == null || !(getClass() == other.getClass()))
			return false;
		ItemDTO otherItemDTO = (ItemDTO) other;
		if(!(itemID.equals(otherItemDTO.itemID)))
			return false;
		if(!(itemName.equals(otherItemDTO.itemName)))
			return false;
		if(!(itemPrice.equals(otherItemDTO.itemPrice)))
			return false;
		if(!(itemDescription.equals(otherItemDTO.itemDescription)))
			return false;
		if(vatRate != otherItemDTO.vatRate)
			return false;
		return true;
	}
}
