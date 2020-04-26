package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.SaleLogDTO;

public class CashPayment {
	private Amount amountPaid;
	private Amount change;
	private CashRegister cashRegister;
	
	public CashPayment(Amount amountPaid, CashRegister cashRegister, Sale currentSale) {			
		this.cashRegister = cashRegister;
		this.amountPaid = amountPaid;
		this.change = amountPaid.subtract(currentSale.CalculateFinalPrice());
	}
	
	public SaleLogDTO processPayment(Sale currentSale) {
		cashRegister.addPayment(currentSale.CalculateFinalPrice());
		SaleLogDTO saleLogDTO = new SaleLogDTO(currentSale, this);
		return saleLogDTO;
	}
	
	public Amount getAmountPaid() {
		return new Amount(amountPaid);
	}
	
	public Amount getChange() {;
		return new Amount (change);
	}
}
