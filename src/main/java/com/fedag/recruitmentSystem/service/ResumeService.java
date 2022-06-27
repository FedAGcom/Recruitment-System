package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.response.admin_response.ExperienceResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ExperienceResponseForUser;
import com.fedag.recruitmentSystem.dto.response.user_response.ResumeResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllResumes();

    Page<T> getAllResumes(Pageable pageable);

    Page<T> getAllResumesByPosition(String position, Pageable pageable);

    Page<T> findByTextFilter(String text, Pageable pageable);

    List<T> findByDateCreated();

    List<ExperienceResponseForAdmin> listExperiencesByResume(Long resumeId) throws ObjectNotFoundException;

    Page<ResumeResponseForUser> getAllResumesByPositionForUser(String position, Pageable pageable);

    Page<ResumeResponseForUser> getAllResumesForUser(Pageable pageable);

    Page<ResumeResponseForUser> findByTextFilterForUser(String text, Pageable pageable);

    ResumeResponseForUser findByIdForUser(Long id);

    List<ExperienceResponseForUser> listExperiencesByResumeForUser(Long id);

    List<ResumeResponseForUser> findByDateCreatedForUser();
}
