package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
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
	public ItemRegistrationDTO generateItemRegistrationDTO(Sale currentSale, ItemDTO itemDTO){
		if(itemDTO != null) {
			for(Item item : currentSale.getItems())							//
				if(itemDTO.getItemName().equals(item.getItemName())) {		// The items isn´t added if the qunatity is zeor
					if(item.getQuantity() <= 0)								//consider adding seperet method
						return null;										//for this.
					else {													//
						break;												//
					}
				}
			Amount totalPrice = currentSale.CalculateFinalPrice();		
			ItemRegistrationDTO itmRegDto = new ItemRegistrationDTO(itemDTO, totalPrice);
			return itmRegDto;
		}
		return null;
	}
	
	/**
	 *	Is used to generate information about the <code>sale</code> regarding the transaction.
	 *
	 * @param sale		Provides the infromation for the <code>DisplayTransactionDTO</code>.
	 * @return			shows the total price after discount and vatrate has been applied.
	 */
	
	/*
	public DisplayTransactionDTO generateDisplayTransactionDTO (Sale sale) {
		DisplayTransactionDTO disTraDto = new DisplayTransactionDTO(sale);
		return disTraDto;
	}
	*/
}
