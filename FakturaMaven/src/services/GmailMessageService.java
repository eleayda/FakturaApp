package services;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;


import model.Invoice;

public interface GmailMessageService {

	MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException;

	Message createMessageWithEmail(MimeMessage email) throws MessagingException, IOException;

	MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText, Invoice invoice) throws MessagingException, IOException, Exception;

	void sendMessage(Gmail service, String userId, MimeMessage email) throws MessagingException, IOException;
}
