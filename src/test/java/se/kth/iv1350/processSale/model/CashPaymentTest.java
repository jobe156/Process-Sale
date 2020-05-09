package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

class CashPaymentTest {
	private Sale sale;
	
	@BeforeEach
	public void startUp() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.20;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		sale = new Sale();
		sale.addItem(breadItemDTO);
	}
	
	@AfterEach
	public void tearDown() {
		sale = null;
	}
	
	@Disabled
	@Test
	public void testCashPayemntnullArg() {
		CashRegister cashRegister = new CashRegister(); 
		Amount nullAmountPaid = null;
		Sale nullSale = null;
		CashPayment cashPayment= new CashPayment(nullAmountPaid, cashRegister, nullSale);
		Amount expResult = new Amount(-1);
		Amount result = cashPayment.getChange();
		assertEquals(expResult, result, "Null argument givs invalid cahnge");
	}
	
	@Test
	public void testProcessPaymentChange() {
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		Amount itemPrice = new Amount(50);
		double vatRate = 0.20;
		Amount expResult = amountPaid.subtract(itemPrice.add(itemPrice.multiply(vatRate)));
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
