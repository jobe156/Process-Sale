package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.DiscountFactors;
import se.kth.iv1350.processSale.util.Amount;

class DiscountRepositoryHandlerTest {
	private DiscountRepositoryHandler DRHandler;
	private DiscountFactors validDiscountFactors;

	@BeforeEach
	public void startUP() {
		DRHandler = new DiscountRepositoryHandler();
		Amount totalAmount = new Amount(120);
		String itemName = "Bread";
		validDiscountFactors = new DiscountFactors(4, totalAmount, itemName);
	}

	@AfterEach
	public void tearDown() {
		DRHandler = null;
		validDiscountFactors = null;
	}

	@Test
	public void testFindDiscountInvalidDiscountFActors() {
		Amount totalAmount = new Amount(10);
		String itemName = "Tomato";
		DiscountFactors invalidDiscountFActors = new DiscountFactors(2, totalAmount, itemName);
		Discount resultDiscount = DRHandler.findDiscount(invalidDiscountFActors);
		assertNull(resultDiscount, "invadlid discountFactors returns valid discount");
	}

	@Test
	public void testFindDiscount() {
		Discount resultDiscount = DRHandler.findDiscount(validDiscountFactors);
		assertNotNull(resultDiscount, "vadlid discountFactors returns null");
	}

}
