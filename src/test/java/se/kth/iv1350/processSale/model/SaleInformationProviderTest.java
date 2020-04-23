package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.ItemIdentifier;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;

class SaleInformationProviderTest {
	private Printer printer;
	private SaleInformationProvider SIProvider;
	private Sale sale;
	private ItemDTO breadItemDTO;

	@BeforeEach
	public void startUp() {
		printer = new Printer();
		SIProvider = new SaleInformationProvider(printer);
		sale = new Sale();
		Amount itemPrice = new Amount(50);
		ItemIdentifier itemID = new ItemIdentifier("001");
		breadItemDTO = new ItemDTO(itemID, "Bread", itemPrice, "It´s whole grain!", 0.1);
	}

	@AfterEach
	public void tearDown() {
		printer = null;
		SIProvider = null;
		sale = null;
		breadItemDTO = null;
	}

	@Test
	public void testGenerateItemRegistrationDTO() {
		sale.addItem(breadItemDTO, 3);
		Amount totalAmount = sale.CalculateFinalPrice();
		ItemRegistrationDTO expItmRegDto = new ItemRegistrationDTO(breadItemDTO, totalAmount);
		ItemRegistrationDTO resultItmRegDto = SIProvider.generateItemRegistrationDTO(sale, breadItemDTO);
		boolean expResult = true;
		boolean result = false;
		if (expItmRegDto.getItemName().equals(resultItmRegDto.getItemName())
				&& expItmRegDto.getItemPrice().equals(resultItmRegDto.getItemPrice())
				&& expItmRegDto.getItemDescription().equals(resultItmRegDto.getItemDescription())
				&& expItmRegDto.getRunningTotal().equals(resultItmRegDto.getRunningTotal()))
			result = true;
		assertEquals(expResult, result, "instances with equal states are not equal");
	}

	@Test
	public void testGenerateItemRegistrationDTONullItemDTO() {
		ItemDTO nullItemDTO = null;
		sale.addItem(nullItemDTO, 1);
		ItemRegistrationDTO resultItmRegDto = SIProvider.generateItemRegistrationDTO(sale, nullItemDTO);
		assertNull(resultItmRegDto, "null item DTO resulted in an non null DTO");
	}

	@Test
	public void testGenerateDisplayTransactionDTO() {

	}

	@Test
	public void testGenerateDisplayTransactionDTONullArg() {

	}

}
