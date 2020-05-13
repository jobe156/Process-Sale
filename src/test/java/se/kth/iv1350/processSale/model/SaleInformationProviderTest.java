package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

class SaleInformationProviderTest {
	private SaleInformationProvider SIProvider;
	private Sale sale;
	private ItemDTO breadItemDTO;

	@BeforeEach
	public void startUp() {
		SIProvider = SaleInformationProvider.getSaleInformationProvider();
		sale = new Sale();
		
		String breadStringIdentifier = "001";
		double breadItemValue = 50;
		String breadItemName = "Bread";
		String breadItemDescription = "It´s whole grain!";
		double breadItemVat = 0.1;
		Amount breadItemPrice = new Amount(breadItemValue);
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		breadItemDTO = new ItemDTO(breadItemID, breadItemName, breadItemPrice, breadItemDescription, breadItemVat);
	}

	@AfterEach
	public void tearDown() {
		SIProvider = null;
		sale = null;
		breadItemDTO = null;
	}

	@Test
	public void testGenerateItemRegistrationDTO() {
		sale.addItem(breadItemDTO);
		Amount totalAmount = sale.CalculateFinalPrice();
		ItemRegistrationDTO expResult = new ItemRegistrationDTO(breadItemDTO, totalAmount);
		ItemRegistrationDTO result = SIProvider.generateItemRegistrationDTO(sale, breadItemDTO);
		assertEquals(expResult.getItemName(), result.getItemName(), "Wrong item name.");
		assertEquals(expResult.getItemDescription(), result.getItemDescription(), "Wrong item description.");
		assertEquals(expResult.getItemPrice(), result.getItemPrice(), "Wrong item price.");
		assertEquals(expResult.getRunningTotal(), result.getRunningTotal(), "Wrong item running total.");
	}

	@Disabled
	@Test
	public void testGenerateItemRegistrationDTONullItemDTO() {
		ItemDTO nullItemDTO = null;
		sale.addItem(nullItemDTO);
		ItemRegistrationDTO resultItmRegDto = SIProvider.generateItemRegistrationDTO(sale, nullItemDTO);
		assertNull(resultItmRegDto, "ItemDTO is null");
	}
	
	@Test
	public void  testGenerateTransactionResultDTOSalePar() {
		TransactionResultDTO expResult = new TransactionResultDTO(sale);
		TransactionResultDTO result = SIProvider.generateTransactionResultDTO(sale);
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
	}
	
	@Disabled
	@Test
	public void testGenerateTransactionResultDTOSaleParNullArg() {
		Sale nullSale = null;
		TransactionResultDTO result = SIProvider.generateTransactionResultDTO(nullSale);
		assertNull(result, "null argument generated a sale Log");
	}

	@Test
	public void testGenerateTransactionResultDTOSaleLogPar() {
		sale.addItem(breadItemDTO);
		CashRegister cashRegister = new CashRegister();
		Amount amountPaid = new Amount(200);
		CashPayment cashPayment= new CashPayment(amountPaid, cashRegister, sale);
		SaleLogDTO sLog = cashPayment.processPayment(sale);
		TransactionResultDTO expResult = new TransactionResultDTO(sLog);
		TransactionResultDTO result = SIProvider.generateTransactionResultDTO(sLog);
		assertEquals(expResult.getTotalPrice(), result.getTotalPrice(), "invalid total price");
		assertEquals(expResult.getAmountPaid(), result.getAmountPaid(), "invalid amount paid");
		assertEquals(expResult.getChange(), result.getChange(), "invalid change");
	}

	@Disabled
	@Test
	public void testGenerateTransactionResultDTOSaleLogParNullArg() {
		SaleLogDTO nullSaleLog = null;
		TransactionResultDTO result = SIProvider.generateTransactionResultDTO(nullSaleLog);
		assertNull(result, "null argument generated a sale Log");
	}
	

}
