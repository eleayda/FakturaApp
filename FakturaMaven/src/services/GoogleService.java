package services;

import java.io.IOException;
import java.util.List;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;

public interface GoogleService {
	AbstractGoogleJsonClient getClient(List<String> scopes) throws IOException;
}
