package services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public interface DriveService {

	File insertFile(Drive service, String title, String description, String parentId, String mimeType, String filename);

}