package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacancyResponseService<T> extends AbstractServiceInterface<T> {

  List<T> getAllVacanciesResponses();

  Page<T> getAllVacanciesResponses(Pageable pageable);
}
