package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.Receipt;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

/**
 * Used to provide information about the a sale.
 */
public class SaleInformationProvider {
	private Printer printer;
	
	private static final SaleInformationProvider SIProvider = new SaleInformationProvider();
	
	private SaleInformationProvider() {
		this.printer = Printer.getPrinter();
	}
	
	public static SaleInformationProvider getSaleInformationProvider() {
		return SIProvider;
	}
	
	/**
	 * Is used to generate information about an <code>item</code> that has been registered in a <code>sale</code>.
	 * 
	 * @param currentSale	Is the <code>sale</code> that is currently being performed.
	 * @return				Information about an <code>item</code> and the <code>sale</code> as a whole.
	 * 					
	 */
	public ItemRegistrationDTO generateItemRegistrationDTO(Sale currentSale, ItemDTO itemDTO){
		Amount totalPrice = currentSale.CalculateFinalPrice();		
		ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(itemDTO, totalPrice);
		return itmRegDto;
	}
	
	/**
	 * Is used to Only show the final price of the current <code>sale</code>.
	 * 
	 * @param currentSale	the current <sale>.
	 * @return				Information about the total cost of the <code>sale</code>.
	 */
	public TransactionResultDTO generateTransactionResultDTO (Sale currentSale) {
		
		return new TransactionResultDTO (currentSale);
	}
	
	/**
	 * Is used to generate information about the the transaction of a sale. 
	 * 
	 * @param saleLog	Provides information about the <code>cashPayment</code>
	 * 					and <code>sale</code>.
	 * @return			The generated displayTransactionDTO. 
	 */
	public TransactionResultDTO generateTransactionResultDTO (SaleLogDTO saleLog) {
	
		TransactionResultDTO traResDto = new TransactionResultDTO(saleLog);
		return traResDto;
	}
	
	/**
	 * Generates a receipt and prints it.
	 * @param saleLog	provides information for the receipt.
	 */
	public void printReceipt(SaleLogDTO saleLog) {

		Receipt receipt = new Receipt(saleLog);
		printer.printReceipt(receipt);
	}	
}
