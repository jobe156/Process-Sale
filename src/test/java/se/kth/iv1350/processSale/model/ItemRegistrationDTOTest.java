package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;

import org.junit.jupiter.api.Test;

class ItemRegistrationDTOTest {

	@Test
	public void testToString() {
		Amount runningTotal = new Amount(148);
		Amount appleItemPrice = new Amount(74);
		ItemIdentifier appleItemID = new ItemIdentifier("002");
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, "Apple",appleItemPrice, "It´s a fruit!", 0.30);
		ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(appleItemDTO, runningTotal);
		StringBuilder builder = new StringBuilder();
		builder.append("Item name: " + itmRegDto.getItemName() + "\n");
		builder.append("Item description: " + itmRegDto.getItemDescription() + "\n");
		builder.append("item price: " + itmRegDto.getItemPrice().toString() + "\n");
		builder.append("runningTotal: " + runningTotal.toString() + "\n");
		String expResult = builder.toString();
		String result = itmRegDto.toString();
		assertEquals(expResult, result, "String was not correctly made");
	}
	
	@Test
	public void testToStringEmptyStringParameter() {
		Amount runningTotal = new Amount(148);
		Amount appleItemPrice = new Amount(74);
		String StringIdentifer = "001";
		ItemIdentifier appleItemID = new ItemIdentifier(StringIdentifer);
		String stringName = "";
		String stringDescription = "";
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, stringName ,appleItemPrice, stringDescription, 0.30);
		ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(appleItemDTO, runningTotal);
		StringBuilder builder = new StringBuilder();
		builder.append("Item name: " + itmRegDto.getItemName() + "\n");
		builder.append("Item description: " + itmRegDto.getItemDescription() + "\n");
		builder.append("item price: " + itmRegDto.getItemPrice().toString() + "\n");
		builder.append("runningTotal: " + runningTotal.toString() + "\n");
		String expResult = builder.toString();
		String result = itmRegDto.toString();
		assertEquals(expResult, result, "String was not correctly made");
	}
}
