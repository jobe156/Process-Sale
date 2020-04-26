package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.SaleLogDTO;
import se.kth.iv1350.processSale.model.Item;

/**
 * 
 *The handler is responsible for communicating with the external
 *accounting system.
 */
public class AccountingSystemHandler {
	private List<SaleLogDTO> saleLogs = new ArrayList<>();
	
	AccountingSystemHandler(){
	}
	
	public void addSaleLog(SaleLogDTO saleLog) {
			saleLogs.add(saleLog);
	}
	
	public SaleLogDTO findSalLogByBoughtItems( List<Item> items) {
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
