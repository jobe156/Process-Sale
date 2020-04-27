package se.kth.iv1350.processSale.integration;

/**
 * Responsible for creating the other external systems.
 */

public class RegistryCreator {
	private InventorySystemHandler ISHandler;
	private AccountingSystemHandler ASHandler;
	
	/**
	 * creates a new instance of registry creator.
	 */
	public RegistryCreator() {
		this.ISHandler = new InventorySystemHandler();
		this.ASHandler = new AccountingSystemHandler();
	}

	/**
	 * Returns the <code>InventorySystemHandler</code>.
	 * @return	The <code>InventorySystemHandler</code>.
	 */
	public InventorySystemHandler getInventorySystemHandler() {
	return this.ISHandler;
	}
	
	/**
	 * Returns the <code>AccountingSystemHAndel</code>.
	 * @return	The <code>AccountingSystemHAndel</code>.
	 */
	public AccountingSystemHandler getAccountingSystemHandler() {
		return this.ASHandler;
	}
}
