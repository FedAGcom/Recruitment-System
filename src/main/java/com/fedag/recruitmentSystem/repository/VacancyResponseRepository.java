package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyResponseRepository extends JpaRepository<VacancyResponse, Long> {

}
