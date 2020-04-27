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
	
	public SaleInformationProvider(Printer printer) {
		this.printer = printer;
	}
	
	/**
	 * Is used to generate information about an <code>item</code> that has been registered in a <code>sale</code>.
	 * 
	 * @param currentSale	Is the <code>sale</code> that is currently being performed.
	 * @return				Information about an <code>item</code> and the <code>sale</code> as a whole.
	 * 						throws exception if the  <code>itemDTO</code> argument is null and 
	 * 						null is returned.
	 */
	public ItemRegistrationDTO generateItemRegistrationDTO(Sale currentSale, ItemDTO itemDTO){
		//if(itemDTO != null) {
			Amount totalPrice = currentSale.CalculateFinalPrice();		
			ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(itemDTO, totalPrice);
			return itmRegDto;
		//}
		//return null;
	}
	
	/**
	 * Is used to generate information about the the transaction of a sale. 
	 * 
	 * @param saleLog	Provides information about the <code>cashPayment</code>
	 * 					and <code>sale</code>.
	 * @return			The generated displayTransactionDTO. if the <code>saleLog</code> argument 
	 * 					is null, a  exception is thrown and null is returned.  
	 */
	public TransactionResultDTO generateTransactionResultDTO (SaleLogDTO saleLog) {
		//if(saleLog == null)
			//return null;
		TransactionResultDTO traResDto = new TransactionResultDTO(saleLog);
		return traResDto;
	}
	
	/**
	 * Generates a receipt and prints it. if the <code>SaleLogDTO</code> is null, a exception is thrown
	 * and null is returned.
	 * @param saleLog	provides information for the receipt.
	 */
	public void printReceipt(SaleLogDTO saleLog) {
		Receipt receipt = new Receipt(saleLog);
		printer.printReceipt(receipt);
	}	
}
