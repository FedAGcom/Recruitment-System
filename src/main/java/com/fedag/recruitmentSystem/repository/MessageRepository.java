package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
