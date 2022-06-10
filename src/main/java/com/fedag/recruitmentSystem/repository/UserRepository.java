package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.criteria.UserCriteriaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCriteriaRepository{
   Optional<User> findByEmail(String email);

//   Boolean existsByEmail(String email);
}
