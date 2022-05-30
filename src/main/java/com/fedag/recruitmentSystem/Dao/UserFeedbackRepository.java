package com.fedag.recruitmentSystem.Dao;

import com.fedag.recruitmentSystem.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Long> {
}
