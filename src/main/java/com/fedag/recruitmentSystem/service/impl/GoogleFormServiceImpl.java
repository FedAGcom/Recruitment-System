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
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.model.Form;
import com.google.api.services.forms.v1.model.Info;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.fedag.recruitmentSystem.util.GoogleFormConstants.*;

public class GoogleFormServiceImpl implements GoogleFormService {

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
    }

    @SneakyThrows
    @Override
    public void createForm() {
        Forms formsService = new Forms.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY, getCredentials(GoogleNetHttpTransport.newTrustedTransport()))
                .setApplicationName(APPLICATION_NAME).build();
        Form form = new Form();
        form.setInfo(new Info());
        form.getInfo().setTitle("New Form Quiz Created from Java");
        form = formsService.forms().create(form)
                .setAccessToken(String.valueOf(getCredentials(GoogleNetHttpTransport.newTrustedTransport())))
                .execute();

    }


}
