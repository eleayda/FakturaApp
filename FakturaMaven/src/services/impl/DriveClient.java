package services.impl;

import java.io.IOException;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import services.DriveService;

public class DriveClient extends GoogleClient implements DriveService {

	@Override
	public Drive getClient(List<String> scopes) throws IOException {
		Credential credential = authorize(scopes, ".credentials/drive.json");
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	/**
	 * Insert new file.
	 *
	 * @param service
	 *            Drive API service instance.
	 * @param title
	 *            Title of the file to insert, including the extension.
	 * @param description
	 *            Description of the file to insert.
	 * @param parentId
	 *            Optional parent folder's ID.
	 * @param mimeType
	 *            MIME type of the file to insert.
	 * @param filename
	 *            Filename of the file to insert.
	 * @return Inserted file metadata if successful, {@code null} otherwise.
	 */
	@Override
	public File insertFile(Drive service, String title, String description, String parentId, String mimeType,
			String filename) {

		// File's metadata.
		File body = new File();
		body.setTitle(title);
		body.setDescription(description);
		body.setMimeType(mimeType);

		// TODO implement method for creation of dedicated directory

		// // Set the parent folder.
		// if (parentId != null && parentId.length() > 0) {
		// body.setParents(
		// Arrays.asList(new ParentReference().setId(parentId)));
		// }

		// File's content.
		java.io.File fileContent = new java.io.File(filename);
		FileContent mediaContent = new FileContent(mimeType, fileContent);
		try {
			File file = service.files().insert(body, mediaContent).execute();

			// Uncomment the following line to print the File ID.
			System.out.println("File ID: " + file.getId());

			return file;
		} catch (IOException e) {
			System.out.println("An error occured: " + e);
			return null;
		}
	}

}
