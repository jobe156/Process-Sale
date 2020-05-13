package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.InvalidItemIdentifierException;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;


/**
 *Represents a program that communicates with an storage system. In this case, it´s used to
 * manages a <code>Item</code> List.
 */
public class InventorySystemHandler {
	private List<ItemDTO> items = new ArrayList<>();
	private ItemIdentifier invalidItemIdentifier;
	 
	/**
	 * creates a new instance of a inventory system hander.
	 */
	InventorySystemHandler(){
		addItems();
		
	}
	
	private void addItems() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		items.add(breadItemDTO);
		
		String appleStringIdentifier = "002";
		double appleItemValue = 74;
		String appleItemName = "Apple";
		String appleItemDescription = "It´s a fruit!";
		double appleItemVat = 0.3;
		Amount appleItemPrice = new Amount(appleItemValue);
		ItemIdentifier appleItemID = new ItemIdentifier(appleStringIdentifier);
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, appleItemName, appleItemPrice, appleItemDescription, appleItemVat);
		items.add(appleItemDTO);
		
		String cerealStringIdentifier = "003";
		double cerealItemValue = 110;
		String cerealItemName = "Cereal";
		String cerealItemDescription = "It contains dried friut!";
		double cerealItemVat = 0.1;
		Amount cerealItemPrice = new Amount(cerealItemValue);
		ItemIdentifier cerealItemID = new ItemIdentifier(cerealStringIdentifier);
		ItemDTO cerealItemDTO = new ItemDTO(cerealItemID, cerealItemName, cerealItemPrice, cerealItemDescription, cerealItemVat);
		items.add(cerealItemDTO);
		
		String invalidStringIdentifier = "abc";
		invalidItemIdentifier = new ItemIdentifier(invalidStringIdentifier);
	}
	
	/**
	 * Finds the item with the same <code>ItemIdentifier</code> from the inventorySystem.
	 * 
	 * @param ItemID	Used to identify the searched item.
	 * @return			The searched <code>ItemDTO</code> or null.
	 * 
	 * @throws InvalidItemIdentifierException 			If the given <code>ItemIdentifier<code> does not have 
	 * 													a corresponding <code>ItemDTO<code> in the inventory 
	 * 													system.
	 * @throws ItemRegistrationException  	If the inventory system is not responding.
	 * @throws NullPointerException						If the given <code>itemIdentifier<code> is null.
	 */
	public ItemDTO findItem(ItemIdentifier itemID) throws InvalidItemIdentifierException{			
		if(itemID.equals(invalidItemIdentifier))
			throw new ItemRegistrationException("The Inventory System is´t responding.");
			
		for(ItemDTO itemDTO: items)
			if( itemDTO.getItemIdentifier().equals(itemID))
				return itemDTO;
			
		throw new InvalidItemIdentifierException(itemID);
	}
}
