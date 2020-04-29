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
	private ItemDTO cerealItemDTO;

	@BeforeEach
	public void setUp() {
		sale = new Sale();
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.1;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);

		String appleStringIdentifier = "002";
		double appleItemValue = 74;
		String appleItemName = "Apple";
		String appleItemDescription = "It´s a fruit!";
		double appleItemVat = 0.30;
		Amount appleItemPrice = new Amount(appleItemValue);
		ItemIdentifier appleItemID = new ItemIdentifier(appleStringIdentifier);
		appleItemDTO = new ItemDTO(appleItemID, appleItemName, appleItemPrice, appleItemDescription, appleItemVat);
		
		String cerealStringIdentifier = "003";
		double cerealItemValue = 110;
		String cerealItemName = "Cereal";
		String cerealItemDescription = "It contains dried friut!";
		double cerealItemVat = 0.10;
		Amount cerealItemPrice = new Amount(cerealItemValue);
		ItemIdentifier cerealItemID = new ItemIdentifier(cerealStringIdentifier);
		cerealItemDTO = new ItemDTO(cerealItemID, cerealItemName, cerealItemPrice, cerealItemDescription, cerealItemVat);;
	}

	@AfterEach
	public void tearDown() {
		sale = null;
		breadItemDTO = null;
		appleItemDTO = null;
		cerealItemDTO = null;
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
		Item cerialItem = new Item(cerealItemDTO);
		List<Item> expResult = new ArrayList<>();
		expResult.add(breadItem);
		expResult.add(appleItem);
		expResult.add(cerialItem);
		sale.addItem(breadItemDTO);
		sale.addItem(appleItemDTO);
		sale.addItem(cerealItemDTO);
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
		Amount amount = new Amount (breadItemDTO.getItemPrice());
		Amount expResult = new Amount(amount.add(breadItemDTO.getItemPrice().multiply(breadItemDTO.getVatRate())));
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
	
}