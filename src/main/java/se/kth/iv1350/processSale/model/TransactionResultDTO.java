package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

/**
 * Provides information about the transaction of a
 * <code>Sale</code>.
 */
public class TransactionResultDTO {
	private Amount totalPrice;
	private Amount amountPaid;
	private Amount change;
	
	/**
	 * Creates a new instance of a display transaction dto.
	 * @param saleLog
	 */
	public TransactionResultDTO(SaleLogDTO saleLog) {
		this.totalPrice = saleLog.getTotalPrice();
		this.amountPaid = saleLog.getPaidAmount();
		this.change = saleLog.getChange();
	}
	
	/**
	 * Returns the total price.
	 * @return The total price.
	 */
	public Amount getTotalPrice() {
		Amount totalPriceCopy = new Amount(totalPrice);
		return totalPriceCopy;
	}
	
	/**
	 * Returns the total price.
	 * @return	The total price.
	 */
	public Amount getAmountPaid() {
		Amount amountPaidCopy = new Amount(amountPaid);
		return amountPaidCopy;
	}

	/**
	 * Returns the change.
	 * @return The change.
	 */
	public Amount getChange() {
		Amount changeCopy = new Amount(change);
		return changeCopy;
	}
	
	/**
	 * Creates a string of the the display transaction dto and returns it.
	 * @return the string of the display transaction dto.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Total price: \t" + totalPrice.toString() + "\n");
		builder.append("Amount paid: \t" + amountPaid.toString() + "\n");
		builder.append("Change: \t" + change.toString() + "\n");
		return builder.toString();
	}
}
