package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;

/**
 * 
 * Contains information about an item.
 *
 */

public class ItemDTO {
	private ItemIdentifier itemID;
	private String itemName;
	private Amount itemPrice;
	private String itemDescription; //consider switching types.
	private double vatRate;	//consider creating VAT rate class.
	
	/**
	 * 
	 * Creates an instance of an
	 * 
	 * @param itemID			The items identification in a store.
	 * @param itemName			The name of the item.
	 * @param itemPrice			The <code>Amount</code> that is being charged for the item.
	 * @param itemDescription	An short description of the item.
	 * @param vatRate			The vat rate of an item.
	 */
	public ItemDTO(ItemIdentifier itemID, String itemName, Amount itemPrice, String itemDescription, double vatRate){
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDescription = itemDescription;
		this.vatRate = vatRate;
	}
	
	public ItemIdentifier getItemIdentifier() {
		return new ItemIdentifier(itemID);
	}
	
	public String getItemName() {
		return new String (itemName);
	}
	
	public Amount getItemPrice() {
		return new Amount(itemPrice);
	}
	
	public String getItemDescription() {
		return new String(itemDescription);
	}
	
	public double getVatRate() {
		return vatRate;
	}
	
	
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
