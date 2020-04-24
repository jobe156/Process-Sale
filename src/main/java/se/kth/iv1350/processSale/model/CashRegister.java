package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;

import se.kth.iv1350.processSale.integration.SaleLogDTO;

public class CashRegister {
	private Amount amntInReg;
	
	public CashRegister(){
		amntInReg = new Amount(0);
	}
	
	public void addPayment(Amount amount) {
		amntInReg.add(amntInReg);
	}
	
}
