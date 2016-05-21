package model;

public class Customer {
private String firstName="", street="", sity="";
private String lastName="";

public Customer(String firstName, String lastName,String street, String sity) {
	super();
	this.firstName = firstName;
	this.lastName=lastName;
	this.street = street;
	this.sity = sity;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public String getSity() {
	return sity;
}

public void setSity(String sity) {
	this.sity = sity;
}

public String getLastName() {
	
	return this.lastName;
}

}
