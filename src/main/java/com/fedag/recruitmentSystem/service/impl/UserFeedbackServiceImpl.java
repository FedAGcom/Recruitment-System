package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.repository.UserFeedbackRepository;
import com.fedag.recruitmentSystem.service.UserFeedbackService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFeedbackServiceImpl implements UserFeedbackService<UserFeedback> {

  private final UserFeedbackRepository userFeedbackRepository;

  @Override
  public List<UserFeedback> getAllUserFeedbacks() {
    return userFeedbackRepository.findAll();
  }

  @Override
  public Page<UserFeedback> getAllUserFeedbacks(Pageable pageable) {
    return userFeedbackRepository.findAll(pageable);
  }

  @Override
  public UserFeedback findById(Long id) {
    return userFeedbackRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("UserFeedback with id: " + id + " not found")
        );
  }

  @Override
  public void save(UserFeedback element) {
    userFeedbackRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    userFeedbackRepository.deleteById(id);
  }
}
