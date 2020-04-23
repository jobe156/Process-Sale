package se.kth.iv1350.processSale.integration;

/**
 * 
 * responsible for creating the other external systems.
 *
 */

public class RegistryCreator {
	private InventorySystemHandler ISHandler;
	private AccountingSystemHandler ASHandler;
	private DiscountRepositoryHandler DRHandler;
	
	/**
	 * 
	 * creates an instance of the class.
	 * 
	 */
	public RegistryCreator() {
		this.ISHandler = new InventorySystemHandler();
		this.ASHandler = new AccountingSystemHandler();
		this.DRHandler = new DiscountRepositoryHandler();
	}

	public InventorySystemHandler getInventorySystemHandler() {
	return this.ISHandler;
	}
	
	public AccountingSystemHandler getAccountingSystemHandler() {
		return this.ASHandler;
	}
	
	public DiscountRepositoryHandler getDiscountRepositoryHandler() {
		return this.DRHandler;
	}
}
