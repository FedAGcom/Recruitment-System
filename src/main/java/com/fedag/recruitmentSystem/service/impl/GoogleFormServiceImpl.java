package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.service.GoogleFormService;
import com.fedag.recruitmentSystem.util.GoogleFormConstants;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.drive.model.PermissionList;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.model.Form;
import com.google.api.services.forms.v1.model.Info;
import lombok.SneakyThrows;

import java.io.*;
import java.security.GeneralSecurityException;

import static com.fedag.recruitmentSystem.util.GoogleFormConstants.*;

public class GoogleFormServiceImpl implements GoogleFormService {

    Forms formsService = new Forms.Builder(GoogleNetHttpTransport.newTrustedTransport(),
            JSON_FACTORY, getCredentials(GoogleNetHttpTransport.newTrustedTransport()))
            .setApplicationName(APPLICATION_NAME).build();

    Drive driveService = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                        JSON_FACTORY, getCredentials(GoogleNetHttpTransport.newTrustedTransport()))
                  .setApplicationName(APPLICATION_NAME).build();
    public GoogleFormServiceImpl() throws GeneralSecurityException, IOException {
    }

    @SneakyThrows
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) {
        InputStream in = GoogleFormConstants.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setCallbackPath("/api/callback")
                .setPort(8000)
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;

        //https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/forms.body&access_type=offline&include_granted_scopes=true&redirect_uri=http://localhost:8000/api/callback&response_type=code&client_id=885907892267-fg6c0666r45710gm2qggv4t2h7jqufdo.apps.googleusercontent.com
    }

    @SneakyThrows
    @Override
    public void createForm() {
        Form form = new Form();
        form.setInfo(new Info());
        form.getInfo().setTitle("New Form Quiz Created from Java");
        form = formsService.forms().create(form)
                .setAccessToken(getCredentials(GoogleNetHttpTransport.newTrustedTransport()).getAccessToken())
                .execute();
        String formId = form.getFormId();

        publishForm(formId, getCredentials(GoogleNetHttpTransport.newTrustedTransport()).getAccessToken());
        System.out.println(formId);
    }

    public boolean publishForm(String formId, String token) throws GeneralSecurityException, IOException {

        PermissionList list = driveService.permissions().list(formId).setOauthToken(token).execute();

        if (list.getPermissions().stream().filter((it) -> it.getRole().equals("reader")).findAny().isEmpty()) {
            Permission body = new Permission();
            body.setRole("reader");
            body.setType("anyone");
            driveService.permissions().create(formId, body).setOauthToken(token).execute();
            return true;
        }

        return false;
    }

        private Form getForm(String formId, String token) throws IOException {
        return formsService.forms().get(formId).setAccessToken(token).execute();
    }
}
