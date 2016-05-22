package model;

public class Company {
	private String companyName="__________", fhone = "__________", vatNumber = "__________", bank = "__________",
			adddress = "__________", zipCode = "__________", zipName = "__________", email = "__________";

	public Company(String companyName, String fhone, String vatNumber, String bank, String adddress, String zipCode,
			String zipName, String email) {
		super();
		this.companyName = companyName;
		this.fhone = fhone;
		this.vatNumber = vatNumber;
		this.bank = bank;
		this.adddress = adddress;
		this.zipCode = zipCode;
		this.zipName = zipName;
		this.email = email;
	}

	public Company() {
		
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return fhone;
	}

	public void setFhone(String fhone) {
		this.fhone = fhone;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAdddress() {
		return adddress;
	}

	public void setAdddress(String adddress) {
		this.adddress = adddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipName() {
		return zipName;
	}

	public void setZipName(String zipName) {
		this.zipName = zipName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
