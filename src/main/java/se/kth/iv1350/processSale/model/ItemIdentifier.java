package se.kth.iv1350.processSale.model;

/**
 * 
 * Is used to get <code>ItemDTO</code> from a Inventory System.
 *
 */
public class ItemIdentifier {
	private String stringIdentifier;
	
	/**
	 * Creates a new instance of a StringIdentifier. Currently theStringIdentifier is 
	 * represented by a String. A exception is thrown if the argument is
	 * null.
	 * 
	 * @paramStringIdentifier the string representing theStringIdentifier.
	 */
	public ItemIdentifier(String stringIdentifier) {
		this.stringIdentifier = stringIdentifier;
	}
	
	/**
	 * Creates an instance of an identifier form an existing ItemStringIdentifier.
	 * 
	 * @param itemIdentifier
	 */
	public ItemIdentifier(ItemIdentifier itemIdentifier) {
		this.stringIdentifier = itemIdentifier.stringIdentifier;
	}
	
	/**
	 * Compares the current <code>ItemIdentifier</code> with a given to see if they are equal or not.
	 * 
	 * @param other A <code>ItemIdentifier</code> that the current one is compared with.
	 * @return		returns <code>True</code> if the two <code>ItemIdentifier</code>s are equal and <code>false</code> 
	 * 				if they are not. If the argument is null or not an instance of a <code>ItemIdentifier</code>, false 
	 * 				is returned.
	 */
	@Override
	public boolean equals(Object other){
		if(other == null || !(other instanceof ItemIdentifier))
			return false;
		ItemIdentifier otherIdentifier = (ItemIdentifier) other;
		return stringIdentifier.equals(otherIdentifier.stringIdentifier);
	}
}
