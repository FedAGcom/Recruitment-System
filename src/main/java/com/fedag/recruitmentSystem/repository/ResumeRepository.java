package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fedag.recruitmentSystem.repository.criteria.ResumeCriteriaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>, ResumeCriteriaRepository {

    Page<Resume> findAllByResumeName(String name, Pageable pageable);
}
