package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

/**
 * Interface Used to communicate with <code>Controller</code>.
 */
public class View {
	private Controller contrl;

	/**
	 * Creates a new instance of controller.
	 * 
	 * @param contrl	Used to communicate with other applications.
	 */
	public View(Controller contrl){
		this.contrl = contrl;
	}	

	/**
	 * Is a test run of the program
	 */
	public void runFakeExecution() {
		String breadStringIdentifier = "001";
		String appleStringIdentifier = "002";
		String cerealStringIdentifier = "003";
		ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
		ItemIdentifier appleItemID = new ItemIdentifier(appleStringIdentifier);
		ItemIdentifier cerealItemID = new ItemIdentifier(cerealStringIdentifier);
		double paidValue = 1000;
		Amount paidAmount = new Amount(paidValue);
		contrl.startSale();
		System.out.println("New sale has started!");
		System.out.println("\nItems are being scanned in\n");
		
		for(int i = 0 ; i < 3; i++)
			System.out.println(contrl.registerItem(breadItemID));

		System.out.println(contrl.registerItem(appleItemID));
		
		for(int i =0; i < 2; i++)
			System.out.println(contrl.registerItem(cerealItemID));
		
		System.out.println(contrl.endSale());
		
		System.out.println("\nThe contents of the receipt:\n\n");
		contrl.processCashPayment(paidAmount);
	}
}
