package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.ItemIdentifier;

class ItemIdentifierTest {
	private ItemIdentifier itemID1;

	@BeforeEach
	public void startUp() {
		itemID1 = new ItemIdentifier("001");
	}

	@AfterEach
	public void tearDown() {
		itemID1 = null;
	}
	
	/*
	@Test
	public void testStringCunstructor() {
		String stringIdentifier = "001";
		ItemIdentifier result = new ItemIdentifier(stringIdentifier);
		assertNotNull(result, "Valid argument gave a null identifer");
	}
	
	@Test
	public void testConstructerEmptyStringArg() {
		String stringIdentifier = "";
		ItemIdentifier result = new ItemIdentifier(stringIdentifier);
		assertNull(result, "Empty string argument gave a valid identifer"); 
	}
	*/

	@Test
	public void testEqualIdentifiers() {
		String stringIdentifier2 = "001";
		ItemIdentifier itemID2 = new ItemIdentifier(stringIdentifier2);
		boolean expResult = true;
		boolean result = itemID1.equals(itemID2);
		assertEquals(expResult, result, "Equal identifiers are not equal");
	}
	
	@Test
	public void testNotEqualIdentifiers() {
		String stringIdentifier2 = "002";
		ItemIdentifier itemID2 = new ItemIdentifier(stringIdentifier2);
		boolean expResult = false;
		boolean result = itemID1.equals(itemID2);
		assertEquals(expResult, result, "Equal identifiers are not equal");
	}
	
	@Test
	public void testNotEqualsNull() {
		ItemIdentifier itemID2 = null;
		boolean expResult = false;
		boolean result = itemID1.equals(itemID2);
		assertEquals(expResult, result, "Null identifers is equal to a valid identifer");
	}
	
	@Test
	public void testNotEqualsJavaLangObject() {
		Object other = new Object();
		boolean expResult = false;
		boolean result = itemID1.equals(other);
		assertEquals(expResult, result, "java lang object is equal to valid identifer");
	}
}
