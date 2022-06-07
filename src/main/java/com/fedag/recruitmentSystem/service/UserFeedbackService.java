package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFeedbackService<T, S> extends AbstractServiceInterface<T, S> {

  List<T> getAllUserFeedbacks();

  Page<T> getAllUserFeedbacks(Pageable pageable);
}
