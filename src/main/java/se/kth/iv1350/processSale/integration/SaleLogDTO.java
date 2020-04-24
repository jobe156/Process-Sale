package se.kth.iv1350.processSale.integration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.util.Amount;

public class SaleLogDTO {
	private String storeName; //detta behövs lägga till i andra ställen också
	private LocalTime localTime;
	private String storeAdress;
	private List<Item> items;
	private double totalVatRate = 0;
	private Amount totalPrice;
	private Amount paidAmount;
	private Amount change;
	
	public SaleLogDTO(Sale currentSale, CashPayment cPayment) {
		this.localTime = currentSale.getTimeOfSale();
		this.storeAdress = currentSale.getStoreAdress();
		this.items = currentSale.getItems();
		this.totalPrice = currentSale.CalculateFinalPrice();
		for(Item item: items)
			this.totalVatRate += item.getItemVat();
		this.change = cPayment.getChange();
		this.paidAmount = cPayment.getAmountPaid();
	}
	
	public LocalTime getTimeOfSale() {
		return localTime;
	}
	
	public String getStoreAdress() {
		return storeAdress;
	}
	
	public List<Item> getItems() {
		List<Item> itemsCopy = new ArrayList<Item>(items);
		return itemsCopy;
	}
	
	public double getTotalVatRate() {
		return totalVatRate;
	}
	
	public Amount getTotalPrice() {
		Amount totalPriceCopy = new Amount(totalPrice);
		return totalPriceCopy;
	}
	
	public Amount getPaidAmount() {
		Amount paidAmountCopy = new Amount(paidAmount);
		return paidAmountCopy;
	}

	public Amount getChange() {
		Amount changeCopy = new Amount(change);
		return change;
	}
}
