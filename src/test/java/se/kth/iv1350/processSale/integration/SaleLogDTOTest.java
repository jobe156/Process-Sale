package se.kth.iv1350.processSale.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;

class SaleLogDTOTest {
	private SaleLogDTO saleLog1;
	private ItemDTO breadItemDTO;
	private ItemDTO appleItemDTO;
	
	@BeforeEach
	public void startUp() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(200);
		
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.20;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		
		Amount itemPrice2 = new Amount(74);
		ItemIdentifier itemID2 = new ItemIdentifier("002");
		appleItemDTO = new ItemDTO(itemID2, "Apple",itemPrice2, "It´s a fruit!", 0.30);
		
		Sale sale = new Sale();
		sale.addItem(breadItemDTO);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		saleLog1 = new SaleLogDTO(sale, cashPayment);
	}
	
	@AfterEach
	public void tearDown() {
		saleLog1 = null;
		breadItemDTO = null;
		appleItemDTO = null;
	}
	
	@Test
	public void testEqual() {
		CashRegister cashRegister = new CashRegister();
		double paidValue = 200;
		Amount paidAmount= new Amount(paidValue);
		double itemValue = 50;
		Amount itemPrice = new Amount(itemValue);
		String stringIdentifier = "001";
		ItemIdentifier itemID = new ItemIdentifier(stringIdentifier);
		String itemName = "Bread";
		String itemDescription = "It´s whole grain!";
		double itemVat = 0.20;
		ItemDTO itemDTO = new ItemDTO(itemID, itemName,itemPrice, itemDescription, itemVat);
		Sale sale = new Sale();
		sale.addItem(itemDTO);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		boolean expResult = true;
		boolean result = saleLog1.equals(saleLog);
		assertEquals(expResult, result, "equal SaleLogs are not equal");
	}
	
	@Test
	public void testNotEqual() {
		CashRegister cashRegister = new CashRegister();
		double paidValue = 250;
		Amount paidAmount= new Amount(paidValue);
		Sale sale = new Sale();
		sale.addItem(appleItemDTO);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		boolean expResult = false;
		boolean result = saleLog1.equals(saleLog);
		assertEquals(expResult, result, "diffrent sale logs are equal");
	}
	
	@Test
	public void testNotEqualItems() {
		CashRegister cashRegister = new CashRegister();
		double paidValue = 250;
		Amount paidAmount= new Amount(paidValue);
		Sale sale = new Sale();
		sale.addItem(appleItemDTO);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		boolean expResult = false;
		boolean result = saleLog1.equals(saleLog);
		assertEquals(expResult, result, "invlaid items");
	}
	
	@Test
	public void testNotEqualAmountPaid() {
		CashRegister cashRegister = new CashRegister();
		double paidValue = 500;
		Amount paidAmount= new Amount(paidValue);
		Sale sale = new Sale();
		sale.addItem(breadItemDTO);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		boolean expResult = false;
		boolean result = saleLog1.equals(saleLog);
		assertEquals(expResult, result, "invalid amount paid");
	}
}
