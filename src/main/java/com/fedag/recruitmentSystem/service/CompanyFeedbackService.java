package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyFeedbackService<T> extends AbstractServiceInterface<T> {

  List<T> getAllCompanyFeedbacks();

  Page<T> getAllCompanyFeedbacks(Pageable pageable);
}
