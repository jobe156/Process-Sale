package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Receipt;

/**
 * Is used to print <code>Receipts</code> to the console.
 */
public class Printer {
	private static final Printer printer = new Printer();
	/**
	 * creates a new instance of a printer
	 */
	private Printer() {}
	
	public static Printer getPrinter() {
		return printer;
	}
	
	/**
	 * Prints out a given <code>Receipt</code>.
	 * 
	 * @param receipt	The <code>Receipt</code> being printed.
	 */
	public void printReceipt(Receipt receipt) {
		System.out.println(receipt.toString());
	}
}
