package com.fedag.recruitmentSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import com.fedag.recruitmentSystem.dto.ExperienceResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ResumeService<T, S> extends AbstractServiceInterface<T, S> {

    List<T> getAllResumes();

    Page<T> getAllResumes(Pageable pageable);

    Page<T> getAllResumesByPosition(String position, Pageable pageable);

    Page<T> findByTextFilter(String text, Pageable pageable);

    Page<T> findByPosition(String position, Pageable pageable);

    List<T> findByDateCreated(LocalDateTime dateCreated);

    List<ExperienceResponse> listExperiencesByResume(Long resumeId) throws ObjectNotFoundException;
}
