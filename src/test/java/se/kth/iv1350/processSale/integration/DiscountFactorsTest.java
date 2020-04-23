package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.DiscountFactors;
import se.kth.iv1350.processSale.util.Amount;

import static org.junit.jupiter.api.Assertions.*;

class DiscountFactorsTest {
	private DiscountFactors discountFactors;

	@BeforeEach
	public void startUp() {
		Amount totalAmount = new Amount(100);
		discountFactors = new DiscountFactors(4, totalAmount, "Bread");
	}

	@AfterEach
	public void tearDown() {
		discountFactors = null;
	}

	@Test
	public void testEqual() {
		DiscountFactors equalDiscountFactor = new DiscountFactors(discountFactors);
		boolean expResult = true;
		boolean result = discountFactors.equal(equalDiscountFactor);
		assertEquals(expResult, result, "equal discount factors an unequal");
	}

	@Test
	public void testEqualDiffrentArg() {
		Amount totalAmount = new Amount(150);
		DiscountFactors falseDiscountFactors = new DiscountFactors(5, totalAmount, "Tomato");
		boolean expResult = false;
		boolean result = falseDiscountFactors.equal(discountFactors);
		assertEquals(expResult, result, "diffrent discountfactors are equal");
	}

	@Test
	public void testEqualNullArg() {
		DiscountFactors nullDiscountFactors = null;
		boolean expResult = false;
		boolean result = discountFactors.equals(nullDiscountFactors);
		assertEquals(expResult, result, " discount factors are equal to null");
	}

}
