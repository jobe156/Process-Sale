package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.Receipt;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;



/**
 * 
 * Used to provide information about the a sale.
 *
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
	 * @return				Information about an an <code>item</code> and the <code>sale</code> as a whole.
	 * 						If the ItemDTO is null or the qunatity of the added item is zero, null will 
	 * 						be returned.
	 */
	
	//has null exception that returns nul if itemDTO is null
	public ItemRegistrationDTO generateItemRegistrationDTO(Sale currentSale, ItemDTO itemDTO){
		//if(itemDTO != null) {
			Amount totalPrice = currentSale.CalculateFinalPrice();		
			ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(itemDTO, totalPrice);
			return itmRegDto;
		//}
		//return null;
	}
	
	//has null exception, if saleLog == null, null is returned.
	public DisplayTransactionDTO generateDisplayTransactionDTO (SaleLogDTO saleLog) {
		//if(saleLog == null)
			//return null;
		DisplayTransactionDTO disTraDto = new DisplayTransactionDTO(saleLog);
		return disTraDto;
	}
	
	public void printReceipt(SaleLogDTO saleLog) {
		Receipt receipt = new Receipt(saleLog);
		printer.printReceipt(receipt);
	}	
}
