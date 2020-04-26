package se.kth.iv1350.processSale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;

class ItemDTOTest {
	private ItemDTO breadItemDTO;
	
	@BeforeEach
	public void startUp() {
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		breadItemDTO = new ItemDTO(breadItemID, "Bread",breadItemPrice, "It´s whole grain!", 0);		
	}
	
	@AfterEach
	public void tearDown() {
		breadItemDTO = null;
	}

	@Test
	public void testEqual() {
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		ItemDTO ItemDTO = new ItemDTO(breadItemID, "Bread",breadItemPrice, "It´s whole grain!", 0);	
		boolean expResult = true;
		boolean result = breadItemDTO.equals(ItemDTO);
		assertEquals(expResult, result, "Equal ItemDTOs are not equal");
	}
	
	@Test
	public void testNotEqual() {
		Amount appleItemPrice = new Amount(74);
		ItemIdentifier appleItemID = new ItemIdentifier("002");
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, "Apple",appleItemPrice, "It´s a fruit!", 0.30);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(appleItemDTO);
		assertEquals(expResult, result, "Diffrent itemDTOs are equal");
	}
	
	@Test
	public void testNotEqualSItemID() {
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("002");
		ItemDTO ItemDTO = new ItemDTO(breadItemID, "Bread",breadItemPrice, "It´s whole grain!", 0);	
		boolean expResult = false;
		boolean result = breadItemDTO.equals(ItemDTO);
		assertEquals(expResult, result, "Invalid item ID");
	}
	
	@Test
	public void testNotEqualSItemName() {
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		ItemDTO ItemDTO = new ItemDTO(breadItemID, "Bröd",breadItemPrice, "It´s whole grain!", 0);	
		boolean expResult = false;
		boolean result = breadItemDTO.equals(ItemDTO);
		assertEquals(expResult, result, "Invalid item name");
	}
	
	@Test
	public void testNotEqualSItemPrice() {
		Amount breadItemPrice = new Amount(100);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		ItemDTO ItemDTO = new ItemDTO(breadItemID, "Bread",breadItemPrice, "It´s whole grain!", 0);	
		boolean expResult = false;
		boolean result = breadItemDTO.equals(ItemDTO);
		assertEquals(expResult, result, "Invalid item price");
	}
	
	@Test
	public void testNotEqualSItemDescription() {
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		ItemDTO ItemDTO = new ItemDTO(breadItemID, "Bread",breadItemPrice, "It isn´t whole grain!", 0);	
		boolean expResult = false;
		boolean result = breadItemDTO.equals(ItemDTO);
		assertEquals(expResult, result, "Invalid item description");
	}
	
	@Test
	public void testNotEqualSVatRate() {
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		ItemDTO ItemDTO = new ItemDTO(breadItemID, "Bread",breadItemPrice, "It´s whole grain!", 0.10);	
		boolean expResult = false;
		boolean result = breadItemDTO.equals(ItemDTO);
		assertEquals(expResult, result, "Invalid vat rate");
	}
	
	@Test
	public void testNotEqualsEmptyStringArg() {
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		ItemDTO ItemDTO = new ItemDTO(breadItemID, "",breadItemPrice, "It´s whole grain!", 0);	
		boolean expResult = false;
		boolean result = breadItemDTO.equals(ItemDTO);
		assertEquals(expResult, result, "Empty string return valid result");
	}
	
	@Test
	public void testNotEqualsNull() {
		ItemDTO nullItemDTO = null;
		boolean expResult = false;
		boolean result = breadItemDTO.equals(nullItemDTO);
		assertEquals(expResult, result, "Null ItemDTO is equal to a ItemDTO");
	}
	
	@Test
	public void testNotEqualsJavaLangObject() {
		Object other = new Object();
		boolean expResult = false;
		boolean result = breadItemDTO.equals(other);
		assertEquals(expResult, result, "java lang object is equal to a valid ItemDTO");
	}
}
