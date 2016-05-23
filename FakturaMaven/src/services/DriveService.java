package services;

import java.io.IOException;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public interface DriveService extends GoogleService {

	File insertFile(Drive service, String title, String description, String parentId, String mimeType, String filename);

	Drive getClient(List<String> scopes) throws IOException;
}