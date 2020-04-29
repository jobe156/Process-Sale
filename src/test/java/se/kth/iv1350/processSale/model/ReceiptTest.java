package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.model.Sale;

class ReceiptTest {
	
	@Test
	public void testReciptToString() {
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
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO saleLog = cashPayment.processPayment(sale);
		StringBuilder builder = new StringBuilder();
		builder.append("Store name: \t"+ saleLog.getStoreName() + "\n");
		builder.append("Store adress: \t" + saleLog.getStoreAddress() + "\n");
		builder.append("Time of sale: \t" + saleLog.getTimeOfSale().toString() + "\n");
		builder.append("Bought items: \t" + "\n\n" );
		for(Item item: saleLog.getItems()) 
			builder.append(item.toString() + "\n");
		builder.append("\nTotalVatAmount:\t" + String.valueOf(saleLog.getTotalVatAmount()) + "\n");
		builder.append("Total price: \t" + saleLog.getTotalPrice().toString() + "\n");
		builder.append("Paid amount: \t" + saleLog.getPaidAmount().toString() + "\n");
		builder.append("Change: \t" + saleLog.getChange().toString() + "\n");
		String expResult = builder.toString();
		Receipt receipt = new Receipt(saleLog);
		String result = receipt.toString();
		assertEquals(expResult, result, "wring receipt");
	}
}