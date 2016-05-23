package model;

public class InvoicePdfTemplate extends Invoice {

	private String fileName = "invoice.pdf";
	private final String MIME = "application/pdf";
	private String content = "";

	public InvoicePdfTemplate() {
		super();

	}

	public InvoicePdfTemplate(String fileName) {
		this();
		this.fileName = fileName;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMIME() {
		return MIME;
	}

	public String getContent() {
		Company company = this.getCompany();
		Customer customer = this.getCustomer();
		StringBuilder sb = new StringBuilder();

		sb.append("<head>");
		sb.append("<meta charset='utf-8'/>");
		sb.append("</head>");

		sb.append("<body style='margin: 5% 20% 5% 20%'>");

		sb.append("<div class='ui segment' id='content' >");

		sb.append("<table name='customer-info' class='table' align='center' width='100%'>");
		sb.append("<tr   align='left'>");

		sb.append("<td colspan='4'>");
		sb.append(company.getCompanyName());

		sb.append("</td>");

		sb.append("<td colspan='4'>");
		sb.append("<p> Faktura </p>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table >");
		sb.append("<table >");
		sb.append("<tr >");
		sb.append("<th colspan='6'>");

		sb.append("</th>");
		sb.append("<th>datum</th>");
		sb.append("<th>");
		sb.append("nummer");
		sb.append("</th>");
		sb.append("</tr>");
		sb.append("<tr >");
		sb.append("<td colspan='6'>");

		sb.append("</td>");
		sb.append("<td>");
		sb.append(getDate());

		sb.append("</td>");

		sb.append("<td>");
		sb.append(getNumber());
		sb.append("</td>");

		sb.append("</tr>");
		sb.append("</table >");
		sb.append(" </div>");
		sb.append("<table >");
		sb.append("<tr   align='left'>");
		sb.append("<th colspan='6'>");
		sb.append("Betalningsvillkor 20 dagar");
		sb.append("</th>");

		sb.append("<th>");
		sb.append("Köpare");
		sb.append("</th>");
		sb.append("</tr>");
		sb.append("<tr   align='left'>");
		sb.append("<td colspan='6'>");
		sb.append("</td>");
		sb.append("<td>");
		sb.append(customer.getFirstName());
		sb.append("</td>");

		sb.append("</tr>");
		sb.append("<tr   align='left'>");
		sb.append("<td colspan='6'>");
		sb.append("</td>");
		sb.append("<td>");
		sb.append(customer.getLastName());
		sb.append("</td>");

		sb.append("</tr>");
		sb.append("<tr   align='left'>");
		sb.append("<td colspan='6'>");
		sb.append("</td>");
		sb.append("<td>");
		sb.append(customer.getStreet());
		sb.append("</td>");

		sb.append("</tr>");
		sb.append("<tr   align='left'>");
		sb.append("<td colspan='6'>");
		sb.append("</td>");
		sb.append("<td>");
		sb.append(customer.getSity());
		sb.append("</td>");

		sb.append("</tr>");
		sb.append("</table>");

		sb.append("<table class='ui table'>");
		sb.append("<tr>");
		sb.append("<th colspan='4' >");
		sb.append("<p>Beskrivning</p>");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("<p>RUT/ROT</p>");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("<p>tim/antal</p>");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("<p>a-pris</p>");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("<p>summa</p>");
		sb.append("</th>");
		sb.append("</tr>");

		sb.append(getRowsAsString());

		sb.append("<tr align='right'>");
		sb.append("<td colspan='3'></td>");
		sb.append("<td>summa:</td>");
		sb.append("<td><label id='sum-total'>" + this.getSumTotal() + "</label></td>");
		sb.append("</tr>");
		sb.append("<tr align='right'>");
		sb.append("<td colspan='3'></td>");
		sb.append("<td>moms 25%:</td>");
		sb.append("<td><label id='vat'>" + this.getVat() + "</label></td>");
		sb.append("</tr>");
		sb.append("<tr align='right'>");
		sb.append("<td colspan='3'></td>");
		sb.append("<td>ROT 30%:</td>");
		sb.append("<td><label id='discount30'>" + this.getDiscount30() + "</label></td>");
		sb.append("</tr>");
		sb.append("<tr align='right'>");
		sb.append("<td colspan='3'></td>");
		sb.append("<td>RUT 50%:</td>");
		sb.append("<td><label id='discount50'>" + this.getDiscount50() + "</label></td>");
		sb.append("</tr>");
		sb.append("<tr align='right'>");
		sb.append("<td colspan='3'></td>");
		sb.append("<td><p>Att betala:</p></td>");
		sb.append("<td><p><label id='to-pay'>" + this.getSumToPay() + "</label></p></td>");
		sb.append("</tr>");

		sb.append("</table>");
		sb.append("<div class='ui divider hidden'></div>");
		sb.append("<table class='ui table'>");
		sb.append("<tr>");
		sb.append("<th>");
		sb.append("företag :");
		sb.append("</th>");
		sb.append("<th >");
		sb.append("telefon :");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("org nummer :");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("Bank Giro");
		sb.append("</th>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append(company.getCompanyName());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(company.getPhone());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(company.getVatNumber());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(company.getBank());
		sb.append("</td>");

		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<table class='ui table'>");
		sb.append("<tr>");
		sb.append("<th>");
		sb.append("Adress :");
		sb.append("</th>");
		sb.append("<th >");
		sb.append("Post Nummer :");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("Ort :");
		sb.append("</th>");
		sb.append("<th>");
		sb.append("Email :");
		sb.append("</th>");
		sb.append("</tr>");
		sb.append("<tr>");

		sb.append("<td>");
		sb.append(company.getAdddress());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(company.getZipCode());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(company.getZipName());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(company.getEmail());
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("</table>");

		sb.append("<h4 align='center'>Copyright © 2016 FakturaApp.com</h4>");

		sb.append("</body>");
		this.content = sb.toString();
		return content;
	}

	private String getRowsAsString() {

		Row[] rows = getRows();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < rows.length; i++) {
			sb.append("<tr class='row'>");
			sb.append("<td colspan='4'>");
			sb.append("<label class='sum'>" + getRows()[i].getDescriprion() + "</label>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<label class='sum'>" + getRows()[i].getDiscount() + "</label>");
			sb.append("</td>");
			sb.append("<td >");
			sb.append("<label class='sum'>" + getRows()[i].getQuantity() + "</label>");
			sb.append("</td>");
			sb.append("<td >");
			sb.append("<label class='sum'>" + getRows()[i].getPrice() + "</label>");
			sb.append("</td>");
			sb.append("<td >");
			sb.append("<label class='sum'>" + getRows()[i].getSum() + "</label>");
			sb.append("</td>");
			sb.append("</tr>");

		}
		return sb.toString();

	}

}
