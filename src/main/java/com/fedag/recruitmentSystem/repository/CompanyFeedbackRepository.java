package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyFeedbackRepository extends JpaRepository<CompanyFeedBack, Long> {
}
