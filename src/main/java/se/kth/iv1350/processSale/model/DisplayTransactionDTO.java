package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

public class DisplayTransactionDTO {
	private Amount totalPrice;
	private Amount amountPaid;
	private Amount change;
	
	public DisplayTransactionDTO(Sale sale){
		totalPrice = sale.CalculateFinalPrice();
	}
	
	public DisplayTransactionDTO(SaleLogDTO saleLog) {
		this.totalPrice = saleLog.getTotalPrice();
		this.amountPaid = saleLog.getPaidAmount();
		this.change = saleLog.getChange();
	}
	
	public Amount getTotalPrice() {
		Amount totalPriceCopy = new Amount(totalPrice);
		return totalPriceCopy;
	}
	
	public Amount getAmountPaid() {
		Amount amountPaidCopy = new Amount(amountPaid);
		return amountPaidCopy;
	}

	public Amount getChange() {
		Amount changeCopy = new Amount(change);
		return changeCopy;
	}	
}
