package se.kth.iv1350.processSale.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
	public void testEquals() {
		double amount1State = 10;
		double amount2State = 10;
		Amount amount1 = new Amount(amount1State);
		Amount amount2 = new Amount(amount2State);
		boolean expResult = true;
		boolean result = amount1.equals(amount2);
		assertEquals(expResult, result, "Amount instances with the same states are not equal");
	}

	@Test
	public void testNotEquals() {
		Amount amountNine = new Amount(9);
		boolean expResult = false;
		boolean result = amountTen.equals(amountNine);
		assertEquals(expResult, result, "Amount instances wiht diffrent states are equal");
	}
	
	@Test
	public void testEqualsNull() {
		Amount nullAmount = null;
		boolean expResult = false;
		boolean result = amountTen.equals(nullAmount);
		assertEquals(expResult, result, "Amount instance equal to null");
	}

	@Test
	public void testNotEqualsJavaLangObject() {
		Object other = new Object();
		boolean expResult = false;
		boolean result = amountTen.equals(other);
		assertEquals(expResult, result, "Amount instance equals pt java.lang.object");
	}

	@Test
	public void testAddPosititvResult() {
		double amount1State = 5;
		double amount2State = 6;
		Amount amount1 = new Amount(amount1State);
		Amount amount2 = new Amount(amount2State);
		Amount expResult = new Amount(amount1State + amount2State);
		Amount result = amount1.add(amount2);
		assertEquals(expResult, result, "Wrong addition result.");
		}
	
	@Test
	public void testAddNegativeResult() {
		double amount1State = 5;
		double amount2State = -6;
		Amount amount1 = new Amount(amount1State);
		Amount amount2 = new Amount(amount2State);
		Amount expResult = new Amount(amount1State + amount2State);
		Amount result = amount1.add(amount2);
		assertEquals(expResult, result, "Worng addition result.");
	}
	
	@Test
	public void testAddZeorResult() {
		double amount1State = 5;
		double amount2State = -5;
		Amount amount1 = new Amount(amount1State);
		Amount amount2 = new Amount(amount2State);
		Amount expResult = new Amount(amount1State + amount2State);
		Amount result = amount1.add(amount2);
		assertEquals(expResult, result, "Worng addition result: should be zero.");
	}
	
	@Test
	public void testSubtractPosititvResult() {
		double amount1State = 5;
		double amount2State = -4;
		Amount amount1 = new Amount(amount1State);
		Amount amount2 = new Amount(amount2State);
		Amount expResult = new Amount(amount1State - amount2State);
		Amount result = amount1.subtract(amount2);
		assertEquals(expResult, result, "Wrong subtraction result.");
		}
	
	@Test
	public void testSubtractNegativeResult() {
		double amount1State = 5;
		double amount2State = 6;
		Amount amount1 = new Amount(amount1State);
		Amount amount2 = new Amount(amount2State);
		Amount expResult = new Amount(amount1State - amount2State);
		Amount result = amount1.subtract(amount2);
		assertEquals(expResult, result, "Worng subtraction result.");
	}
	
	@Test
	public void testSubtractZeorResult() {
		double amount1State = 5;
		double amount2State = 5;
		Amount amount1 = new Amount(amount1State);
		Amount amount2 = new Amount(amount2State);
		Amount expResult = new Amount(amount1State - amount2State);
		Amount result = amount1.subtract(amount2);
		assertEquals(expResult, result, "Worng subtraction result: should be zero.");
	}
	
	@Test
	public void testMultiplyPositiveResult() {
		double multiple = 1.5;
		double amountState = 5;
		Amount product = new Amount(amountState);
		Amount expResult = new Amount(amountState*multiple);
		Amount result = product.multiply(multiple);
		assertEquals(expResult, result, "Wrong multiplication result.");
	}
	
	@Test
	public void testMultiplyNegativeArg() {
		double multiple = -1.5;
		double amountState = 5;
		Amount product = new Amount(amountState);
		Amount expResult = new Amount(amountState*multiple);
		Amount result = product.multiply(multiple);
		assertEquals(expResult, result, "Wrong multiplication result.");
	}
	
	@Test
	public void testMultiplyZeroResult() {
		double multiple = 0;
		double amountState = 5;
		Amount product = new Amount(amountState);
		Amount expResult = new Amount(amountState*multiple);
		Amount result = product.multiply(multiple);
		assertEquals(expResult, result, "Wrong multiplication result.");
	}
	
	@Test
	public void testToString() {
		double amountValue = 3.05;
		Amount amount = new Amount(amountValue);
		String expResult = "3.05";
		String result = amount.toString();
		assertEquals(expResult, result, "invlaid string");
	}
	
	@Test
	public void testToStringZero() {
		double amountValue = 0;
		Amount amount = new Amount(amountValue);
		String expResult = "0.00";
		String result = amount.toString();
		assertEquals(expResult, result, "invlaid string");
	}
	
	@Test
	public void testToStringNegative() {
		double amountValue = -3.05;
		Amount amount = new Amount(amountValue);
		String expResult = "-3.05";
		String result = amount.toString();
		assertEquals(expResult, result, "invlaid string");
	}
	
	@Test
	public void testToStringInfinitDecimals() {
		double amountValue = (1.0/3.0);
		Amount amount = new Amount(amountValue);
		String expResult = "0.33";
		String result = amount.toString();
		assertEquals(expResult, result, "invlaid string");
	}
	
	@Test
	public void testToStringOneDeciaml() {
		double amountValue = 3.1;
		Amount amount = new Amount(amountValue);
		String expResult = "3.10";
		String result = amount.toString();
		assertEquals(expResult, result, "invlaid string");
	}
	
	@Test
	public void testToStringLargeNumber() {
		double amountValue = 5123332.3445435;
		Amount amount = new Amount(amountValue);
		String expResult = "5123332.34";
		String result = amount.toString();
		assertEquals(expResult, result, "invlaid string");
	}
}
