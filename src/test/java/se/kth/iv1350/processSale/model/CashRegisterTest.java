package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.util.Amount;

class CashRegisterTest {

	@Test
	public void testAddPayment() {
		CashRegister cashRegister = new CashRegister(); 
		Amount payment = new Amount(120);
		Amount expResult = new Amount(payment);
		cashRegister.addPayment(payment);
		Amount result = new Amount(cashRegister.getAmountInRegister());
		assertEquals(expResult, result, "incorrect value in register");	
	}
	
	@Test
	public void testAddPaymentNeagativArg() {
		CashRegister cashRegister = new CashRegister(); 
		Amount payment = new Amount(-120);
		Amount expResult = new Amount(payment);
		cashRegister.addPayment(payment);
		Amount result = new Amount(cashRegister.getAmountInRegister());
		assertEquals(expResult, result, "incorrect value in register");	
	}
	
	@Test
	public void testAddPaymentZeroArg() {
		CashRegister cashRegister = new CashRegister(); 
		Amount payment = new Amount();
		Amount expResult = new Amount(payment);
		cashRegister.addPayment(payment);
		Amount result = new Amount(cashRegister.getAmountInRegister());
		assertEquals(expResult, result, "incorrect value in register");	
	}
	
	@Disabled
	@Test
	public void testAddPaymentNullArg() {
		CashRegister cashRegister = new CashRegister(); 
		Amount nullPayment = null;
		cashRegister.addPayment(nullPayment);
		Amount expResult = new Amount(0);
		Amount result = cashRegister.getAmountInRegister();
		assertEquals(expResult, result, "adding null to the cash register increased the amount in it");
	}
}