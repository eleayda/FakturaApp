package services;

import java.io.IOException;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public interface DriveService {

//	/**
//	 * Build and return an authorized Drive client service.
//	 * @return an authorized Drive client service
//	 * @throws IOException
//	 */
//	Drive getDriveService() throws IOException;

	/**
	   * Insert new file.
	   *
	   * @param service Drive API service instance.
	   * @param title Title of the file to insert, including the extension.
	   * @param description Description of the file to insert.
	   * @param parentId Optional parent folder's ID.
	   * @param mimeType MIME type of the file to insert.
	   * @param filename Filename of the file to insert.
	   * @return Inserted file metadata if successful, {@code null} otherwise.
	   */
	File insertFile(Drive service, String title, String description, String parentId, String mimeType, String filename);

}