package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacancyService<T> extends AbstractServiceInterface<T> {

  List<T> getAllVacancies();

  Page<T> getAllVacancies(Pageable pageable);
}
