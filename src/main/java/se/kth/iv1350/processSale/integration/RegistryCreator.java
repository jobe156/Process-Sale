package se.kth.iv1350.processSale.integration;

/**
 * Responsible for creating the other external systems.
 */

public class RegistryCreator {
	private InventorySystemHandler ISHandler;
	private AccountingSystemHandler ASHandler;
	private DiscountSystemHandler DSHandler;
	
	/**
	 * creates a new instance of registry creator.
	 */
	public RegistryCreator() {
		this.ISHandler = new InventorySystemHandler();
		this.ASHandler = new AccountingSystemHandler();
		this.DSHandler = new DiscountSystemHandler();
	}

	/**
	 * Returns the <code>InventorySystemHandler</code>.
	 * @return	The <code>InventorySystemHandler</code>.
	 */
	public InventorySystemHandler getInventorySystemHandler() {
	return this.ISHandler;
	}
	
	/**
	 * Returns the <code>AccountingSystemHandel</code>.
	 * @return	The <code>AccountingSystemHandel</code>.
	 */
	public AccountingSystemHandler getAccountingSystemHandler() {
		return this.ASHandler;
	}
	
	/**
	 * Returns the <code>DiscountSystemHandler</code>.
	 * @return	The <code>DiscountSystemHandler</code>.
	 */
	public DiscountSystemHandler getDiscountSystemHandler() {
		return this.DSHandler;
	}
}
