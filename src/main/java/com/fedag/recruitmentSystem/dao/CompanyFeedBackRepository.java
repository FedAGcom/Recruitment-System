package com.fedag.recruitmentSystem.dao;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyFeedBackRepository extends JpaRepository<CompanyFeedBack, Long> {
}
