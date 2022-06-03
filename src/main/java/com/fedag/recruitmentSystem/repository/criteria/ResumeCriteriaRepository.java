package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Resume;

import java.time.LocalDateTime;
import java.util.List;

public interface ResumeCriteriaRepository {
    List<Resume> findByDateCreated(LocalDateTime dateCreated);
}
