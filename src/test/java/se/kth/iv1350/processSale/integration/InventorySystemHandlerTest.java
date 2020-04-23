package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.util.ItemIdentifier;

class InventorySystemHandlerTest {
	private InventorySystemHandler ISHandler;

	@BeforeEach
	public void setUp() {
		ISHandler = new InventorySystemHandler();
	}

	@AfterEach
	public void tearDown() {
		ISHandler = null;
	}

	@Test
	public void testNullArgument() {
		ISHandler.addItems();
		ItemIdentifier nullItemID = null;
		ItemDTO result = ISHandler.findItem(nullItemID);
		assertNull(result, "null ItemIdentifier argument returns a itemDTO");
	}

	@Test
	public void testItemIsFound() {
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO result = ISHandler.findItem(itemID);
		assertNotNull(result, "validItemIdentifier returns null value");
	}

	@Test
	public void testItemIsNotFound() {
		ItemIdentifier itemID = new ItemIdentifier("£#ü");
		ItemDTO result = ISHandler.findItem(itemID);
		assertNull(result, "invalid identifier does´t return a null value");
	}
}
