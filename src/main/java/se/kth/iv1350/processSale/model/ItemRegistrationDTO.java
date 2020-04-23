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
	 * Creates and instance of {@link ItemRegistrationDTO}.
	 * 
	 * @param itemDTO			Provides information about an item.
	 * @param runningTotal		Is the total <code>amount</code> the have to be paid.
	 */
	public ItemRegistrationDTO(ItemDTO itemDTO, Amount runningTotal){
		this.itemName = itemDTO.getItemName();
		this.itemDescription = itemDTO.getItemDescription();
		this.itemPrice = itemDTO.getItemPrice();
		this.runningTotal= runningTotal;
	}
	
	public String getItemName() {
		String itemNameCopy = new String(itemName);
		return itemNameCopy;
	}

	public String getItemDescription() {
		String itemDescriptionCopy = new String(itemDescription);
		return itemDescriptionCopy;
	}
	
	public Amount getItemPrice() {
		Amount itemPriceCopy = new Amount(itemPrice);
		return itemPriceCopy;
	}
	
	public Amount getRunningTotal() {
		Amount runningTotalCopy = new Amount(runningTotal);
		return runningTotalCopy;
	}
}
