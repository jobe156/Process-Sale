package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.SaleLogDTO;

import java.lang.StringBuilder;

public class Receipt {
	private SaleLogDTO saleLog;
	private String receiptString;
	
	public Receipt(SaleLogDTO saleLog) {
		this.saleLog = saleLog;
		receiptString = receiptToString();
	}
	
	private String receiptToString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Store adress: " + saleLog.getStoreAdress() + "/n");
		builder.append("Time of sale: " + saleLog.getTimeOfSale().toString() + "/n");
		builder.append("Bought items: " + "/n" );
		for(Item item: saleLog.getItems()) 
			builder.append(item.toString() + "/n");
		builder.append("/nTotalVatRate: " + String.valueOf(saleLog.getTotalVatRate()) + "/n");
		builder.append("Total price: " + saleLog.getTotalPrice().toString() + "/n");
		builder.append("Paid amount: " + saleLog.getPaidAmount().toString() + "/n");
		builder.append("Total price: " + saleLog.getChange().toString() + "/n");
		return builder.toString();
	}
	
	public String getReceiptString() {
		String receiptStringCopy = new String(receiptString);
		return receiptStringCopy;
	}
}
