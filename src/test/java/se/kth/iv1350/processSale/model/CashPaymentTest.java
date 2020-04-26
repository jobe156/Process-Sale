package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

class CashPaymentTest {
	private Sale sale;
	
	@BeforeEach
	public void startUp() {
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0.20);
		sale = new Sale();
		sale.addItem(itemDTO);
	}
	
	@AfterEach
	public void tearDown() {
		sale = null;
	}
	
	@Test
	public void testProcessPaymentChange() {
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		Amount itemPrice = new Amount(50);
		double vatRate = 0.20;
		Amount expResult = amountPaid.subtract(itemPrice.multiply(1 + vatRate));;
		Amount result = cashPayment.getChange();
		assertEquals(expResult, result, "invalid change");
	}
	
	@Test
	public void testProcessPayment() {
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO expResult = new SaleLogDTO(sale, cashPayment);
		SaleLogDTO result = cashPayment.processPayment(sale);
		assertEquals(expResult, result, "Payment was incorrectly processed");
		assertTrue(sale.CalculateFinalPrice().equals(cashRegister.getAmountInRegister()), 
				"wrong amount in register");
	}
}
