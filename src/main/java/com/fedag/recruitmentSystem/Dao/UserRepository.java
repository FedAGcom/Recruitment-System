package com.fedag.recruitmentSystem.Dao;

import com.fedag.recruitmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
