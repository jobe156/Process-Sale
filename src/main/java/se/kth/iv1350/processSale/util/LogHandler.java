package se.kth.iv1350.processSale.util;


/**
 * Logs caught exceptions thrown to <code>view<code>.
 */
public class LogHandler {
	
	/**
	 * writes a log, for a caught exception, containing information which
	 * is relevant to a developer.
	 * 
	 * @param exp	the exception caught by the view.
	 * @throws InterruptedException 
	 */
	public void LogException(Exception exp){
		StringBuilder builder = new StringBuilder();
		builder.append("\n----------------------------------------Log----------------------------------------");
		builder.append("\nError message: \n");
		builder.append(exp.getMessage() + "\n");
		builder.append("\nException stack trace:");
		System.out.println(builder);
		exp.printStackTrace();
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\n-----------------------------------------------------------------------------------\n");
	}
}
