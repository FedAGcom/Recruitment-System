package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.criteria.VacancyCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

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
