package app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.mail.internet.MimeMessage;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import model.InvoicePdfTemplate;
import services.DriveService;
import services.GmailService;
import services.impl.DriveClient;
import services.impl.GmailClient;
import util.HtmlToPdfConverter;

public class InvoiceService {
	private DriveService driveService = new DriveClient();
	private GmailService gmailService = new GmailClient();
	private Gmail gmail;
	private Drive drive;

	public InvoiceService() throws IOException {
		drive = getDriveClient();
		gmail = getGmailClient();
	}

	public void sendGmail(String mailTo, String mailSubject, String mailContent,InvoicePdfTemplate invoice) throws Exception {

		HtmlToPdfConverter converter = new HtmlToPdfConverter(invoice.getContent(), invoice.getFileName());
		File file = converter.getSourceFile();
		MimeMessage emailWithAttach = gmailService.createEmailWithAttachment(mailTo, "me", mailSubject,
				mailContent, file, "application/pdf");
		gmailService.sendMessage(gmail, "me", emailWithAttach);

	}

	public void insertDrive(InvoicePdfTemplate invoice) {

		driveService.insertFile(drive, invoice.getFileName(), "description", "", "application/pdf",
				invoice.getFileName());
	}

	private Drive getDriveClient() throws IOException {
		Drive drive = driveService.getClient(Arrays.asList(DriveScopes.DRIVE));
		return drive;
	}

	private Gmail getGmailClient() throws IOException {
		Gmail gmail = gmailService.getClient(Arrays.asList(GmailScopes.MAIL_GOOGLE_COM));
		return gmail;
	}

}
