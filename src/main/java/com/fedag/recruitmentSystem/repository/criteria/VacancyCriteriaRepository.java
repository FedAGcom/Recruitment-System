package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VacancyCriteriaRepository {
    List<Vacancy> findByDateCreated();
}
