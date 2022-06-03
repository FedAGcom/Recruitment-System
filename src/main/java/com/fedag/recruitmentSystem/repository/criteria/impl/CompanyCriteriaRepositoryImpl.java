package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.repository.criteria.CompanyCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CompanyCriteriaRepositoryImpl implements CompanyCriteriaRepository {

    private final EntityManager entityManager;

    @Autowired
    public CompanyCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Company> findByStars(byte stars) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> cr = cb.createQuery(Company.class);

        Root<Company> root = cr.from(Company.class);
        Join<CompanyFeedBack, Company> join = root.join("companyFeedBackList");

        cr.select(root).
                where(cb.ge(join.get("stars"), stars)).
                orderBy(cb.desc(join.get("stars")));

        TypedQuery<Company> query = entityManager.createQuery(cr);
        return query.getResultList();
    }
}
