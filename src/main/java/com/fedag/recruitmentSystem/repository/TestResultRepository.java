package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
