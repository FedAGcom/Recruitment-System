package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.*;
import com.fedag.recruitmentSystem.repository.criteria.UserCriteriaRepository;
import java.util.ArrayList;
import java.beans.Expression;
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
public class UserCriteriaRepositoryImpl implements UserCriteriaRepository {

  private final EntityManager entityManager;

  @Override
  public List<User> findByEntranceExamScore(int score) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cr = cb.createQuery(User.class);

    Root<User> root = cr.from(User.class);
    Join<Exam, User> join = root.join("exam");

    cr.select(root).
        where(cb.ge(join.get("score"), score)).
        orderBy(cb.desc(join.get("score")));

    TypedQuery<User> query = entityManager.createQuery(cr);
    List<User> users = query.getResultList();

    return users;
  }

  @Override
  public List<User> findByStars(byte stars) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cr = cb.createQuery(User.class);

    Root<User> root = cr.from(User.class);
    Join<UserFeedback, User> join = root.join("userFeedbackList");

    cr.select(root).
            where(cb.ge(join.get("stars"), stars)).
            orderBy(cb.desc(cb.avg(join.get("stars")))).groupBy(root);

    TypedQuery<User> query = entityManager.createQuery(cr);
    return query.getResultList();
  }

  @Override
  public List<User> findByExperience(int max) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> cr = cb.createQuery(User.class);

    Root<User> root = cr.from(User.class);
    Join<Resume, User> join1 = root.join("resumeList");
    Join<Experience, Resume> join = join1.join("experiences");

    if(max == 0) {
      cr.select(root).orderBy(cb.desc(cb.sum(cb.diff(join.get("endDate"), join.get("startDate"))))).groupBy(root);
    } else {
      cr.select(root).orderBy(cb.desc(cb.max(cb.diff(join.get("endDate"), join.get("startDate"))))).groupBy(root.get("id"));
    }
    TypedQuery<User> query = entityManager.createQuery(cr);
    return query.getResultList();
  }
}
