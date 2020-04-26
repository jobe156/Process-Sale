package se.kth.iv1350.processSale.model;

/**
 * 
 * Is used to get <code>ItemDTO</code> from a Inventory System.
 *
 */
public class ItemIdentifier {
	private String stringIdentifier; //for now it´s a number like "0001"
	
	/**
	 * Creates an instance of anStringIdentifier. Currently theStringIdentifier is 
	 * represented by a String.
	 * 
	 * @paramStringIdentifier the string representing theStringIdentifier.
	 */
	
	public ItemIdentifier(String stringIdentifier) {
		//this.stringIdentifier = (stringIdentifier.isEmpty())?null:stringIdentifier;
		this.stringIdentifier = stringIdentifier;
	}
	
	/**
	 * creates an instance of an identifer form an existing ItemStringIdentifier.
	 * 
	 * @param itemIdentifier
	 */
	public ItemIdentifier(ItemIdentifier itemIdentifier) {
		this.stringIdentifier = itemIdentifier.stringIdentifier;
	}
	
	/**
	 * Check if twoStringIdentifiers are equal. 
	 * 
	 * @param ItemID 	Used to identify and item.
	 * @return			a boolean value.
	 */
	
	@Override
	public boolean equals(Object other){
		if(other == null || !(other instanceof ItemIdentifier))
			return false;
		ItemIdentifier otherIdentifier = (ItemIdentifier) other;
		return stringIdentifier.equals(otherIdentifier.stringIdentifier);
	}
	
	public String getStringIdentifier() {
		return new String (stringIdentifier);
	}
}
