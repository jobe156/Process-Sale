package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.ItemIdentifier;

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
		ItemIdentifier itemIDCopy = new ItemIdentifier(itemID);
		return itemIDCopy;
	}
	
	public String getItemName() {
		String itemNameCopy = new String(itemName);
		return itemNameCopy;
	}
	
	public Amount getItemPrice() {
		Amount itemPriceCopy = new Amount(itemPrice);
		return itemPriceCopy;
	}
	
	public String getItemDescription() {
		String itemDesctiptionCopy = new String(itemDescription);
		return itemDescription;
	}
	
	public double getVatRate() {
		return vatRate;
	}
}
