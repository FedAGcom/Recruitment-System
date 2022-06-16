package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    void addQuestion(String id, String title, String question, String answer, String correct);

    void updateQuestion(String id, String title, String question, String answer, String correct);

    List<Question> searchQuestionsByTitle(String title);

    void deleteQuestion();

}
