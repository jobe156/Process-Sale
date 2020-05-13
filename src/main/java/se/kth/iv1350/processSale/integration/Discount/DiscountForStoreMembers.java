package se.kth.iv1350.processSale.integration.Discount;

import java.util.List;

import se.kth.iv1350.processSale.model.CustomerIdentificationDTO;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

/**
 * Is a discount that is applied if the customer is a store member.
 *
 */
public class DiscountForStoreMembers implements Discount{
	private List<CustomerIdentificationDTO> storeMembers;

	/**
	 * Creates an instance of discount for store members.
	 * @param storeMembers		the list of store members.
	 */
	public DiscountForStoreMembers(List<CustomerIdentificationDTO> storeMembers){
		this.storeMembers = storeMembers;
	}
			
	@Override
	public boolean discountEligible(Sale sale, CustomerIdentificationDTO customerID) {
			for(CustomerIdentificationDTO member: storeMembers)
				if(customerID.getName().equals(member.getName()) &&
						customerID.getYearOfBirth() == member.getYearOfBirth())
							return true;
		return false;
	}

	@Override
	public Amount calculateDicount(Sale sale) {
		return sale.calculateTotalItemPrice().multiply(0.10);
	}

	

}
