package se.kth.iv1350.processSale.controller;
import se.kth.iv1350.processSale.integration.AccountingSystemHandler;
import se.kth.iv1350.processSale.integration.InventorySystemHandler;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.integration.RegistryCreator;
import se.kth.iv1350.processSale.integration.InvalidItemIdentifierException;
import se.kth.iv1350.processSale.integration.InventorySystemNotRespondingException;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.TransactionResultDTO;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.ItemRegistrationDTO;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.SaleInformationProvider;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.model.RevenueObserver;
import se.kth.iv1350.processSale.util.Amount;

import java.util.ArrayList;
import java.util.List;


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
	
	private List<RevenueObserver> revenueObservers = new ArrayList<>();


	/**
	 *Creates a new instance of a controller.
	 * 
	 *@param SIProvider Provides information about the <code>Sale</code> that´s returned to the <code>View</code>.
	 */
	public Controller( SaleInformationProvider SIProvider) {
		RegistryCreator registryCreator = new RegistryCreator();
		ISHandler = registryCreator.getInventorySystemHandler();
		ASHandler = registryCreator.getAccountingSystemHandler();
		cashRegister = new CashRegister();
		this.SIProvider = SIProvider;
		
	}
	
	/**
	 * Adds a <code>RevenueObserver<code> to the current Controller.
	 * @param revObs	The <code>RevenueObserver<code> to be added.
	 */
	public void addRevenueObserver(RevenueObserver revObs) {
		revenueObservers.add(revObs);
	}
	
	/**
	 * Starts a new <code>Sale</code>.
	 */
	public void startSale() {
		this.currentSale = new Sale();
	}
	
	/**
	 * Add new items to the current <code>Sale</code> and returns a <code>ItemRegistraionDTO</code> containing
	 * Information about the state of the current sale. 
	 * 
	 * @param itemID	used to find the corresponding itemDTO.
	 * @return			Information about the registered <code>Item</code> and <code>sale</code>. 
	 * @throws InvalidItemIdentifierException 			If the given <code>ItemIdentifier<code> does not have 
	 * 													a corresponding <code>ItemDTO<code> in the inventory 
	 * 													system.
	 * @throws NullPointerException						If the given <code>itemIdentifier<code> is null.
	 * @throws UnsuccessfulOperationException			If there is another unspecified problem occurred.
	 * @throws IllegalStateException					If the sale has not started.
	 */
	public ItemRegistrationDTO registerItem(ItemIdentifier itemID) throws InvalidItemIdentifierException, 
																			UnsuccessfulOperationException {
		if(currentSale == null) 
			throw new IllegalStateException("Sale has to start befor items can be added");
		try {
			ItemDTO itemDTO = ISHandler.findItem(itemID);
			currentSale.addItem(itemDTO);
			ItemRegistrationDTO itmRegDTO = SIProvider.generateItemRegistrationDTO(currentSale, itemDTO);
			return itmRegDTO;
		}
		catch(InventorySystemNotRespondingException exp) {
			throw new UnsuccessfulOperationException("The given task couldn´t be carried out.", exp);
		}
	}
	
	
	/**
	 * Show to total price that is going to be paid.
	 * @return	The total price that is going to be paid.
	 */
	public TransactionResultDTO endSale() {
		if(currentSale == null)
			throw new IllegalStateException("Trying to end a sale before starting one");
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
	 * 
	 * @throws IllegalStateException	If the sale has not started.
	 * @throws nullPointerException		If the paidAmount is null and the processPayment method is called.
	 */
	public TransactionResultDTO processCashPayment(Amount paidAmount) {
		if(currentSale == null)
			throw new IllegalStateException("sale has to start befor payment");
		CashPayment cashPayment = new CashPayment(paidAmount, cashRegister, currentSale);
		cashPayment.addRevenueObservers(revenueObservers);
		SaleLogDTO saleLog = cashPayment.processPayment(currentSale);
		ASHandler.addSaleLog(saleLog);
		SIProvider.printReceipt(saleLog);
		TransactionResultDTO traResDto = SIProvider.generateTransactionResultDTO(saleLog);
		return traResDto;
	}
}
