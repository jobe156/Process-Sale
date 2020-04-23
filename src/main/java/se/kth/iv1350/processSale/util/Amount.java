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
	
	// consider removing cuz mabye only used in test.
	
	/**
	 * Creates an instance of an {@link Amount} from an instance of an {@link Amount}.
	 * 
	 * @param amount	Is an instance of {@link Amount}.
	 */
	public Amount(Amount amount) {	
		currentAmount = amount.getCurrentAmount();
	}
	
	
	/**
	 * Creates an instance of the class without any parameters witch results in the
	 * current amount = 0.
	 */
	public Amount () {
		this(0);
	}
	
	private double getCurrentAmount() {
		Amount currentAmountCopy = new Amount(currentAmount);
		return currentAmount;
	}
	
	/**
	 * Checks to see if a given amount is equal to the current amount.
	 * 
	 * @param amount	Is the amount the current amount is being compared to.
	 */
	public boolean equal(Amount amount) {
		if((amount != null) && currentAmount == amount.getCurrentAmount())
			return true;
		return false;
	}
	
	/**
	 * Compares two amounts.
	 * 
	 * @param amount	the amount being compared with.
	 * @return			Return 1 if compared is less then current amount.
	 * 					Return 0 if amounts are equal.
	 * 					Return -1 if compared is larger the current amount.
	 */
	public int compare(Amount amount) {
		if(currentAmount > amount.currentAmount)
			return 1;
		else if(currentAmount == amount.currentAmount)
			return 0;
		else
			return -1;	
	}
	
	/**
	 * Add an {@link amount} the the current {@link amount}.
	 * 
	 * @param amount	amount being added to the current {@link Amount}.
	 */
	public void add(Amount amount){
		if(amount != null ) {
			currentAmount += amount.getCurrentAmount();
				if(currentAmount < 0 ) {
					currentAmount = 0;
				}
		}
	}
	
	
	
	/**
	 * Multiplies the the current {@link Amount} with an multiple larger the ze
	 * 
	 * @param multiple	Is the multiple.
	 */
	public void multiply(double multiple) {
		if(multiple > 0)
			currentAmount *= multiple;
	}
	
	//correspondingAmount
}
