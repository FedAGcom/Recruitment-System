package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamService<T> extends AbstractServiceInterface<T> {

  List<T> getAllExams();

  Page<T> getAllExams(Pageable pageable);
}