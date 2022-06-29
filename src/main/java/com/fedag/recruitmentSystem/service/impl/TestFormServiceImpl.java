package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.model.Question;
import com.fedag.recruitmentSystem.model.TestForm;
import com.fedag.recruitmentSystem.service.TestFormService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class TestFormServiceImpl implements TestFormService {

    private final QuestionServiceImpl questionService;

    @Override
    public TestForm createForm(String language) {
        TestForm form = new TestForm();
        List<String> questionsList = new ArrayList<>();
        List<String> correct = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        List<Question> questions = questionService.searchQuestionsByTitle(language);
        for (Question question : questions){
            questionsList.add(question.getQuestion());
            answers.add(question.getAnswer());
            correct.add(question.getCorrect());
        }
        form.setAnswers(answers);
        form.setCorrect(correct);
        form.setQuestions(questionsList);
        form.setLanguage(language);
        form.setStart(LocalDateTime.now());
        form.setEnd(LocalDateTime.now().plusMinutes(30L));
        return form;
    }

}
