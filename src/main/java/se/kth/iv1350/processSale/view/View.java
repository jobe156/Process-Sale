package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.controller.UnsuccessfulOperationException;
import se.kth.iv1350.processSale.model.InvalidItemIdentifierException;
import se.kth.iv1350.processSale.model.ItemIdentifier;
import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.LogHandler;

/**
 * Interface Used to communicate with <code>Controller</code>.
 */
public class View {
	private Controller contrl;
	private ErrorMessageHandler errorMessageHandler;
	private LogHandler logHandler;

	/**
	 * Creates a new instance of controller.
	 * 
	 * @param contrl Used to communicate with other applications.
	 */
	public View(Controller contrl) {
		this.contrl = contrl;
		contrl.addRevenueObserver(new TotalRevenueView());
		this.errorMessageHandler = new ErrorMessageHandler();
		this.logHandler = new LogHandler();
	}

	/**
	 * Is a test run of the program.
	 */
	public void runFakeExecution() {
		try {
			for(int j = 0; j < 2; j++) {
			String breadStringIdentifier = "001";
			String appleStringIdentifier = "002";
			String cerealStringIdentifier = "003";
			String InvalidStringIdentifier = "asd";
			String notRespStrinIdentifier = "abc";
			ItemIdentifier breadItemID = new ItemIdentifier(breadStringIdentifier);
			ItemIdentifier appleItemID = new ItemIdentifier(appleStringIdentifier);
			ItemIdentifier cerealItemID = new ItemIdentifier(cerealStringIdentifier);
			ItemIdentifier invlaidItemID = new ItemIdentifier(InvalidStringIdentifier);
			ItemIdentifier notRespItemID = new ItemIdentifier(notRespStrinIdentifier);
			double paidValue = 1000;
			Amount paidAmount = new Amount(paidValue);
			
			String customerName = "Bob";
			int customerYearOfBirth = 1994;
			CustomerIdentificationDTO customerID = new CustomerIdentificationDTO(customerName, customerYearOfBirth);
			
			contrl.startSale();
			System.out.println("----------------------------------------UserInterface----------------------------------------");
			System.out.println("New sale has started!");
			System.out.println("\nItems are being scanned in\n");

			for (int i = 0; i < 3; i++)
				System.out.println(contrl.registerItem(breadItemID));

			System.out.println(contrl.registerItem(appleItemID));

			for (int i = 0; i < 2; i++)
				System.out.println(contrl.registerItem(cerealItemID));

			try {
				System.out.print(contrl.registerItem(invlaidItemID));
			} catch (InvalidItemIdentifierException exp) {
				errorMessageHandler.displayErrorMessage(
						"'" + exp.getInvalidItemIdentifier().toString() + "' is and invalid item identifier");
			}

			try {
				System.out.print(contrl.registerItem(notRespItemID));
			} catch (UnsuccessfulOperationException exp) {
				errorMessageHandler.displayErrorMessage("operation was unsuccessful");
				logHandler.LogException(exp);
			}

			System.out.println("The total price of the bought items");
			System.out.println(contrl.endSale());
			
			System.out.println("The total price of the bought items after discount");
			System.out.println(contrl.CheckForDiscounts(customerID));

			
			contrl.processCashPayment(paidAmount);
			}
		} catch (InvalidItemIdentifierException exp) {
			errorMessageHandler.displayErrorMessage(
					"'" + exp.getInvalidItemIdentifier().toString() + "' is and invalid item identifier");
		} catch (Exception exp) {
			errorMessageHandler.displayErrorMessage("operation was unsuccessful");
			logHandler.LogException(exp);
		}

	}

}
