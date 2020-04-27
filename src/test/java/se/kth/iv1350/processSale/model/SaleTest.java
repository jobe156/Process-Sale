package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.util.Amount;

import java.util.ArrayList;
import java.util.List;

class SaleTest {
	private Sale sale;
	private ItemDTO breadItemDTO;
	private ItemDTO appleItemDTO;
	private ItemDTO cerialItemDTO;

	@BeforeEach
	public void setUp() {
		sale = new Sale();
		
		Amount breadItemPrice = new Amount(50);
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		breadItemDTO = new ItemDTO(breadItemID, "Bread", breadItemPrice, "It´s whole grain!", 0.1);
		
		Amount appleItemPrice = new Amount(74);
		ItemIdentifier appleItemID = new ItemIdentifier("002");
		appleItemDTO = new ItemDTO(appleItemID, "Apple",appleItemPrice, "It´s a fruit!", 0.30);
		
		Amount cerialItemPrice = new Amount(110);
		ItemIdentifier cerialItemID = new ItemIdentifier("003");
		cerialItemDTO = new ItemDTO(cerialItemID, "Cereal",cerialItemPrice, "It contains dried friut!", 0.10);
	}

	@AfterEach
	public void tearDown() {
		sale = null;
		breadItemDTO = null;
		appleItemDTO = null;
		cerialItemDTO = null;
	}
	
	@Test
	public void testAddItem() {
		Item breadItem = new Item(breadItemDTO);
		List<Item> expResult = new ArrayList<>();
		expResult.add(breadItem);
		sale.addItem(breadItemDTO);
		List<Item> result = sale.getItems();
		for(int i = 0; i < result.size(); i++) {
			assertEquals(expResult.get(i).getItemName(), result.get(i).getItemName(), "Items in sale has incorrect names");
			assertEquals(expResult.get(i).getQuantity(),result.get(i).getQuantity(), "items int sale has incorrect quanteties");
			assertEquals(expResult.get(i).getItemPrice(),result.get(i).getItemPrice(), "items int sale has incorrect item price");
			assertEquals(expResult.get(i).getItemVat(),result.get(i).getItemVat(), "items int sale has incorrect item vat rate");
		}
	}

	@Test
	public void testAddDiffrentItems() {
		Item breadItem = new Item(breadItemDTO);
		Item appleItem = new Item(appleItemDTO);
		Item cerialItem = new Item(cerialItemDTO);
		List<Item> expResult = new ArrayList<>();
		expResult.add(breadItem);
		expResult.add(appleItem);
		expResult.add(cerialItem);
		sale.addItem(breadItemDTO);
		sale.addItem(appleItemDTO);
		sale.addItem(cerialItemDTO);
		List<Item> result = sale.getItems();
		for(int i = 0; i < result.size(); i++) {
			assertEquals(expResult.get(i).getItemName(), result.get(i).getItemName(), "Items in sale has incorrect names");
			assertEquals(expResult.get(i).getQuantity(),result.get(i).getQuantity(), "items int sale has incorrect quanteties");
			assertEquals(expResult.get(i).getItemPrice(),result.get(i).getItemPrice(), "items int sale has incorrect item price");
			assertEquals(expResult.get(i).getItemVat(),result.get(i).getItemVat(), "items int sale has incorrect item vat rate");
		}	
	}
	
	@Test
	public void testAddItemAlreadyInSale() {
		Item breadItem = new Item(breadItemDTO);
		sale.addItem(breadItemDTO);
		List<Item> expResult = new ArrayList<>();
		for(int i = 0; i < 4; i++) {
			breadItem.increaseQuantity();
			sale.addItem(breadItemDTO);
		}
		expResult.add(breadItem);
		List<Item> result = sale.getItems();
		for(int i = 0; i < result.size(); i++) {
			assertEquals(expResult.get(i).getItemName(), result.get(i).getItemName(), "Items in sale has incorrect names");
			assertEquals(expResult.get(i).getQuantity(),result.get(i).getQuantity(), "items int sale has incorrect quanteties");
			assertEquals(expResult.get(i).getItemPrice(),result.get(i).getItemPrice(), "items int sale has incorrect item price");
			assertEquals(expResult.get(i).getItemVat(),result.get(i).getItemVat(), "items int sale has incorrect item vat rate");
		}
	}
	
	@Test
	public void testAddItemNullItemDto() {
		ItemDTO itemDTO = null;
		sale.addItem(itemDTO);
		boolean result = sale.getItems().isEmpty();
		assertTrue(result, "list contains a invalid item");
	}
	
	@Test
	public void testCalculatefinalPrice() {
		double totalVat = 1 + breadItemDTO.getVatRate();
		Amount expResult = breadItemDTO.getItemPrice().multiply(totalVat);
		sale.addItem(breadItemDTO);
		Amount result = sale.CalculateFinalPrice();
		assertEquals(expResult, result, "the total price of the sale was not successful calculated ");
	}
	
	@Test
	public void testCalculatefinalPriceNoItems() {
			Amount expResult = new Amount();
			Amount result = sale.CalculateFinalPrice();
			assertEquals(expResult, result, "the total price of the sale was not successful calculated ");
	}
	
	@Test
	public void testGetTotalNumberOfItems() {
		Item breadItem = new Item(breadItemDTO);
		Item cerialItem = new Item(cerialItemDTO);
		List<Item> itemList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			itemList.add(breadItem);
			itemList.add(cerialItem);
			sale.addItem(breadItemDTO);
			sale.addItem(cerialItemDTO);
		}
		int expResult = 0;
		for(Item item: itemList)
			expResult += item.getQuantity();
		int result = sale.getTotalNumberOfItems();
		assertEquals(expResult, result, "total number of items is not correct");
	}
	
	@Test
	public void testGetTotalNumberOfItemsZeroItems() {
		int expResult = 0;
		int result = sale.getTotalNumberOfItems();
		assertEquals(expResult, result, "total number of items is not correct");
	}
}