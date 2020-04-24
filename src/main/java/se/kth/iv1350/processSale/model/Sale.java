package se.kth.iv1350.processSale.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.util.Amount;

/**
 * 
 * Is used to keep track of items being bought and information regarding the
 * transaction.
 * 
 */
public class Sale {
	private LocalTime localTime;
	private String storeAdress;
	private List<Item> items = new ArrayList<>();

	/**
	 * starts an instance of a sale which sets the time and date.
	 */
	public Sale() {
		localTime = java.time.LocalTime.now();
		storeAdress = "street St 11";
	}

	/**
	 * 
	 * @return The list containing the registered items and the quantity of them.
	 */
	public List<Item> getItems() {
		List<Item> itemsCopy = new ArrayList<Item>(items);
		return itemsCopy;
	}

	/**
	 * Adds an <code>item</code>, increases the quantity of an <code>item</code> to
	 * the {@link Sale}.
	 * 
	 * @param itemDTO  Used to provide information about the item being bought,
	 * @param quantity tells the number of items that are being addad to the
	 *                 {@link Sale}.
	 */
	public void addItem(ItemDTO itemDTO, int quantity) {
		if (itemDTO == null || quantity <= 0)
			return;
		for (Item currentItem : items)
			if (currentItem.getItemName().equals(itemDTO.getItemName())) {
				currentItem.increaseQuantity(quantity);
				return;
			}
		Item item = new Item(itemDTO, quantity);
		items.add(item);
	}

	/**
	 * Calculates the final price of the sale.
	 * 
	 * @return the total <code>amount</code> of the <code>Item</code> includeing Vat
	 *         rate and <code>discount</code>.
	 */
	public Amount CalculateFinalPrice() {
		Amount totalPrice = new Amount();
		if (!items.isEmpty()) {
			double totalVatRate = 1;
			for (Item item : items) {
				totalPrice.add(item.totalItemPrice());
				totalVatRate += item.getItemVat();
			}
			totalPrice.multiply(totalVatRate);
		}
		return totalPrice;
	}

	/**
	 * 
	 * gets the sum of the quantities of all items.
	 * 
	 * @return	the total number of items.
	 */
	public int getTotalNumberOfItems() {
		int totalNumberOfItems = 0;
		for(Item item: items) 
			totalNumberOfItems += item.getQuantity();
		
		return totalNumberOfItems;
	}
	
	public LocalTime getTimeOfSale() {
		return localTime;
	}
	
	public String getStoreAdress() {
		String storeAdressCopy = new String(storeAdress);
		return storeAdressCopy;
	}
}
