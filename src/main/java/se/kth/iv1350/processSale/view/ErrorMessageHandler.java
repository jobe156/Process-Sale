package se.kth.iv1350.processSale.view;
/**
 * Displays information about an exception to the interface.
 */
public class ErrorMessageHandler {

	/**
	 * Displays an message to the interface by printing it to system out. 
	 * 
	 * @param msg	the message that should be displayed when a exception is thrown.
	 */
	void displayErrorMessage(String msg) {
		StringBuilder builder = new StringBuilder();
		builder.append("A problem has occured:\n");
		builder.append(msg);
		builder.append("\n");
		System.out.print(builder);
	}
}
