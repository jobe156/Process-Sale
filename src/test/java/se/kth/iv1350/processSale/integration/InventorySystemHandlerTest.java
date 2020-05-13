package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import se.kth.iv1350.processSale.model.InvalidItemIdentifierException;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemHandlerTest {
	private InventorySystemHandler ISHandler;
	private ItemDTO cerealItemDTO;

	@BeforeEach
	public void setUp() {
		ISHandler = new InventorySystemHandler();
		
		String cerealStringIdentifier = "003";
		double cerealItemValue = 110;
		String cerealItemName = "Cereal";
		String cerealItemDescription = "It contains dried friut!";
		double cerealItemVat = 0.10;
		Amount cerealItemPrice = new Amount(cerealItemValue);
		ItemIdentifier cerealItemID = new ItemIdentifier(cerealStringIdentifier);
		cerealItemDTO = new ItemDTO(cerealItemID, cerealItemName, cerealItemPrice, cerealItemDescription, cerealItemVat);
		
	}

	@AfterEach
	public void tearDown() {
		ISHandler = null;
		cerealItemDTO = null;
	}

	@Test
	public void testFindItem() {
		String cerialStringIdentifier = "003";
		ItemIdentifier cerialItemID = new ItemIdentifier(cerialStringIdentifier);
		try {
			ItemDTO result = ISHandler.findItem(cerialItemID);
			assertEquals(cerealItemDTO, result, "Wrong itemDTO was found");
		}catch(Exception exp) {
			fail("An unintentional exception was thrown");
		}
	}
	
	@Test
	public void testFindItemwrongItemID() {
		String breadStringIdentifier = "001";
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		try {
			ItemDTO result = ISHandler.findItem(breadItemID);
			assertFalse(result.equals(cerealItemDTO), "diffrent ItemIDs return the same ItemDTO");
		}catch(Exception exp) {
			fail("An unintentional exception was caught");
		}
	}
	
	@Test
	public void testFindItemInvalidItemID() {
		String invalidStrinIdentifier = "üüü";
		ItemIdentifier invalidItemID = new ItemIdentifier(invalidStrinIdentifier);
		try {
		ISHandler.findItem(invalidItemID);
		fail("Unvalid identifier returned a valid ItemDTO");
		}catch(InvalidItemIdentifierException exp) {
			assertTrue(exp.getInvalidItemIdentifier().equals(invalidItemID), "ItemID was invalid and "
																			+ "itemDTO was found");
		}
	}
	
	@Disabled
	@Test
	public void testFindItemNullArg()throws InvalidItemIdentifierException{
		ItemIdentifier nullItemID = null;
		try {
			ISHandler.findItem(nullItemID);
			fail("null identifier returnd a valid ItemDTO");
		}catch(IllegalStateException exp) {
			assertTrue(exp.getMessage().equals("The given item identifer is null"));	
		}	
	}
	
	@Test
	public void testfindItemInventorySystemNotRespondingException() throws InvalidItemIdentifierException{
		String notRespStringIdentifier = "abc";
		ItemIdentifier notRespIdentifier = new ItemIdentifier(notRespStringIdentifier);
		try {
			ISHandler.findItem(notRespIdentifier);
			fail("The correct exception was not called.");
		}catch(ItemRegistrationException exp){
			assertTrue(exp.getMessage().equals("The Inventory System is´t responding."));
		}
	}
}
