package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface QuestionService<T, S, U> {

    T findById(String id);

    void save(S element);

    void update(U element) throws IOException;

    void deleteById(String id);

    List<T> getAllQuestions();

    Page<T> getAllQuestions(Pageable pageable);
}
