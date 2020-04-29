package se.kth.iv1350.processSale.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

class AccountingSystemHandlerTest {

	@Test
	public void testAddSaleLog() {
		Sale sale = new Sale();
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		double paidValue = 200;
		Amount amountPaid = new Amount(paidValue);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		AccountingSystemHandler ASHandler = new AccountingSystemHandler();
		ASHandler.addSaleLog(saleLog);
		SaleLogDTO expResult = new SaleLogDTO(sale, cashPayment);
		SaleLogDTO result = ASHandler.findSalLogByBoughtItems(sale.getItems());
		assertEquals(expResult, result, "invalid sale logs");
	}
	
}
