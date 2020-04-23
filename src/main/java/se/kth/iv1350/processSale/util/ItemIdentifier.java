package se.kth.iv1350.processSale.util;

/**
 * 
 * Is used to get <code>ItemDTO</code> from a Inventory System.
 *
 */

public class ItemIdentifier {
	private String identifier; //for now it´s a number like "0001"
	
	/**
	 * Creates an instance of an Identifier. Currently the identifier is 
	 * represented by a String.
	 * 
	 * @param identifier the string representing the identifier.
	 */
	
	public ItemIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * creates an instance of an identifer form an existing Item identifier.
	 * 
	 * @param itemIdentifier
	 */
	public ItemIdentifier(ItemIdentifier itemIdentifier) {
		this.identifier = itemIdentifier.getIdentifier();
	}
	
	private String getIdentifier(){
		String identifierCopy = new String(identifier);
		return identifierCopy;
	}
	
	/**
	 * Check if two identifiers are equal. 
	 * 
	 * @param ItemID 	Used to identify and item.
	 * @return			a boolean value.
	 */
	public boolean equals(ItemIdentifier itemID){
		if(itemID == null) //borde throw exeption, subclasser 
			return false;
		if(identifier.equals(itemID.getIdentifier()))
			return true;
		return false;
	}
}
