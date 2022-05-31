package com.fedag.recruitmentSystem.dao;

import com.fedag.recruitmentSystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message, Long> {
}
