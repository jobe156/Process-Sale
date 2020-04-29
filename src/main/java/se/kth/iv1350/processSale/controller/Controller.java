package se.kth.iv1350.processSale.controller;
import se.kth.iv1350.processSale.integration.AccountingSystemHandler;
import se.kth.iv1350.processSale.integration.InventorySystemHandler;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.integration.RegistryCreator;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.TransactionResultDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.SaleInformationProvider;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.util.Amount;

/**
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
	 *Creates a new instance of a controller.
	 * 
	 *@param SIProvider Provides information about the <code>Sale</code> that´s returned to the <code>View</code>.
	 */
	public Controller( SaleInformationProvider SIProvider){
		RegistryCreator registryCreator = new RegistryCreator();
		ISHandler = registryCreator.getInventorySystemHandler();
		ASHandler = registryCreator.getAccountingSystemHandler();
		cashRegister = new CashRegister();
		this.SIProvider = SIProvider;
	}
	
	/**
	 * Starts a new <code>Sale</code>.
	 */
	public void startSale() {
		this.currentSale = new Sale();
	}
	
	/**
	 * Add new items to the current <code>Sale</code> and returns a <code>ItemRegistraionDTO</code> containing
	 * Information about the state of the current sale. Null will be returned if the <code>ItemIdentifier</code> 
	 * argument is invalid or null.
	 * 
	 * @param itemID	used to find the corresponding itemDTO.
	 * @return			Information about the registered <code>Item</code> and <code>sale</code>. 					
	 */
	public ItemRegistrationDTO registerItem(ItemIdentifier itemID) {
		ItemDTO itemDTO = ISHandler.findItem(itemID);
		currentSale.addItem(itemDTO);
		ItemRegistrationDTO itmRegDTO = SIProvider.generateItemRegistrationDTO(currentSale, itemDTO);
		return itmRegDTO;
	}
	
	/**
	 * Show to total price that is going to be paid.
	 * @return	The total price that is going to be paid.
	 */
	public TransactionResultDTO endSale() {
		return SIProvider.generateTransactionResultDTO(currentSale);
	}
	
	
	/**
	 * Process the <code>CashPayment</code> and finalize the sale by sending a <code>SaleLogDTO</code> to the 
	 * <code>AccountingSystemHandler</code> and returns a <code>DisplayTransactionDTO</code> which contains information 
	 * about the transaction. A <code>Receipt</code> containing information about the <code>Sale</code> and 
	 * <code>CashPayment</code> is printed out.
	 * 
	 * @param paidAmount	The <code>Amount</code> paid. 
	 * @return				Information about the <code>Sale</code> and <code>CashPayment</code>.
	 */
	public TransactionResultDTO processCashPayment(Amount paidAmount) {
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, currentSale);
		SaleLogDTO saleLog = cashPayment.processPayment(currentSale);
		ASHandler.addSaleLog(saleLog);
		SIProvider.printReceipt(saleLog);
		TransactionResultDTO traResDto = SIProvider.generateTransactionResultDTO(saleLog);
		return traResDto;
	}
}
