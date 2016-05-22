package services.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import services.GmailService;

public class GmailClient extends GoogleClient implements GmailService {

	@Override
	public Gmail getClient(List<String> scopes) throws IOException {
		Credential credential = authorize(scopes, ".credentials/gmail.json");
		return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Create a Message from an email
	 *
	 * @param email
	 *            Email to be set to raw of message
	 * @return Message containing base64url encoded email.
	 * @throws IOException
	 * @throws MessagingException
	 */

	private Message createMessageWithEmail(MimeMessage email) throws MessagingException, IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		email.writeTo(bytes);
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes.toByteArray());
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;

	}

	/**
	 * Create a MimeMessage using the parameters provided.
	 *
	 * @param to
	 *            Email address of the receiver.
	 * @param from
	 *            Email address of the sender, the mailbox account.
	 * @param subject
	 *            Subject of the email.
	 * @param bodyText
	 *            Body text of the email.
	 * @param fileDir
	 *            Path to the directory containing attachment.
	 * @param filename
	 *            Name of file to be attached.
	 * @return MimeMessage to be used to send email.
	 * @throws Exception
	 */

	@Override
	public MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText,
			File attachment,String mimeType) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);
		InternetAddress tAddress = new InternetAddress(to);
		InternetAddress fAddress = new InternetAddress(from);

		email.setFrom(fAddress);
		email.addRecipient(javax.mail.Message.RecipientType.TO, tAddress);
		email.setSubject(subject);

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(bodyText, "text/plain");
		mimeBodyPart.setHeader("Content-Type", "text/plain; charset=\"UTF-8\"");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		mimeBodyPart = new MimeBodyPart();

		DataSource source = new FileDataSource (attachment);
		DataHandler dh = new DataHandler(source);
		
		mimeBodyPart.setDataHandler(dh);
		mimeBodyPart.setFileName(attachment.getName());

		mimeBodyPart.setHeader("Content-Type", mimeType + "; name=\"" + attachment.getName() + "\"");
		mimeBodyPart.setHeader("Content-Transfer-Encoding", "base64");

		multipart.addBodyPart(mimeBodyPart);

		email.setContent(multipart);

		return email;
	}

	/**
	 * Send an email from the user's mailbox to its recipient.
	 *
	 * @param service
	 *            Authorized Gmail API instance.
	 * @param userId
	 *            User's email address. The special value "me" can be used to
	 *            indicate the authenticated user.
	 * @param email
	 *            Email to be sent.
	 * @throws MessagingException
	 * @throws IOException
	 */

	@Override
	public void sendMessage(Gmail service, String userId, MimeMessage email) throws MessagingException, IOException {
		Message message = createMessageWithEmail(email);
		message = service.users().messages().send(userId, message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString());

	}

}
