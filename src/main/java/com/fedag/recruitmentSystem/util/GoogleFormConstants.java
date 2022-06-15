package com.fedag.recruitmentSystem.util;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.FormsScopes;

import java.util.List;

import static java.util.Collections.singletonList;

public interface GoogleFormConstants {

    /**
     * Application name.
     */
    String APPLICATION_NAME = "Recruitment System";

    /**
     * Global instance of the JSON factory.
     */
    JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Directory to store authorization tokens for this application.
     */
    String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by start of calendar.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    List<String> SCOPES = singletonList("https://www.googleapis.com/auth/forms");
    String CREDENTIALS_FILE_PATH = "/cred.json";
}
