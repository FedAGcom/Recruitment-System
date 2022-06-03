package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.repository.criteria.ResumeCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeCriteriaRepositoryImpl implements ResumeCriteriaRepository {

    private final EntityManager entityManager;

    @Override
    public List<Resume> findByDateCreated(LocalDateTime dateCreated) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resume> cr = cb.createQuery(Resume.class);

        Root<Resume> root = cr.from(Resume.class);

        cr.select(root).orderBy(cb.desc(root.get("dateCreated")));

        TypedQuery<Resume> query = entityManager.createQuery(cr);

        return query.getResultList();
    }
}
