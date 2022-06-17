package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.criteria.UserCriteriaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCriteriaRepository {

  Optional<User> findByEmail(String email);

  Optional<User> findByActivationCode(String code);

}
