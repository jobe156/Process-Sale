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
	
	public String getName() {
		return new String(name);
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
}
