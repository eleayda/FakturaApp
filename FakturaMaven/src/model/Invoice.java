package model;

public class Invoice {
	private Company company;
	private String date="________", number="________";
	private Customer customer;
	private Row[] rows = new Row[4];

	private String sumTotal="0", vat="0", discount30="0", discount50="0", sumToPay="0";

	public Invoice() {

	}

	public Invoice(Company company, String date, String number, Customer customer, Row[] rows, String sumTotal,
			String vat, String discount30, String discount50, String sumToPay) {
		super();
		this.company = company;
		this.date = date;
		this.number = number;
		this.customer = customer;
		this.rows = rows;
		this.sumTotal = sumTotal;
		this.vat = vat;
		this.discount30 = discount30;
		this.discount50 = discount50;
		this.sumToPay = sumToPay;
	}

	public Invoice(String date, String number) {
		super();
		this.date = date;
		this.number = number;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Row[] getRows() {
		return rows;
	}

	public void setRows(Row[] rows) {
		this.rows = rows;
	}

	public String getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(String sumTotal) {
		this.sumTotal = sumTotal;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getDiscount30() {
		return discount30;
	}

	public void setDiscount30(String discount30) {
		this.discount30 = discount30;
	}

	public String getDiscount50() {
		return discount50;
	}

	public void setDiscount50(String discount50) {
		this.discount50 = discount50;
	}

	public String getSumToPay() {
		return sumToPay;
	}

	public void setSumToPay(String sumToPay) {
		this.sumToPay = sumToPay;
	}

	public class Row {
		private String descriprion="", discount="",quantity="", price="", sum="";

		public Row(String descriprion, String quantity, String price, String sum) {
			this.descriprion = descriprion;
			this.quantity = quantity;
			this.price = price;
			this.sum = sum;
		}

		public String getDescriprion() {
			return descriprion;
		}

		public void setDescriprion(String descriprion) {
			this.descriprion = descriprion;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getSum() {
			return sum;
		}

		public void setSum(String sum) {
			this.sum = sum;
		}

		public String getDiscount() {
			
			return discount;
		}
		
	}

}
