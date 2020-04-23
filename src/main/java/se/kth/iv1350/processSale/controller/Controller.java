package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.InventorySystemHandler;
import se.kth.iv1350.processSale.integration.AccountingSystemHandler;
import se.kth.iv1350.processSale.integration.DiscountRepositoryHandler;
import se.kth.iv1350.processSale.integration.RegistryCreator;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.SaleInformationProvider;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.DisplayTransactionDTO;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.util.ItemIdentifier;

/**
 * 
 *This Controller class is responsible for passing calls to the model and returning
 *values to the View.
 */
public class Controller {
	private InventorySystemHandler ISHandler;
	private AccountingSystemHandler ASHandler;
	//private DiscountRepositoryHandler DRHandler;
	private CashRegister cashRegister;
	private Sale currentSale;
	private SaleInformationProvider SIProvider;

	/**
	 * 
	 *Creates an new instance of controller
	 * 
	 *@param	registryCreator Used for getting classes form the integration layer.
	 *@param	SIProvider Used to provide information about the <code>Sale</code>.
	 */
	public Controller( SaleInformationProvider SIProvider){
		RegistryCreator registryCreator = new RegistryCreator();
		ISHandler = registryCreator.getInventorySystemHandler();
		ASHandler = registryCreator.getAccountingSystemHandler();
		//DRHandler = registryCreator.getDiscountRepositoryHandler();
		cashRegister = new CashRegister();
		this.SIProvider = SIProvider;
	}
	
	/**
	 * Creates a new instance of a sale
	 */
	public void startSale() {
		this.currentSale = new Sale();
	}
	
	//problem with sale not being started and items beeing added.
	
	/**
	 * Is used to add items to the current <code>sale</code>.
	 * 
	 * @param itemID	used to find corresponding itemDTO.
	 * @return			Information about the registered <code>Item</code> and <code>sale</code>.
	 * 					returns null if the identifier is invalid.
	 */
	public ItemRegistrationDTO registerItem(ItemIdentifier itemID, int quantity) {
		ItemDTO itemDTO = ISHandler.findItem(itemID);
		currentSale.addItem(itemDTO, quantity);
		ItemRegistrationDTO itmRegDTO = SIProvider.generateItemRegistrationDTO(currentSale, itemDTO);
		return itmRegDTO;
	}
	
	/**
	 * Is used to add discounts to a <code>Sale</code>.
	 * 
	 * @param customerID	Is required to apply disocunts to a sale.
	 * @return				Information about the current totalPrice after discounts have been applied.
	 */
	
	
	/*
	
	public DisplayTransactionDTO DiscountRequest(CustomerIdentificationDTO customerID) {
		currentSale.LookForDiscounts(DRHandler, customerID);
		
		DisplayTransactionDTO dispTraDTO = new DisplayTransactionDTO(currentSale);
		return dispTraDTO;
	}
	
	public DiscountRepositoryHandler getDiscountRepositoryHandler () {
		return DRHandler;
	}
	
	*/
	
	
	
}
