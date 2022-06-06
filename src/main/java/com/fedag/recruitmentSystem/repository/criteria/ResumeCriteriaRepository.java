package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResumeCriteriaRepository {
    List<Resume> findByDateCreated(LocalDateTime dateCreated);

    Page<Resume> findByTextFilter(String text, Pageable pageable);

    Page<Resume> findByPosition(String position, Pageable pageable);
}
