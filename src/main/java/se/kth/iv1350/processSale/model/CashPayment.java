package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Is the payment type of a <code>Sale</code>.
 */
public class CashPayment {
	private Amount amountPaid;
	private Amount change;
	private Amount totalPrice;
	private CashRegister cashRegister;
	
	private List<RevenueObserver> revenueObservers = new ArrayList<>();
	
	/**
	 * Creates a new instance of a cash payment. if amountPaid or currentSale arguments are null
	 * change will be given a value of -1.
	 * 
	 * @param amountPaid	The <code>amount</code> paid in cash.
	 * @param cashRegister	The <code>cashRegister</code> that keeps track of the physical 
	 * 						<code>Amount</code> in the real cash register.
	 * @param currentSale	Provides information about the registered <code>Items</code>.
	 * 
	 */
	public CashPayment(Amount amountPaid, CashRegister cashRegister, Sale currentSale) {			
		this.cashRegister = cashRegister;
		this.amountPaid = amountPaid;
		this.totalPrice = currentSale.CalculateFinalPrice();
		this.change = amountPaid.subtract(totalPrice);
	}
	
	/**
	 * Adds a <code>RevenueObserver<code> to the current CashPayment.
	 * @param revObs	The <code>RevenueObserver<code> to be added.
	 */
	public void addRevenueObserver(RevenueObserver revObs) {
		revenueObservers.add(revObs);
	}
	
	/**
	 * Adds a list of <code>RevenueObserver<code>s to the current CashPayment.
	 * @param observers	The <code>RevenueObserver<code>s to be added.
	 */
	public void addRevenueObservers(List<RevenueObserver> observers) {
		for(RevenueObserver revObs: observers)
			addRevenueObserver(revObs);
	}
	
	/**
	 * Notifies all the Observers in the current CashPayment.
	 */
	public void notifyObservers() {
		for(RevenueObserver revObs: revenueObservers)
			revObs.newPayment(totalPrice);
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
		cashRegister.addPayment(totalPrice);
		notifyObservers();
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
	
	/**
	 * Returns the total price
	 * @return The total price.
	 */
	public Amount getTotalPrice() {
		return new Amount(totalPrice);
	}
}
