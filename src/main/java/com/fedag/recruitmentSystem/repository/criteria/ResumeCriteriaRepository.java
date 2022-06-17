package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Resume;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeCriteriaRepository {

  List<Resume> findByDateCreated();

  Page<Resume> findByTextFilter(String text, Pageable pageable);

  Page<Resume> findByPosition(String position, Pageable pageable);
}

