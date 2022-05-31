package com.fedag.recruitmentSystem.dao;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyResponseDao extends JpaRepository<VacancyResponse, Long> {
}
