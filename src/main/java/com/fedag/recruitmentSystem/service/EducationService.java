package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.response.user_response.EducationResponseForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EducationService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllEducation();

    Page<T> getAllEducation(Pageable pageable);

    EducationResponseForUser findByIdForUser(Long id);
}
