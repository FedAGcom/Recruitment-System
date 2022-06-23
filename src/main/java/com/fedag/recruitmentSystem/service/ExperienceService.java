package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.response.admin_response.ExperienceResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ExperienceResponseForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExperienceService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllExperience();

    Page<T> getAllExperience(Pageable pageable);

    ExperienceResponseForUser findByIdForUser(Long id);

    Page<ExperienceResponseForAdmin> getAllExperienceByUserId(Pageable pageable, Long id);

    Page<ExperienceResponseForUser> getAllExperienceByUserIdForUser(Pageable pageable, Long id);
}
