package services;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.google.api.services.gmail.Gmail;

import model.Invoice;

public interface GmailService {

	MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText, Invoice invoice)
			throws MessagingException, IOException, Exception;

	void sendMessage(Gmail service, String userId, MimeMessage email) throws MessagingException, IOException;
}
