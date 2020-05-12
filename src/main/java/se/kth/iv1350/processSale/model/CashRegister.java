package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;

/**
 * The <code>cashRegister</code> that keeps track of the physical 
 * <code>Amount</code> in the real cash register.
 */
public class CashRegister {
	private Amount amntInReg;
	
	/**
	 * Creates a new instance of a cash register.
	 */
	public CashRegister(){
		amntInReg = new Amount(0);
	}
	
	/**
	 * Is used when a payment is added to the real cash register. 
	 * 
	 * @param amount	the total cost of the <code>Item</code>s 
	 * 					in the <code>Sale</code>.
	 */
	public void addPayment(Amount amount) {
		amntInReg = amntInReg.add(amount);
	}
	
	/**
	 * Returns the  amount in the cashRegister.
	 * @return	The amount in the cashRegister.
	 */
	public Amount getAmountInRegister() {
		return new Amount(amntInReg);
	}
}
