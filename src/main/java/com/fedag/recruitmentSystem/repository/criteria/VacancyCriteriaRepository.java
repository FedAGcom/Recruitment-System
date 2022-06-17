package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Vacancy;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyCriteriaRepository {

  List<Vacancy> findByDateCreated();
}
