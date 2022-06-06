package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.domain.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

}
