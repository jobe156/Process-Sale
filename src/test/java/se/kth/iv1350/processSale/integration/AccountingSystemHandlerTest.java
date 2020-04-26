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
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO breadItemDTO = new ItemDTO(itemID, "Bread", itemPrice, "It´s whole grain!", 0.1);
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		AccountingSystemHandler ASHandler = new AccountingSystemHandler();
		ASHandler.addSaleLog(saleLog);
		SaleLogDTO expResult = new SaleLogDTO(sale, cashPayment);
		SaleLogDTO result = ASHandler.findSalLogByBoughtItems(sale.getItems());
		assertEquals(expResult, result, "invalid sale logs");
		
	}
}
