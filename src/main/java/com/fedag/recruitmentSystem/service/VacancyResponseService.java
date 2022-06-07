package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacancyResponseService<T, S, U> extends AbstractServiceInterface<T, S, U> {

  List<T> getAllVacanciesResponses();

  Page<T> getAllVacanciesResponses(Pageable pageable);
}
