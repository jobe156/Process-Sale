package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.util.Amount;

class DisplayTransactionDTOTest {

	@Test
	public void testToString() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(200);
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		ItemDTO itemDTO = new ItemDTO(itemID, "Bread",itemPrice, "It´s whole grain!", 0.20);
		Sale sale = new Sale();
		sale.addItem(itemDTO);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		DisplayTransactionDTO disTraDto = new DisplayTransactionDTO(saleLog);
		StringBuilder builder = new StringBuilder();
		builder.append("Total price: \t" + sale.CalculateFinalPrice().toString() + "\n");
		builder.append("Amount paid: \t" + paidAmount.toString() + "\n");
		builder.append("Change: \t" + cashPayment.getChange().toString() + "\n");
		String expResult = builder.toString();
		String result = disTraDto.toString();
		assertEquals(expResult, result, "String was not correctly made");
	}
}
