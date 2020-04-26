package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.Sale;

class ReceiptTest {
	
	@Test
	public void testReciptToString() {
		Sale sale = new Sale();
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO breadItemDTO = new ItemDTO(itemID, "Bread", itemPrice, "It´s whole grain!", 0.1);
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		StringBuilder builder = new StringBuilder();
		builder.append("Store name: "+ saleLog.getStoreName());
		builder.append("Store adress: " + saleLog.getStoreAdress() + "/n");
		builder.append("Time of sale: " + saleLog.getTimeOfSale().toString() + "/n");
		builder.append("Bought items: " + "/n" );
		for(Item item: saleLog.getItems()) 
			builder.append(item.toString() + "/n");
		builder.append("/nTotalVatRate: " + String.valueOf(saleLog.getTotalVatRate()) + "/n");
		builder.append("Total price: " + saleLog.getTotalPrice().toString() + "/n");
		builder.append("Paid amount: " + saleLog.getPaidAmount().toString() + "/n");
		builder.append("Total price: " + saleLog.getChange().toString() + "/n");
		String expResult = builder.toString();
		Receipt receipt = new Receipt(saleLog);
		String result = receipt.toString();
		assertEquals(expResult, result, "wring receipt");
	}
}