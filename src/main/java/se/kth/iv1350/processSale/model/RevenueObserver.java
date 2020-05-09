package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;

/**
 * An interface for a observer that is used to keep track of all successful 
 * payments since the program started.
 */

public interface RevenueObserver {

	/**
	 * Adds a new payment to the class implementing this interface.
	 * @param totalPrice	the payment being added.
	 */
	void newPayment(Amount totalPrice);
}
