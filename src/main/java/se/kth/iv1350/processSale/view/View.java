package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;

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
		
	}
}
