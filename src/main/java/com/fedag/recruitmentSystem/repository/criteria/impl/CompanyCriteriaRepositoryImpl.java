package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.repository.criteria.CompanyCriteriaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyCriteriaRepositoryImpl implements CompanyCriteriaRepository {

  private final EntityManager entityManager;

  @Override
  public List<Company> findByStars(byte stars) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Company> cr = cb.createQuery(Company.class);

    Root<Company> root = cr.from(Company.class);
    Join<CompanyFeedBack, Company> join = root.join("companyFeedBackList");

    cr.select(root).
        where(cb.ge(join.get("stars"), stars)).
        orderBy(cb.desc(cb.avg(join.get("stars")))).groupBy(root);
    ;

    TypedQuery<Company> query = entityManager.createQuery(cr);
    return query.getResultList();
  }
}
