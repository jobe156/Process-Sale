package se.kth.iv1350.processSale.model;

public class CustomerIdentificationDTO {
	private String name;
	private int yearOfBirth;
	
	/**
	 * Is the identification of the customer.
	 * 
	 * @param name			Customer name.
	 * @param yearOfBirth	The birth year of the customer.
	 */
	public CustomerIdentificationDTO(String name, int yearOfBirth ) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}
	
	/**
	 * Returns the name of the customer. 
	 * @return	name of the Customer.
	 */
	public String getName() {
		return new String(name);
	}
	
	/**
	 * Returns the year of birth. 
	 * @return	the year of birth.
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}
}
