package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;


/**
 * 
 *The handler is responsible for communicating with the external
 *inventory system.
 */
public class InventorySystemHandler {
	private List<ItemDTO> items = new ArrayList<>();
	 
	/**
	 * creates the itemSystemHandler and adds Items;
	 */
	
	InventorySystemHandler(){
		addItems();
		
	}
	
	private void addItems() {
		Amount itemPrice1 = new Amount(50);
		ItemIdentifier itemID1 = new ItemIdentifier("001");
		ItemDTO itemDTO1 = new ItemDTO(itemID1, "Bread",itemPrice1, "It´s whole grain!", 0);
		items.add(itemDTO1);
		
		Amount itemPrice2 = new Amount(74);
		ItemIdentifier itemID2 = new ItemIdentifier("002");
		ItemDTO itemDTO2 = new ItemDTO(itemID2, "Apple",itemPrice2, "It´s a fruit!", 0.30);
		items.add(itemDTO2);
		
		Amount itemPrice3 = new Amount(110);
		ItemIdentifier itemID3 = new ItemIdentifier("003");
		ItemDTO itemDTO3 = new ItemDTO(itemID3, "Cereal",itemPrice3, "It contains dried friut!", 0.10);
		items.add(itemDTO3);
	}
	
	/**
	 * 
	 * Finds the item with the same <code>ItemIdentifier</code> from the inventorySystem.
	 * Returns null if the item is not found.
	 * 
	 * @param ItemID	Used to identify the searched item.
	 * @return			the searched <code>ItemDTO</code> or null.
	 */
	public ItemDTO findItem(ItemIdentifier itemID) {
		if(itemID != null){
			for(ItemDTO itemDTO: items)
				if( itemDTO.getItemIdentifier().equals(itemID))
					return itemDTO;
		}
		return null;
	}
}
