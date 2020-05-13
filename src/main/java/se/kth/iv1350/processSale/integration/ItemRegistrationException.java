package se.kth.iv1350.processSale.integration;

/**
 * a general <code>RuntimeException<code> for when the inventory system is not responding.
 */
public class ItemRegistrationException extends RuntimeException{
	
	/**
	 * Creates an instance of the <code>InventorySystemNotRespondingException<code>
	 * @param msg		The messages that the exception should display.
	 */
	public ItemRegistrationException(String msg) {
		super(msg);
	}

}
