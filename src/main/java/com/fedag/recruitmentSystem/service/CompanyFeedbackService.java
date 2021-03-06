package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.response.user_response.CompanyFeedbackResponseForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyFeedbackService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllCompanyFeedbacks();

    Page<T> getAllCompanyFeedbacks(Pageable pageable);

    Page<CompanyFeedbackResponseForUser> getAllCompanyFeedbacksForUser(Pageable pageable);

    CompanyFeedbackResponseForUser findByIdForUser(Long id);
}
