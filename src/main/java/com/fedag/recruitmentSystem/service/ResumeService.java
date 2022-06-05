package com.fedag.recruitmentSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fedag.recruitmentSystem.dto.ExperienceResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResumeService<T> extends AbstractServiceInterface<T> {

  List<T> getAllResumes();

  Page<T> getAllResumes(Pageable pageable);

  List<T> findByDateCreated(LocalDateTime dateCreated);

  Page<T> findByTextFilter(String text, Pageable pageable);

  List<ExperienceResponse> listExperiencesByResume(Long resumeId) throws ObjectNotFoundException;
}
