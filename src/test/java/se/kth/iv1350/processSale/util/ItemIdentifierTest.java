package se.kth.iv1350.processSale.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@Test
	public void testEqualNullArgument() {
		ItemIdentifier nullIdentifier = null;
		boolean expResult = false;
		boolean result = itemID1.equals(nullIdentifier);
		assertEquals(expResult, result, "ItemIndetifier instance equal to null.");
	}

	@Test
	public void testNotEqual() {
		ItemIdentifier itemID2 = new ItemIdentifier("002");
		boolean expResult = false;
		boolean result = itemID1.equals(itemID2);
		assertEquals(expResult, result, "ItemIdentifer instances with diffrent states are equal");
	}

	@Test
	public void testEqual() {
		ItemIdentifier equalIdentifer = new ItemIdentifier("001");
		boolean expResult = true;
		boolean result = itemID1.equals(equalIdentifer);
		assertEquals(expResult, result, "ItemIdentifers with the statse are note equal");
	}
}
