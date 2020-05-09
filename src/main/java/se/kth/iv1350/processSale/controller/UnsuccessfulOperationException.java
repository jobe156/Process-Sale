package se.kth.iv1350.processSale.controller;

/**
 * A general <code>Exception<code> for unchecked exceptions that are thrown to the 
 * <code>View<code> class.
 *
 */
public class UnsuccessfulOperationException extends Exception{
	
	/**
	 * Creates an instance of <code>UnsuccessfulOperationException<code>.
	 * 
	 * @param msg	The message of the exception.
	 */
	public UnsuccessfulOperationException(String msg, Exception cause) {
		super(msg, cause);
	}

}
