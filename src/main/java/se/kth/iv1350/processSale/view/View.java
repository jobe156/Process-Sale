package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.util.Amount;

/**
 * 
 * Interface Used to communicate with <code>Controller</code>.
 *
 */
public class View {
	private Controller contrl;

	/**
	 * Creates an instance of the class.
	 * 
	 * @param contrl	Used to communicate with other applications.
	 */
	public View(Controller contrl){
		this.contrl = contrl;
	}	

	public void runFakeExecution() {
		ItemIdentifier itemID1 = new ItemIdentifier("001");
		ItemIdentifier itemID2 = new ItemIdentifier("002");
		Amount paidAmount = new Amount(200);
		contrl.startSale();
		System.out.println("New sale has started!");
		System.out.println("\nItems are being scanned in\n");
		System.out.println(contrl.registerItem(itemID1));
		System.out.println(contrl.registerItem(itemID2));
		System.out.println("\nThe contents of the receipt:\n\n");
		contrl.processCashPayment(paidAmount);
	}
}
