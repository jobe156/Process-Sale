package se.kth.iv1350.processSale.model;

public class CustomerIdentificationDTO {
	private String fullName;
	private int birthYear;
	private double hight;
	private int socialSecurityNumber;
	private boolean discountEligibility;

	public CustomerIdentificationDTO(String fullName, int birthYear, double hight, int socialSecurityNumber, boolean discountEligibility){
		this.fullName = fullName;
		this.birthYear = birthYear;
		this.hight = hight;
		this.socialSecurityNumber = socialSecurityNumber;
		this.discountEligibility = discountEligibility; // kan komma att ändras ||
	}
	
	public String getFullName(){
		String fullNameCopy = new String(fullName);
		return fullNameCopy;
	}
	
	public int birthYear() {
		return birthYear;
	}
	
	public double gethigth() {
		return hight;
	}
	
	public int getsocialCecurityNumber() {
		return socialSecurityNumber;
	}
	
	public boolean getDiscountEligibility() {
		return discountEligibility;
	}
}
