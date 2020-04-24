package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.AccountingSystemHandler;
import se.kth.iv1350.processSale.integration.InventorySystemHandler;
import se.kth.iv1350.processSale.integration.ItemDTO;

import se.kth.iv1350.processSale.integration.RegistryCreator;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.DisplayTransactionDTO;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.SaleInformationProvider;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.ItemIdentifier;



/**
 * 
 *This Controller class is responsible for passing calls to the model and returning
 *values to the View.
 */
public class Controller {
	private InventorySystemHandler ISHandler;
	private AccountingSystemHandler ASHandler;
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
	
	public DisplayTransactionDTO processCashPayment(Amount paidAmount) {
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister);
		SaleLogDTO sLog = cashPayment.ProcessPayment(currentSale);
		return null;
	}
}
