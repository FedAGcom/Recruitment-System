package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.criteria.UserCriteriaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, UserCriteriaRepository, JpaSpecificationExecutor<User> {
}
