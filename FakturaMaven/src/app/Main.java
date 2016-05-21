package app;

import java.util.Arrays;

import javax.mail.internet.MimeMessage;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import model.Company;
import model.Customer;
import model.Invoice;
import model.Invoice.Row;
import services.DriveService;
import services.GmailMessageService;
import services.GoogleAutorizationService;
import services.impl.DriveServiceImpl;
import services.impl.GoogleAutorizationServiceImpl;
import services.impl.GmailMessageServiceImpl;

public class Main {
	private GoogleAutorizationServiceImpl google = new GoogleAutorizationServiceImpl();
	private GmailMessageService messService = new GmailMessageServiceImpl();

	public void sendTestMethod() throws Exception {
		Gmail gmail = google.getGmailService(Arrays.asList(GmailScopes.MAIL_GOOGLE_COM));
		Drive drive=google.getDriveService(Arrays.asList(DriveScopes.DRIVE));
		
		Invoice invoice = new Invoice();
		invoice.setCustomer(new Customer("Anders", "Andersson", "Strangata 23", "121345 Huddinge"));
		invoice.setCompany(new Company("AppaNU", "+045 4333773839", "435353-5353377", "54546-34353", "Strandvägen",
				"12134", "Stockholm", "appanu@hotmail.com"));
		Invoice.Row rows[] = { invoice.new Row("descriprion bla bla", "ROT", "10", "300"),
				invoice.new Row("descriprion bla bla", "ROT", "13", "350"),
				invoice.new Row("descriprion bla bla bla bla", "RUT", "1", "400"),
				invoice.new Row("descriprion  bla", "", "10", "300") };
		invoice.setRows(rows);
		
		MimeMessage emailWithAttach = messService.createEmailWithAttachment("eleayda@hotmail.com", "me", "TESTY!!!",
				"HALILUIA", invoice);
		messService.sendMessage(gmail, "me", emailWithAttach);
		
		
		  
        DriveService driveService=new DriveServiceImpl();
        
        driveService. insertFile(drive,"Drive-fak-4.jpg","description","SVP","application/pdf","faktura.pdf");
      
	}

	public static void main(String[] args) throws Exception {
		Main main = new Main();
	
		main.sendTestMethod();

	}

}
