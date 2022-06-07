package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCriteriaRepository {
  List<User> findByEntranceExamScore(int score);
  List<User> findByStars(byte stars);
  List<User> findByExperience(int max);

}
