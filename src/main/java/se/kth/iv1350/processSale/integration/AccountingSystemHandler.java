package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.processSale.integration.SaleLogDTO;

/**
 * 
 *The handler is responsible for communicating with the external
 *accounting system.
 */
public class AccountingSystemHandler {
	private List<SaleLogDTO> saleLogs;
	
	AccountingSystemHandler(){
		saleLogs = new ArrayList<>();
	}
	
	public void addSaleLog(SaleLogDTO saleLog) {
		if(saleLog != null)
			saleLogs.add(saleLog);
	}		
}
