package com.fedag.recruitmentSystem.controller;
import com.fedag.recruitmentSystem.service.impl.GoogleFormServiceImpl;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.drive.model.PermissionList;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.FormsScopes;
import com.google.api.services.forms.v1.model.*;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/forms")
public class FormController {

    private final GoogleFormServiceImpl googleFormService;

    @GetMapping
    public String createForm() {
        return googleFormService.createForm();
    }






//    private static final String APPLICATION_NAME = "google-form-api-project";
//    private static Drive driveService;
//    private static Forms formsService;
//
//    static {
//
//        try {
//
//            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//
//            driveService = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
//                    jsonFactory, null)
//                    .setApplicationName(APPLICATION_NAME).build();
//
//            formsService = new Forms.Builder(GoogleNetHttpTransport.newTrustedTransport(),
//                    jsonFactory, null)
//                    .setApplicationName(APPLICATION_NAME).build();
//
//        } catch (GeneralSecurityException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws IOException, GeneralSecurityException {
//
//        String token = getAccessToken();
//
//        String formId = createNewForm(token);
//        System.out.println(formId);
//
//        publishForm(formId, token);
//
//        transformInQuiz(formId, token);
//
//        addItemToQuiz(
//                "Which of these singers was not a member of Destiny's Child?",
//                Arrays.asList("Kelly Rowland", "Beyoncè", "Rihanna", "Michelle Williams"),
//                "Rihanna",
//                formId,
//                token
//        );
//
//        readResponses("1zeE_BsrhtD3Swii0bESzl11RrdBbkiO8IT1vUNrySEI", token);
//
//    }
//
//    private static void readResponses(String formId, String token) throws IOException {
//        ListFormResponsesResponse response = formsService.forms().responses().list(formId).setOauthToken(token).execute();
//        System.out.println(response.toPrettyString());
//    }
//
//    private static void addItemToQuiz(
//            String questionText,
//            List<String> answers,
//            String correctAnswer,
//            String formId, String token) throws IOException {
//
//        BatchUpdateFormRequest batchRequest = new BatchUpdateFormRequest();
//        Request request = new Request();
//
//        Item item = new Item();
//        item.setTitle(questionText);
//
//        item.setQuestionItem(new QuestionItem());
//        Question question = new Question();
//        question.setRequired(true);
//        question.setChoiceQuestion(new ChoiceQuestion());
//        question.getChoiceQuestion().setType("RADIO");
//
//        List<Option> options = new ArrayList<>();
//        for (String answer : answers) {
//            Option option = new Option();
//            option.setValue(answer);
//            options.add(option);
//        }
//        question.getChoiceQuestion().setOptions(options);
//
//        Grading grading = new Grading();
//        grading.setPointValue(2);
//        grading.setCorrectAnswers(new CorrectAnswers());
//        grading.getCorrectAnswers().setAnswers(new ArrayList<>());
//        grading.getCorrectAnswers().getAnswers().add(new CorrectAnswer());
//        grading.getCorrectAnswers().getAnswers().get(0).setValue(correctAnswer);
//        Feedback whenRight = new Feedback();
//        whenRight.setText("Yeah!");
//        Feedback whenWrong = new Feedback();
//        whenWrong.setText("Wrong Answer");
//        grading.setWhenRight(whenRight);
//        grading.setWhenWrong(whenWrong);
//        question.setGrading(grading);
//
//        item.getQuestionItem().setQuestion(question);
//        request.setCreateItem(new CreateItemRequest());
//        request.getCreateItem().setItem(item);
//        request.getCreateItem().setLocation(new Location());
//        request.getCreateItem().getLocation().setIndex(0);
//
//        batchRequest.setRequests(Collections.singletonList(request));
//
//        formsService.forms().batchUpdate(formId, batchRequest)
//                .setAccessToken(token).execute();
//    }
//
//    private static void transformInQuiz(String formId, String token) throws IOException {
//        BatchUpdateFormRequest batchRequest = new BatchUpdateFormRequest();
//        Request request = new Request();
//        request.setUpdateSettings(new UpdateSettingsRequest());
//        request.getUpdateSettings().setSettings(new FormSettings());
//        request.getUpdateSettings().getSettings().setQuizSettings(new QuizSettings());
//        request.getUpdateSettings().getSettings().getQuizSettings().setIsQuiz(true);
//        request.getUpdateSettings().setUpdateMask("quizSettings.isQuiz");
//        batchRequest.setRequests(Collections.singletonList(request));
//        formsService.forms().batchUpdate(formId, batchRequest)
//                .setAccessToken(token).execute();
//    }
//
//    private static Form getForm(String formId, String token) throws IOException {
//        return formsService.forms().get(formId).setAccessToken(token).execute();
//    }
//
//    private static String createNewForm(String token) throws IOException {
//        Form form = new Form();
//        form.setInfo(new Info());
//        form.getInfo().setTitle("New Form Quiz Created from Java");
//        form = formsService.forms().create(form)
//                .setAccessToken(token)
//                .execute();
//        return form.getFormId();
//    }
//
//    public static boolean publishForm(String formId, String token) throws GeneralSecurityException, IOException {
//
//        PermissionList list = driveService.permissions().list(formId).setOauthToken(token).execute();
//
//        if (list.getPermissions().stream().filter((it) -> it.getRole().equals("reader")).findAny().isEmpty()) {
//            Permission body = new Permission();
//            body.setRole("reader");
//            body.setType("anyone");
//            driveService.permissions().create(formId, body).setOauthToken(token).execute();
//            return true;
//        }
//
//        return false;
//    }
//
//    public static String getAccessToken() throws IOException {
//        GoogleCredentials credential = GoogleCredentials.fromStream(Objects.requireNonNull(
//                FormController.class.getResourceAsStream("cred.json"))).createScoped(FormsScopes.all());
//        return credential.getAccessToken() != null ?
//                credential.getAccessToken().getTokenValue() :
//                credential.refreshAccessToken().getTokenValue();
//    }

}