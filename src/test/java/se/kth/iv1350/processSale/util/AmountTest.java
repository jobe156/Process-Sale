package se.kth.iv1350.processSale.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
	private Amount amountTen;

	@BeforeEach
	public void startUp() {
		amountTen = new Amount(10);
	}

	@AfterEach
	public void tearDown() {
		amountTen = null;
	}

	@Test
	public void testEqualNullArg() {
		Amount nullAmount = null;
		boolean expResult = false;
		boolean result = amountTen.equal(nullAmount);
		assertEquals(expResult, result, "Amount instance equal to null");
	}

	@Test
	public void testNotEqual() {
		Amount amountNine = new Amount(9);
		boolean expResult = false;
		boolean result = amountTen.equal(amountNine);
		assertEquals(expResult, result, "Amount instances wiht diffrent states are equal");
	}

	@Test
	public void testEqual() {
		Amount amount = new Amount(10);
		boolean expResult = true;
		boolean result = amountTen.equal(amount);
		assertEquals(expResult, result, "Amount instances wiht the same states are not equal");
	}

	@Test
	public void testAddnull() {
		Amount nullAmount = null;
		Amount addAmount = new Amount(10);
		addAmount.add(nullAmount);
		boolean expResult = true;
		boolean result = amountTen.equal(addAmount);
		assertEquals(expResult, result, "Amount instance is diffrent when a null instance is added");
	}

	@Test
	public void testAddNegativeArgZeroResult() {
		Amount negativAmount = new Amount(-11);
		amountTen.add(negativAmount);
		Amount zeroAmount = new Amount();
		boolean expResult = true;
		boolean result = zeroAmount.equal(amountTen);
		assertEquals(expResult, result, "an larger negativ Amount instance added to an amount is negative");
	}

	@Test
	public void testAddNegativeArg() {
		Amount negativeAmount = new Amount(-5);
		amountTen.add(negativeAmount);
		Amount expAmount = new Amount(5);
		boolean expResult = true;
		boolean result = amountTen.equal(expAmount);
		assertEquals(expResult, result, "Amount with added negative amount is not equal to the amount after addition");
	}

	@Test
	public void testAddPositiveArg() {
		Amount positivAmount = new Amount(5);
		amountTen.add(positivAmount);
		Amount expAmount = new Amount(15);
		boolean expResult = true;
		boolean result = amountTen.equal(expAmount);
		assertEquals(expResult, result, "amount with added positiv amount si not equal to the amount after addition");
	}

	@Test
	public void testMultiplyNegativeArg() {
		double multiple = -2;
		Amount multAmount = new Amount(amountTen);
		multAmount.multiply(multiple);
		boolean expResult = true;
		boolean result = amountTen.equal(multAmount);
		assertEquals(expResult, result,
				"A negative multiple is not ignored, amount and amount times a negative multiple is not equal");
	}

	@Test
	public void testMultiply() {
		double multiple = 1.5;
		Amount multAmount = new Amount(amountTen);
		multAmount.multiply(multiple);
		Amount expAmount = new Amount(15);
		boolean expResult = true;
		boolean result = expAmount.equal(multAmount);
		assertEquals(expResult, result, "amounts with equals states are not equal");

	}

	@Test
	public void testCompareMoreThan() {
		Amount amountEleven = new Amount(11);
		int expInt = 1;
		int resultInt = amountEleven.compare(amountTen);
		boolean expResult = true;
		boolean result = (expInt == resultInt);
		assertEquals(expResult, result, "amount with larger state is less or equal to a amount" + "lesser amount");
	}

	@Test
	public void testCompareEqual() {
		Amount amountEqual = new Amount(10);
		int expInt = 0;
		int resultInt = amountEqual.compare(amountTen);
		boolean expResult = true;
		boolean result = (expInt == resultInt);
		assertEquals(expResult, result, "amounts with equal states are note equal");
	}

	@Test
	public void testCompareLessThan() {
		Amount amountEleven = new Amount(11);
		int expInt = -1;
		int resultInt = amountTen.compare(amountEleven);
		boolean expResult = true;
		boolean result = (expInt == resultInt);
		assertEquals(expResult, result,
				"amount with lesser state is equal or larger to a" + "amount with a larger state");
	}

}
