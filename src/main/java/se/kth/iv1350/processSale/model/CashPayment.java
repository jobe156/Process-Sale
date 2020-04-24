package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

public class CashPayment {
	private Amount amountPaid;
	private Amount change;
	private CashRegister cashRegister;
	
	
	public CashPayment(Amount amountPaid, CashRegister casheRegister) {
		this.amountPaid = amountPaid;
		this.cashRegister = cashRegister;
	}
	
	public SaleLogDTO ProcessPayment(Sale currentSale) {
		cashRegister.addPayment(currentSale.CalculateFinalPrice());
		change = new Amount(amountPaid);
		change.subtract(currentSale.CalculateFinalPrice());
		SaleLogDTO saleLogDTO = new SaleLogDTO(currentSale, this);
		return saleLogDTO;
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
