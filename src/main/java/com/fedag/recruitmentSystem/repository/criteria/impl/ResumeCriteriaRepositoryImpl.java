package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Resume;

import com.fedag.recruitmentSystem.repository.criteria.ResumeCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<Resume> findByTextFilter(String text, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery
                .select(criteriaBuilder.count(countQuery.from(Resume.class)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        CriteriaQuery<Resume> criteriaQuery = criteriaBuilder.createQuery(Resume.class);
        Root<Resume> resume = criteriaQuery.from(Resume.class);
        criteriaQuery
                .select(resume)
                .distinct(false)
                .where(criteriaBuilder.like(resume.get("resumeName"), "%" + text + "%"));
        TypedQuery<Resume> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber*pageSize);
        query.setMaxResults(pageSize);
        List<Resume> res = query.getResultList();

        return new PageImpl<>(res, pageable, count);
    }

    @Override
    public Page<Resume> findByPosition(String position, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resume> cq = cb.createQuery(Resume.class);
        Root<Resume> root = cq.from(Resume.class);

        cq.select(root).where(cb.equal(root.get("resumeName"), position));
        TypedQuery<Resume> query = entityManager.createQuery(cq);

        List<Resume> resumeList = query.getResultList();

        return new PageImpl<>(resumeList,pageable,resumeList.size());
    }
}
