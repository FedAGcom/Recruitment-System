package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyFeedbackService<T, S> extends AbstractServiceInterface<T, S> {

  List<T> getAllCompanyFeedbacks();

  Page<T> getAllCompanyFeedbacks(Pageable pageable);
}
