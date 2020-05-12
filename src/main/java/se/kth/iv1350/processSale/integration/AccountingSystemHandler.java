package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.model.Item;

/**
 * Represents a program that communicates with an storage system. In this case, it´s used to
 * manages a <code>SaleLogDTO</code> List.
 */
public class AccountingSystemHandler {
	private List<SaleLogDTO> saleLogs = new ArrayList<>();
	
	/**
	 * Creates a new instance of a accounting system handler.
	 */
	AccountingSystemHandler(){
	}
	
	/**
	 * Adds a new <code>SaleLogDTO</code> to the List.
	 * 
	 * @param saleLog	The <code>SaleLogDTO</code> being added to the list.
	 */
	public void addSaleLog(SaleLogDTO saleLog) {
			saleLogs.add(saleLog);
	}
	
	/**
	 * Finds and returns a <code>SaleLogDTO</code> based on the bought <code>Item</code>s. If no 
	 * saleLog with the sought after items is found, null is returned.
	 * 
	 * @param items		The sought after <code>Item</code>s in a sale log. 
	 * @return			The sale log with the same Items.	
	 */
	public SaleLogDTO findSalLogByBoughtItems(List<Item> items) {
		for(SaleLogDTO saleLog: saleLogs) {
			if(saleLog.getItems().size() == items.size())
				for(int i = 0; i < items.size(); i++) {
					if(saleLog.getItems().get(i).equals(items.get(i))) {
						return saleLog;
					}
					else {
						break;
					}
				}
		}
		return null;		
	}
}
