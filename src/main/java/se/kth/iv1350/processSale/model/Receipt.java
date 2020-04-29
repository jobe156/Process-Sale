package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.SaleLogDTO;

import java.lang.StringBuilder;

public class Receipt {
	private String receiptString;
	
	public Receipt(SaleLogDTO saleLog) {
		StringBuilder builder = new StringBuilder();
		builder.append("Store name: \t"+ saleLog.getStoreName() + "\n");
		builder.append("Store adress: \t" + saleLog.getStoreAddress() + "\n");
		builder.append("Time of sale: \t" + saleLog.getTimeOfSale().toString() + "\n");
		builder.append("Bought items: \t" + "\n\n" );
		for(Item item: saleLog.getItems()) 
			builder.append(item.toString() + "\n");
		builder.append("\nTotalVatAmount:\t" + String.valueOf(saleLog.getTotalVatAmount()) + "\n");
		builder.append("Total price: \t" + saleLog.getTotalPrice().toString() + "\n");
		builder.append("Paid amount: \t" + saleLog.getPaidAmount().toString() + "\n");
		builder.append("Change: \t" + saleLog.getChange().toString() + "\n");
		this.receiptString = builder.toString();
	}
	
	public String toString() {
		return new String(receiptString);
	}
}