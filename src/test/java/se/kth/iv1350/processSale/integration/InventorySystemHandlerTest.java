package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemHandlerTest {
	private InventorySystemHandler ISHandler;
	private ItemDTO cerialItemDTO;

	@BeforeEach
	public void setUp() {
		ISHandler = new InventorySystemHandler();
		Amount cerialItemPrice = new Amount(110);
		ItemIdentifier cerialItemID = new ItemIdentifier("003");
		cerialItemDTO = new ItemDTO(cerialItemID, "Cereal", cerialItemPrice, "It contains dried friut!", 0.10);
	}

	@AfterEach
	public void tearDown() {
		ISHandler = null;
		cerialItemDTO = null;
	}

	@Test
	public void testFindItem() {
		ItemIdentifier cerialItemID = new ItemIdentifier("003");
		ItemDTO result = ISHandler.findItem(cerialItemID);
		assertEquals(cerialItemDTO, result, "Wrong itemDTO was found");
	}
	
	@Test
	public void testFindItemwrongItemID() {
		ItemIdentifier breadItemID = new ItemIdentifier("001");
		ItemDTO result = ISHandler.findItem(breadItemID);
		assertFalse(result.equals(cerialItemDTO), "diffrent ItemIDs return the same ItemDTO");
	}
	
	@Test
	public void testFindItemInvalidItemID() {
		ItemIdentifier invalidItemID = new ItemIdentifier("üüü");
		ItemDTO result = ISHandler.findItem(invalidItemID);
		assertNull( result, "ItemID was Invalid and itemDTO was found");
	}
	
	@Test
	public void testFindItemNullArg() {
		ItemIdentifier nullItemID = null;
		ItemDTO result = ISHandler.findItem(nullItemID);
		assertNull(result, "ItemID was null and itemDTO was found");
	}
	
	/*
	@Test
	public void testItemIsFound() {
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO result = ISHandler.findItem(itemID);
		assertNotNull(result, "validItemIdentifier returns null value");
	}
	
	@Test
	public void testNullArgument() {
		ItemIdentifier nullItemID = null;
		ItemDTO result = ISHandler.findItem(nullItemID);
		assertNull(result, "null ItemIdentifier argument returns a itemDTO");
	}

	@Test
	public void testItemIsNotFound() {
		ItemIdentifier itemID = new ItemIdentifier("£#ü");
		ItemDTO result = ISHandler.findItem(itemID);
		assertNull(result, "invalid identifier does´t return a null value");
	}
	*/
}
