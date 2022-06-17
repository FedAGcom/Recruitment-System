package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.criteria.VacancyCriteriaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VacancyCriteriaRepositoryImpl implements VacancyCriteriaRepository {

  private final EntityManager entityManager;

  @Override
  public List<Vacancy> findByDateCreated() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Vacancy> cr = cb.createQuery(Vacancy.class);

    Root<Vacancy> root = cr.from(Vacancy.class);

    cr.select(root).orderBy(cb.desc(root.get("dateCreated")));

    TypedQuery<Vacancy> query = entityManager.createQuery(cr);

    return query.getResultList();
  }
}
