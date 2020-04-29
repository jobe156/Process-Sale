package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.util.Amount;

class TransactionResultDTOTest {

	@Test
	public void testToString() {
		CashRegister cashRegister = new CashRegister();
		Amount paidAmount= new Amount(200);
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		Sale sale = new Sale();
		sale.addItem(breadItemDTO);
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, sale);
		SaleLogDTO saleLog = new SaleLogDTO(sale, cashPayment);
		TransactionResultDTO disTraDto = new TransactionResultDTO(saleLog);
		StringBuilder builder = new StringBuilder();
		builder.append("Total price: \t" + sale.CalculateFinalPrice().toString() + "\n");
		builder.append("Amount paid: \t" + paidAmount.toString() + "\n");
		builder.append("Change: \t" + cashPayment.getChange().toString() + "\n");
		String expResult = builder.toString();
		String result = disTraDto.toString();
		assertEquals(expResult, result, "String was not correctly made");
	}
	
	@Test
	public void testToStringOnlyTotalAmount() {
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemDTO breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
		Sale sale = new Sale();
		sale.addItem(breadItemDTO);
		TransactionResultDTO disTraDto = new TransactionResultDTO(sale);
		StringBuilder builder = new StringBuilder();
		builder.append("Total price: \t" + sale.CalculateFinalPrice().toString() + "\n");
		String expResult = builder.toString();
		String result = disTraDto.toString();
		assertEquals(expResult, result, "String was not correctly made");
	}
}