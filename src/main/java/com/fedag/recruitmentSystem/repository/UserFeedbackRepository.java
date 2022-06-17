package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Long> {

}
