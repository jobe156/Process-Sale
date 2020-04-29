package se.kth.iv1350.processSale.startup;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.view.View;
import se.kth.iv1350.processSale.model.SaleInformationProvider;

/** 
 *Include the main method responsible for starting other
 *applications.
 */
public class Main {
	
		/**
		 * Responsible for starting other applications.
		 * 
		 * @param args is not used in the method
		 */
		public static void main (String[] args) {
			Printer printer = new Printer();
			SaleInformationProvider SIProvider = new SaleInformationProvider(printer);
			Controller controller = new Controller( SIProvider);
			View view = new View(controller);
			view.runFakeExecution();
		}
}
