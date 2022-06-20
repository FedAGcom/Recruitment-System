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
import com.google.api.services.forms.v1.model.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.fedag.recruitmentSystem.util.GoogleFormConstants.*;
@Service
public class GoogleFormServiceImpl implements GoogleFormService {

    private final QuestionServiceImpl questionService;

    Forms formsService = new Forms.Builder(GoogleNetHttpTransport.newTrustedTransport(),
            JSON_FACTORY, getCredentials(GoogleNetHttpTransport.newTrustedTransport()))
            .setApplicationName(APPLICATION_NAME).build();

    Drive driveService = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                        JSON_FACTORY, getCredentials(GoogleNetHttpTransport.newTrustedTransport()))
                  .setApplicationName(APPLICATION_NAME).build();
    public GoogleFormServiceImpl(QuestionServiceImpl questionService) throws GeneralSecurityException, IOException {
        this.questionService = questionService;
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
    public String createForm(String language) {
        Form form = new Form();
        String token = getCredentials(GoogleNetHttpTransport.newTrustedTransport()).getAccessToken();
        form.setInfo(new Info());
        form.getInfo().setTitle("FedAG test " + language);
        form = formsService.forms().create(form)
                .setAccessToken(token)
                .execute();
        String formId = form.getFormId();

        transformInQuiz(formId, token);

        List<com.fedag.recruitmentSystem.model.Question> questions = questionService.searchQuestionsByTitle(language);

        for(com.fedag.recruitmentSystem.model.Question question : questions){
            addItemToQuiz(question.getQuestion(),
                    Arrays.stream(question.getAnswer().split(", ")).collect(Collectors.toList()),
                    question.getCorrect(), formId, token);
        }


        return "https://docs.google.com/forms/d/" + formId;
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

    private void addItemToQuiz(
            String questionText,
            List<String> answers,
            String correctAnswer,
            String formId, String token) throws IOException {

        BatchUpdateFormRequest batchRequest = new BatchUpdateFormRequest();
        Request request = new Request();

        Item item = new Item();
        item.setTitle(questionText);

        item.setQuestionItem(new QuestionItem());
        Question question = new Question();
        question.setRequired(true);
        question.setChoiceQuestion(new ChoiceQuestion());
        question.getChoiceQuestion().setType("RADIO");

        List<Option> options = new ArrayList<>();
        for (String answer : answers) {
            Option option = new Option();
            option.setValue(answer);
            options.add(option);
        }
        question.getChoiceQuestion().setOptions(options);

        Grading grading = new Grading();
        grading.setPointValue(1);
        grading.setCorrectAnswers(new CorrectAnswers());
        grading.getCorrectAnswers().setAnswers(new ArrayList<>());
        grading.getCorrectAnswers().getAnswers().add(new CorrectAnswer());
        grading.getCorrectAnswers().getAnswers().get(0).setValue(correctAnswer);
        Feedback whenRight = new Feedback();
        whenRight.setText("Yeah!");
        Feedback whenWrong = new Feedback();
        whenWrong.setText("Wrong Answer");
        grading.setWhenRight(whenRight);
        grading.setWhenWrong(whenWrong);
        question.setGrading(grading);

        item.getQuestionItem().setQuestion(question);
        request.setCreateItem(new CreateItemRequest());
        request.getCreateItem().setItem(item);
        request.getCreateItem().setLocation(new Location());
        request.getCreateItem().getLocation().setIndex(0);

        batchRequest.setRequests(Collections.singletonList(request));

        formsService.forms().batchUpdate(formId, batchRequest)
                .setAccessToken(token).execute();
    }


    private void transformInQuiz(String formId, String token) throws IOException {
        BatchUpdateFormRequest batchRequest = new BatchUpdateFormRequest();
        Request request = new Request();
        request.setUpdateSettings(new UpdateSettingsRequest());
        request.getUpdateSettings().setSettings(new FormSettings());
        request.getUpdateSettings().getSettings().setQuizSettings(new QuizSettings());
        request.getUpdateSettings().getSettings().getQuizSettings().setIsQuiz(true);
        request.getUpdateSettings().setUpdateMask("quizSettings.isQuiz");
        batchRequest.setRequests(Collections.singletonList(request));
        formsService.forms().batchUpdate(formId, batchRequest)
                .setAccessToken(token).execute();
    }



}
