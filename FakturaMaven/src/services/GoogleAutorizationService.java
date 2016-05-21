package services;

import java.io.IOException;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.gmail.Gmail;

public interface GoogleAutorizationService {

	Gmail getGmailService( List<String> scopes) throws IOException;


	Drive getDriveService( List<String> scopes) throws IOException;

}