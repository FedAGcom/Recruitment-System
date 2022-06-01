package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
