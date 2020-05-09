package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.RevenueObserver;
import se.kth.iv1350.processSale.util.Amount;

import java.util.ArrayList;
import java.util.List;


/**
 * Is used to keep track of all successful payments since the program started. 
 * Also able to show the total revenue earned.
 *
 */
public class TotalRevenueView implements RevenueObserver {
	private List<Amount> payments = new ArrayList<>();
	
	/**
	 * Adds a new payment to the class implementing this interface.
	 * @param totalPrice	the payment being added.
	 */
	@Override
	public void newPayment(Amount totalPrice) {
		payments.add(totalPrice);
		printCurrentState();
	}
	
	/**
	 * Prints the revenue of all the payments and the total of all the revenue. 
	 */
	public void printCurrentState() {
		Amount totalAmount = new Amount();
		StringBuilder builder = new StringBuilder();
		builder.append("\n----------Total Revenue----------\n\n");
		for(Amount amount: payments) {
			totalAmount = totalAmount.add(amount);
			builder.append("\t" + amount.toString() + "\n");	
		}
		builder.append("+\n");
		builder.append("---------------\n");
		builder.append("\t" + totalAmount.toString() + "\n\n");
		builder.append("\n---------------------------------\n");
		System.out.println(builder.toString());
	}

}
