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
		String appleStringIdentifier = "002";
		double appleItemValue = 74;
		String appleItemName = "Apple";
		String appleItemDescription = "It´s a fruit!";
		double appleItemVat = 0.30;
		Amount appleItemPrice = new Amount(appleItemValue);
		ItemIdentifier appleItemID = new ItemIdentifier(appleStringIdentifier);
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, appleItemName, appleItemPrice, appleItemDescription, appleItemVat);
		ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(appleItemDTO, runningTotal);
		StringBuilder builder = new StringBuilder();
		builder.append("Item name: " + itmRegDto.getItemName() + "\n");
		builder.append("Item description: " + itmRegDto.getItemDescription() + "\n");
		builder.append("item price: " + itmRegDto.getItemPrice().multiply(1 + 0.3).toString() + "\n");
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
		double appleVat = 0.30;
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, stringName ,appleItemPrice, stringDescription, appleVat);
		ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(appleItemDTO, runningTotal);
		StringBuilder builder = new StringBuilder();
		builder.append("Item name: " + itmRegDto.getItemName() + "\n");
		builder.append("Item description: " + itmRegDto.getItemDescription() + "\n");
		builder.append("item price: " + itmRegDto.getItemPrice().multiply(1 + 0.3).toString() + "\n");
		builder.append("runningTotal: " + runningTotal.toString() + "\n");
		String expResult = builder.toString();
		String result = itmRegDto.toString();
		assertEquals(expResult, result, "String was not correctly made");
	}
}
