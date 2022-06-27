package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllExams();

    Page<T> getAllExams(Pageable pageable);
}