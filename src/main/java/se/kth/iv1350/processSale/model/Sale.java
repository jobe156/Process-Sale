package se.kth.iv1350.processSale.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.integration.Discount.Discount;

/**
 * Is used to keep track of items being bought and information regarding the
 * transaction.
 * 
 */
public class Sale {
	private LocalTime localTime;
	private String storeName;
	private String storeAddress;
	private List<Item> items = new ArrayList<>();
	private List<Discount> discounts = new ArrayList<>();

	/**
	 * Creates a new instance of a sale which sets the time and date.
	 */
	public Sale() {
		storeName = "The Store";
		localTime = java.time.LocalTime.now();
		storeAddress = "street St 11";
	}

	/**
	 * Returns the registered items.
	 * @return	The registered items.
	 */
	public List<Item> getItems() {
		List<Item> itemsCopy = new ArrayList<Item>(items);
		return itemsCopy;
	}
	
	/**
	 * Returns the time of sale. 
	 * @return	The time of sale.
	 */
	public LocalTime getTimeOfSale() {
		return localTime;
	}
	
	/**
	 * Returns the store address.
	 * @return	The store address.
	 */
	public String getStoreAddress() {
		return new String(storeAddress);
	}
	/**
	 * Returns the store name.
	 * @return	The store name.
	 */
	public String getStoreName() {
		return new String(storeName);
	}
	
	/**
	 * Returns the discounts.
	 * @return	The discounts.
	 */
	public List<Discount> getDiscounts(){
		return discounts;
	}
	
	/**
	 * Adds an <code>item</code>, increases the quantity of an <code>item</code> to
	 * the {@link Sale}. 
	 * 
	 * @param itemDTO  Used to provide information about the item being bought.
	 */
	public void addItem(ItemDTO itemDTO) {
		if (itemDTO == null)
			return;
		for (Item currentItem : items)
			if (currentItem.getItemName().equals(itemDTO.getItemName())) {
				currentItem.increaseQuantity();
				return;
			}
		Item item = new Item(itemDTO);
		items.add(item);
	}
	
	/**
	 * Adds a <code>discount<code>.
	 * @param discount
	 */
	public void addDiscount(Discount discount) {
		discounts.add(discount);
	}
	
	/**
	 * adds a list of <code>discount<code>.
	 * @param discounts
	 */
	public void addDiscounts(List<Discount> discounts) {
		this.discounts.addAll(discounts);
	}
	
	/**
	 * Calculates the final price of the sale, including vat and discount.
	 * 
	 * @return the total <code>amount</code> of the <code>Item</code> including Vat rate.
	 */
	public Amount CalculateFinalPrice() {
		Amount totalPrice = calculateTotalItemPrice();
		if(!discounts.isEmpty())
			for(Discount discount: discounts)
				totalPrice = totalPrice.subtract(discount.calculateDicount(this));
		return totalPrice;
	}
	
	
	/**
	 * Only calculates the total price of the items including vat.
	 * 
	 * @return	
	 */
	public Amount calculateTotalItemPrice() {
		Amount totalItemPrice = new Amount();
		if (!items.isEmpty()) {
			for (Item currentItem : items) {
				totalItemPrice = totalItemPrice.add(currentItem.totalItemPrice());
				totalItemPrice = totalItemPrice.add(currentItem.totalItemVatPrice());
			}
		}
		return totalItemPrice;
	}

}
