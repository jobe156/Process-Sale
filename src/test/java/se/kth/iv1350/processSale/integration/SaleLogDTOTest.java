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
	private ItemDTO itemDTO1;
	private ItemDTO itemDTO2;
	
	@BeforeEach
	public void startUp() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(200);
		
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		itemDTO1 = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0.20);
		
		Amount itemPrice2 = new Amount(74);
		ItemIdentifier itemID2 = new ItemIdentifier("002");
		itemDTO2 = new ItemDTO(itemID2, "Apple",itemPrice2, "It´s a fruit!", 0.30);
		
		Sale sale = new Sale();
		sale.addItem(itemDTO1);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		saleLog1 = new SaleLogDTO(sale, cashPayment);
	}
	
	@AfterEach
	public void tearDown() {
		saleLog1 = null;
		itemDTO1 = null;
		itemDTO2 = null;
		
	}

	@Test
	public void testEqual() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(200);
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0.20);
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
		Amount paidAmount= new Amount(250);
		Sale sale = new Sale();
		sale.addItem(itemDTO2);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		boolean expResult = false;
		boolean result = saleLog1.equals(saleLog);
		assertEquals(expResult, result, "diffrent sale logs are equal");
	}
	
	@Test
	public void testNotEqualItems() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(200);
		Sale sale = new Sale();
		sale.addItem(itemDTO2);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		boolean expResult = false;
		boolean result = saleLog1.equals(saleLog);
		assertEquals(expResult, result, "invlaid items");
	}
	
	@Test
	public void testNotEqualAmountPaid() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(500);
		Sale sale = new Sale();
		sale.addItem(itemDTO1);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		boolean expResult = false;
		boolean result = saleLog1.equals(saleLog);
		assertEquals(expResult, result, "invalid amount paid");
	}
	
	@Test
	public void testSaleLogDTOCustructorVatRate() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(200);
		Sale sale = new Sale();
		sale.addItem(itemDTO1);
		sale.addItem(itemDTO2);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		double vatRate1 = itemDTO1.getVatRate();
		double vatRate2 = itemDTO2.getVatRate();
		double totalVatRate = vatRate1 + vatRate2;
		double expResult = totalVatRate;
		double result =  saleLog.getTotalVatRate();
		assertEquals(expResult, result, "incorect VAT rate"); 
	}
}
