package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
