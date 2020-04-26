package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.util.Amount;

class ItemTest {
	private Item breadItem;

	@BeforeEach
	public void setUp() {
	Amount itemPrice = new Amount(50);
	ItemIdentifier itemID = new ItemIdentifier("001");
	ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0);
	breadItem = new Item(itemDTO);
	}
	
	@AfterEach
	public void tearDown() {
		breadItem = null;
	}
	
	@Test
	public void testIncreaseQuantity() {
		int expQuantity = 2;
		breadItem.increaseQuantity();
		int resultingQuantity = breadItem.getQuantity();
		assertEquals(expQuantity, resultingQuantity, "breadItem quantity is´t increased.");
	}
	
	@Test
	public void testTotalbreadItemPrice() {
		Amount breadItemPrice = breadItem.getItemPrice();
		Amount expResult = breadItemPrice.multiply(5);
		for(int i = 0; i < 4; i++)
			breadItem.increaseQuantity();
		Amount result = breadItem.totalItemPrice();	
		assertEquals(expResult, result, "Calculation of total breadItem price went wrong.");
	}
	
	@Test
	public void testToString() {
		StringBuilder builder = new StringBuilder();
		builder.append(breadItem.getItemName());
		builder.append(" x ");
		builder.append(Integer.toString(breadItem.getQuantity()));
		builder.append(" ");
		builder.append(breadItem.getItemPrice().toString());
		String expResult =  builder.toString();
		String result = breadItem.toString();
		assertEquals(expResult, result, "ToString operation went wrong.");
	}
	
	@Test
	public void testEquals() {
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0);
		Item item = new Item(itemDTO);
		boolean expResult = true;
		boolean result = breadItem.equals(item);
		assertEquals(expResult, result, "Equals items are not equal");
	}
	
	@Test
	public void tetsNotEquals() {
		Amount appleItemPrice = new Amount(74);
		ItemIdentifier appleItemID = new ItemIdentifier("002");
		ItemDTO appleItemDTO = new ItemDTO(appleItemID, "Apple",appleItemPrice, "It´s a fruit!", 0.30);
		Item appleItem = new Item(appleItemDTO);
		boolean expResult = false;
		boolean result = breadItem.equals(appleItem);
		assertEquals(expResult, result, "Diffrent items are equal");
	}
	
	@Test
	public void testNotEqualsItemName() {
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bröd",itemPrice, "It´s whole grain!", 0);
		Item item = new Item(itemDTO);
		boolean expResult = false;
		boolean result = breadItem.equals(item);
		assertEquals(expResult, result, "Invalid item name");
	}
	
	@Test
	public void testNotEqualsQuantity() {
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0);
		Item item = new Item(itemDTO);
		item.increaseQuantity();
		boolean expResult = false;
		boolean result = breadItem.equals(item);
		assertEquals(expResult, result, "invalid quantity");
	}
	
	@Test
	public void testNotEqualsItemPrice() {
		Amount itemPrice = new Amount(1000);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0);
		Item item = new Item(itemDTO);
		boolean expResult = false;
		boolean result = breadItem.equals(item);
		assertEquals(expResult, result, "Invalid item price");
	}
	
	@Test
	public void testNotEqualsItemVat() {
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0.5);
		Item item = new Item(itemDTO);
		boolean expResult = false;
		boolean result = breadItem.equals(item);
		assertEquals(expResult, result, "invalid vatRate");
	}
	
	@Test
	public void testNotEqualsNullArg() {
		Item item = null;
		boolean expResult = false;
		boolean result = breadItem.equals(item);
		assertEquals(expResult, result, "invalid null argument");
	}
	
	@Test
	public void testNotEqualsJavaLangObject() {
		Object other = new Object();
		boolean expResult = false;
		boolean result = breadItem.equals(other);
		assertEquals(expResult, result, "invalid java lang object");
	}
}
