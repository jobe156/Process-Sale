package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.model.*;

/**
 * Is a <code>Exception<code> that is thrown if there does not exist a <code>ItemDTO<code>
 * for a given <code>ItemIdentifier<code>.
 */
public class InvalidItemIdentifierException extends Exception{
		private ItemIdentifier invalidItemID;
		
		/**
		 * Creates an instance of the <code>InvalidItemIdentifierException<code>.
		 * 
		 * @param invalidItemID		The <code>ItemIdentifier<code> that did´t have a 
		 * 							<code>ItemdDTO<code> associated with it.
		 */
		public InvalidItemIdentifierException(ItemIdentifier invalidItemID) {
			super("No item with the given ItemIdentifer '" + invalidItemID.toString() + "'  Could be found.");
			this.invalidItemID = invalidItemID;
		}
	
		/**
		 * Returns the invalid <code>ItemIdentifier<code>. 
		 * @return	The invalid <code>ItemIdentifier<code>.
		 */
		public ItemIdentifier getInvalidItemIdentifier() {
			return new ItemIdentifier(invalidItemID);
		}
}
