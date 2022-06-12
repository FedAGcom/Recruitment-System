package com.fedag.recruitmentSystem.service.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author koilng
 * @created 12/06/2022 - 14:14
 */
@Service
public class GoogleApi {

  private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart1";
  private static final JsonFactory JSON_FACTORY = new GsonFactory();
  private static final String TOKENS_PATH = "tokens/";
  private static final String TOKENS_PATH_JAR = "/tmp/tokens/";
  private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
  private static final String CREDENTIALS_FILE_PATH = "/credential.json";

  @SneakyThrows
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) {
    // Load client secrets.
    InputStream in = GoogleApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    URL dirURL = GoogleApi.class.getClassLoader().getResource(TOKENS_PATH);
    File googleToken = null;
    if (dirURL != null && dirURL.getProtocol().equals("file")) {
      /* A file path: easy enough */
      googleToken = new File(dirURL.toURI());
    } else {
      /*
       * In case of a jar file, we can't actually find a directory.
       * Have to assume the same jar as clazz.
       */
      googleToken = new File(TOKENS_PATH_JAR);
    }
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(googleToken))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(9990).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }

  public static Calendar getCalendar() throws IOException, GeneralSecurityException {
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();
  }
}
