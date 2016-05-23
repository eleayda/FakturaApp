package services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.google.api.services.gmail.Gmail;

public interface GmailService extends GoogleService{

	

	void sendMessage(Gmail service, String userId, MimeMessage email) throws MessagingException, IOException;

	MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText,
			File attachment, String mimeType) throws Exception;
	Gmail getClient(List<String> scopes) throws IOException;
}
