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
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
	}
	
	@AfterEach
	public void tearDown() {
		breadItemDTO = null;
	}

	@Test
	public void testEqual() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO itemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		boolean expResult = true;
		boolean result = breadItemDTO.equals(itemDTO);
		assertEquals(expResult, result, "Equal ItemDTOs are not equal");
	}
	
	@Test
	public void testNotEqual() {
		String appleStringIdentifier = "002";
		double appleItemValue = 74;
		String appleItemName = "Apple";
		String appleItemDescription = "It´s a fruit!";
		double appleItemVat = 0.30;
		Amount appleItemPrice = new Amount(appleItemValue);
		ItemIdentifier appleItemID = new ItemIdentifier(appleStringIdentifier);
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, appleItemName, appleItemPrice, appleItemDescription, appleItemVat);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(appleItemDTO);
		assertEquals(expResult, result, "Diffrent itemDTOs are equal");
	}
	
	@Test
	public void testNotEqualSItemID() {
		String breadStringIdentifier = "002";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO itemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(itemDTO);
		assertEquals(expResult, result, "Invalid item ID");
	}
	
	@Test
	public void testNotEqualSItemName() {	
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bröd";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO itemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(itemDTO);
		assertEquals(expResult, result, "Invalid item name");
	}
	
	@Test
	public void testNotEqualSItemPrice() {
		String breadStringIdentifier = "001";
		double breadItemValue = 100;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO itemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(itemDTO);
		assertEquals(expResult, result, "Invalid item price");
	}
	
	@Test
	public void testNotEqualSItemDescription() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bröd";
		String breadItemDescription = "It isn´t whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO itemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(itemDTO);
		assertEquals(expResult, result, "Invalid item description");
	}
	
	@Test
	public void testNotEqualSVatRate() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.10;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO itemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(itemDTO);
		assertEquals(expResult, result, "Invalid vat rate");
	}
	
	@Test
	public void testNotEqualsEmptyStringArg() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "";
		String breadItemDescription = "";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO itemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		boolean expResult = false;
		boolean result = breadItemDTO.equals(itemDTO);
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
