package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Vacancy;

import java.time.LocalDateTime;
import java.util.List;

public interface VacancyCriteriaRepository {
    List<Vacancy> findByDateCreated(LocalDateTime dateCreated);
}
