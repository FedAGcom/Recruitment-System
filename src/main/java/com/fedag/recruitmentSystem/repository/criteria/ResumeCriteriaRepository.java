package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeCriteriaRepository {
    Page<Resume> findByTextFilter(String text, Pageable pageable);
}
