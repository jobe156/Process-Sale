package se.kth.iv1350.processSale.util;

/**
 * 
 * Represent a amount of an currency.
 *
 */

public class Amount {
	private double currentAmount;
	
	/**
	 * Creates an instance of the class.
	 * 
	 * @param amount	The amount represented by a double.
	 */
	public Amount(double amount) {
		this.currentAmount = amount;
	}
	
	/**
	 * Creates an instance of an {@link Amount} from an instance of an {@link Amount}.
	 * 
	 * @param amount	Is an instance of {@link Amount}.
	 */
	public Amount(Amount amount) {	
		currentAmount = amount.currentAmount;
	}
	
	
	/**
	 * Creates an instance of the class without any parameters witch results in the
	 * current amount = 0.
	 */
	public Amount () {
		this(0);
	}
	
	/**
	 * Checks to see if a given amount is equal to the current amount.
	 * 
	 * @param amount	Is the amount the current amount is being compared to.
	 */
	@Override
	public boolean equals(Object other) {
		if(other == null  || !(other instanceof Amount))
			return false;
		Amount otherAmount = (Amount) other;
		return currentAmount == otherAmount.currentAmount;
	}
	
	/**
	 * Add an {@link amount} the the current {@link amount}.
	 * 
	 * @param amount	amount being added to the current {@link Amount}.
	 */
	public Amount add(Amount amount){
			return new Amount(currentAmount + amount.currentAmount);
	}
	
	/**
	 * Add an {@link amount} the the current {@link amount}.
	 * 
	 * @param amount	amount being added to the current {@link Amount}.
	 */
	public Amount subtract(Amount amount){
			return new Amount(currentAmount - amount.currentAmount);
	
	}
	
	/**
	 * Multiplies the the current {@link Amount} with an multiple larger then zero
	 * 
	 * @param multiple	Is the multiple.
	 */
	public Amount multiply(double multiple) {
		 return new Amount(currentAmount * multiple);
	}
	
	/**
	 * Creates a string of the amount with one deciaml and returns it.
	 * @return the corresponding string of the amount.
	 */
	public String toString() {
		String stringAmount = Double.toString(currentAmount);
		int pointIndex = stringAmount.indexOf(".");
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < pointIndex+2; i++) {
				builder.append(stringAmount.charAt(i));
		}
		return builder.toString();
	}
}
