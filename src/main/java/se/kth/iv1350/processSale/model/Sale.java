package se.kth.iv1350.processSale.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Discount;
import se.kth.iv1350.processSale.integration.DiscountRepositoryHandler;
import se.kth.iv1350.processSale.integration.DiscountFactors;
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
	// private List<Discount> discounts = new ArrayList<>();

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

	
	/*
	 * private boolean isItemInSale(ItemDTO itemDTO) { for(Item item: items)
	 * if(item.getItemName().equals(itemDTO.getItemName())) return true;
	 * 
	 * return false; }
	 */
	

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
			/*
			for (Discount disc : discounts)
				disc.applyDiscount(totalPrice);
				*/
		}
		return totalPrice;
	}
	
	/**
	 * Checks to see if any discounts can be applied to the {@link Sale}.
	 * 
	 * @param DRHandler Used to finds discounts in some external system.
	 * @param CustomerID Used to identify if the customer is eligible for a discount
	 * 
	 */
	
	/*
	public void LookForDiscounts(DiscountRepositoryHandler DRHandler, CustomerIdentificationDTO customerID) {
		if(customerID != null && DRHandler != null && customerID.getDiscountEligibility()) {
			Discount searchedDiscount; 
			DiscountFactors discountFactors = new DiscountFactors(this, null);
			for(Item item: items) {
				discountFactors.setItemName(item.getItemName());
				searchedDiscount = DRHandler.findDiscount(discountFactors);
			
				if(searchedDiscount != null)
					discounts.add(searchedDiscount);
			}
		}
	}
	*/
	
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
}
