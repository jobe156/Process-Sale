package se.kth.iv1350.processSale.integration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.model.CashPayment;
import se.kth.iv1350.processSale.util.Amount;

public class SaleLogDTO {
	private String storeName;
	private String storeAdress;
	private LocalTime localTime;
	private List<Item> items;
	private double totalVatRate = 0;
	private Amount totalPrice;
	private Amount paidAmount;
	private Amount change;
	
	public SaleLogDTO(Sale currentSale, CashPayment cPayment) {
		this.storeName = currentSale.getStoreName();
		this.localTime = currentSale.getTimeOfSale();
		this.storeAdress = currentSale.getStoreAdress();
		this.items = currentSale.getItems();
		this.totalPrice = currentSale.CalculateFinalPrice();
		for(Item item: items)
			this.totalVatRate += item.getItemVat();
		this.change = cPayment.getChange();
		this.paidAmount = cPayment.getAmountPaid();
	}
	
	public String getStoreName() {
		return new String(storeName);
	}
	
	public String getStoreAdress() {
		return new String(storeAdress);
	}
	
	public LocalTime getTimeOfSale() {
		return localTime;
	}
	
	public List<Item> getItems() {
		List<Item> itemsCopy = new ArrayList<Item>(items);
		return itemsCopy;
	}
	
	public double getTotalVatRate() {
		return totalVatRate;
	}
	
	public Amount getTotalPrice() {
		return new Amount(totalPrice);
	}
	
	public Amount getPaidAmount() {
		return new Amount(paidAmount);
	}

	public Amount getChange() {
		return new Amount(change);
	}
	
	@Override 
	public boolean equals(Object other) {
		if(other == null || !(getClass() == other.getClass()))
			return false;
		SaleLogDTO otherSaleLogDTO = (SaleLogDTO) other;
		if(!(storeName.equals(otherSaleLogDTO.storeName)))
			return false;
		if(!(storeAdress.equals(otherSaleLogDTO.storeAdress)))
			return false;
		if(!(localTime.equals(otherSaleLogDTO.localTime)))
			return false;
		
		for(int i = 0; i < items.size()-1; i++)
			if(!(items.get(i).equals(otherSaleLogDTO.items.get(i))))
				return false;
		
		if(!(totalPrice.equals(otherSaleLogDTO.totalPrice)))
			return false;		
		if(totalVatRate != otherSaleLogDTO.totalVatRate)
			return false;
		if(!(change.equals(otherSaleLogDTO.change)))
			return false;
		if(!(paidAmount.equals(otherSaleLogDTO.paidAmount)))
			return false;
		return true;
	}
}
