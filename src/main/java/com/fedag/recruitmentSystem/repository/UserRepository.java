package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
