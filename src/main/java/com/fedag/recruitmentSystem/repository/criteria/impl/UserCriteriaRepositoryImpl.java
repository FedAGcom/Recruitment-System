package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.repository.criteria.UserCriteriaRepository;
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
    return query.getResultList();

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
}
