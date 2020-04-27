package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

/**
 * Is the payment type of a <code>Sale</code>.
 */
public class CashPayment {
	private Amount amountPaid;
	private Amount change;
	private CashRegister cashRegister;
	
	/**
	 * Creates a new instance of a cash payment.
	 * 
	 * @param amountPaid	The <code>amount</code> paid in cash.
	 * @param cashRegister	The <code>cashRegister</code> that keeps track of the physical 
	 * 						<code>Amount</code> in the real cash register.
	 * @param currentSale	Provides information about the registered <code>Items</code>.
	 */
	public CashPayment(Amount amountPaid, CashRegister cashRegister, Sale currentSale) {			
		this.cashRegister = cashRegister;
		this.amountPaid = amountPaid;
		this.change = amountPaid.subtract(currentSale.CalculateFinalPrice());
	}
	
	/**
	 * Adds the total <code>Amount</code> of the sale to the <code>cashReigster</code> and
	 * returns a <code>SaleLogDTO</code>.
	 * 
	 * @param currentSale	The sale that is begin processed.
	 * @return				A <code>SaleLogDTO</code> containing information about the 
	 * 						processed <code>sale</code> and <code>CashPayment</code>.			
	 */
	public SaleLogDTO processPayment(Sale currentSale) {
		cashRegister.addPayment(currentSale.CalculateFinalPrice());
		SaleLogDTO saleLogDTO = new SaleLogDTO(currentSale, this);
		return saleLogDTO;
	}
	
	/**
	 * Returns the amount paid.
	 * @return	the amount paid.
	 */
	public Amount getAmountPaid() {
		return new Amount(amountPaid);
	}
	
	/**
	 * Returns the change.
	 * @return The change.
	 */
	public Amount getChange() {;
		return new Amount (change);
	}
}
