package se.kth.iv1350.processSale.integration;

/**
 * 
 * responsible for creating the other external systems.
 *
 */

public class RegistryCreator {
	private InventorySystemHandler ISHandler;
	private AccountingSystemHandler ASHandler;
	
	/**
	 * 
	 * creates an instance of the class.
	 * 
	 */
	public RegistryCreator() {
		this.ISHandler = new InventorySystemHandler();
		this.ASHandler = new AccountingSystemHandler();
	}

	public InventorySystemHandler getInventorySystemHandler() {
	return this.ISHandler;
	}
	
	public AccountingSystemHandler getAccountingSystemHandler() {
		return this.ASHandler;
	}
}
