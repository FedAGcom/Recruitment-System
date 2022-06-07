package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.repository.criteria.ResumeCriteriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface ResumeRepository extends JpaRepository<Resume, Long>, ResumeCriteriaRepository, JpaSpecificationExecutor<Resume> {

}
