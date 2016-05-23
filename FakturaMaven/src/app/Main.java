package app;

import java.io.File;
import java.util.Arrays;

import javax.mail.internet.MimeMessage;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import model.Company;
import model.Customer;
import model.Invoice;
import model.InvoicePdfTemplate;
import services.DriveService;
import services.GmailService;
import services.impl.DriveClient;
import services.impl.GmailClient;
import util.HtmlToPdfConverter;

public class Main {

	private DriveService driveService=new DriveClient();
	private GmailService gmailService=new GmailClient();
	

	public void sendTestMethod(String fname) throws Exception {
		Gmail gmail = gmailService.getClient(Arrays.asList(GmailScopes.MAIL_GOOGLE_COM));
		Drive drive = driveService.getClient(Arrays.asList(DriveScopes.DRIVE));
		
		InvoicePdfTemplate invoice = new InvoicePdfTemplate();
		invoice.setCustomer(new Customer("Anders", "Andersson", "Strangata 23", "121345 Huddinge"));
		invoice.setCompany(new Company(fname, "+045 4333773839", "435353-5353377", "54546-34353", "Strandvägen",
				"12134", "Stockholm", "appanu@hotmail.com"));
		Invoice.Row rows[] = { invoice.new Row("descriprion bla bla", "ROT", "10", "300","3000"),
				invoice.new Row("descriprion bla bla", "ROT", "13", "350","3500"),
				invoice.new Row("descriprion bla bla bla bla", "RUT", "1", "400","4000"),
				invoice.new Row("descriprion  bla", "", "10", "300","49888S") };
		invoice.setRows(rows);
	

		HtmlToPdfConverter converter=new HtmlToPdfConverter(invoice.getContent(),invoice.getFileName());
		File file =converter.getSourceFile();
		MimeMessage emailWithAttach = gmailService.createEmailWithAttachment("eleayda@hotmail.com", "me", "TESTY!!!",
				"HALILUIA", file,"application/pdf");
		gmailService.sendMessage(gmail, "me", emailWithAttach);

		driveService.insertFile(drive, invoice.getFileName(), "description", "", "application/pdf",
				 invoice.getFileName());

	}

	public static void main(String[] args) throws Exception {
		Main main = new Main();

		main.sendTestMethod("AppaNu AB");

	}

}
