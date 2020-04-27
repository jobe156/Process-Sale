package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Receipt;

/**
 * Is used to print <code>Receipts</code> to the console.
 */
public class Printer {
	
	/**
	 * creates a new instance of a printer
	 */
	public Printer() {}
	
	/**
	 * Prints out a given <code>Receipt</code>.
	 * 
	 * @param receipt	The <code>Receipt</code> being printed.
	 */
	public void printReceipt(Receipt receipt) {
		System.out.println(receipt.toString());
	}
}
