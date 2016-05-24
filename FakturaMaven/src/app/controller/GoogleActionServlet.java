package app.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.drive.Drive;
import com.google.api.services.gmail.Gmail;

import app.Main;
import model.Company;
import model.Customer;
import model.Invoice;
import model.Invoice.Row;
import model.InvoicePdfTemplate;
import services.DriveService;
import services.GmailService;
import services.impl.DriveClient;
import services.impl.GmailClient;
import util.HtmlToPdfConverter;

/**
 * Servlet implementation class GoogleActionServlet
 */
@WebServlet("/send")
public class GoogleActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InvoicePdfTemplate invoice;
	static InvoiceService invoiceService;
	static{
		try {
			invoiceService=new InvoiceService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		
		invoice = new InvoicePdfTemplate();
		invoice.setDate(request.getParameter("date"));
		invoice.setNumber(request.getParameter("number"));
		invoice.setSumTotal(request.getParameter("sumTot"));
		invoice.setVat(request.getParameter("VAT"));
		invoice.setDiscount30(request.getParameter("discount30"));
		invoice.setDiscount50(request.getParameter("discount50"));
		invoice.setSumToPay(request.getParameter("topay"));
		
	
		Company company = new Company();
		company.setCompanyName(request.getParameter("fNameTop"));
		company.setFhone(request.getParameter("phone"));
		company.setVatNumber(request.getParameter("vatNumber"));
		company.setBank(request.getParameter("bank"));
		company.setAdddress(request.getParameter("companyAddress"));	
		company.setZipCode(request.getParameter("companyZipCode"));
		company.setZipName(request.getParameter("companySity"));
		company.setEmail(request.getParameter("companyEmail"));
		invoice.setCompany(company);

		Customer customer = new Customer();
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setStreet(request.getParameter("address"));
		customer.setSity(request.getParameter("postalZIP"));
		invoice.setCustomer(customer);


		invoice.setRows(getRows(request));

		
		
		try {
			invoiceService.sendGmail(request.getParameter("customEmail"), "Faktura från "+company.getCompanyName(),
					"översender en faktura", invoice);

			String check=request.getParameter("sendDrive");

			if(check!= null)
				invoiceService.insertDrive(invoice);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private Row[] getRows(HttpServletRequest request) {
		Invoice.Row rows[] = { invoice.new Row(request.getParameter("descriptionR1"),
				request.getParameter("discountR1"), request.getParameter("quantityR1"), request.getParameter("priceR1"),
				request.getParameter("sumR1")),

				invoice.new Row(request.getParameter("descriptionR2"), request.getParameter("discountR2"),
						request.getParameter("quantityR2"), request.getParameter("priceR2"),
						request.getParameter("sumR2")),

				invoice.new Row(request.getParameter("descriptionR3"), request.getParameter("discountR3"),
						request.getParameter("quantityR3"), request.getParameter("priceR3"),
						request.getParameter("sumR3")),
				invoice.new Row(request.getParameter("descriptionR4"), request.getParameter("discountR4"),
						request.getParameter("quantityR4"), request.getParameter("priceR4"),
						request.getParameter("sumR4")) };
		return rows;
	}
}
