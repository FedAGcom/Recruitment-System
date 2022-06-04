package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

public interface ResumeCriteriaRepository {
    List<Resume> findByDateCreated(LocalDateTime dateCreated);

@Repository
public interface ResumeCriteriaRepository {
    Page<Resume> findByTextFilter(String text, Pageable pageable);
}
