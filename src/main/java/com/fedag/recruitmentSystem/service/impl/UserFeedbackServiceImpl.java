package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.UserFeedbackRequest;
import com.fedag.recruitmentSystem.dto.request.UserFeedbackUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserFeedbackResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.UserFeedbackMapper;
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
public class UserFeedbackServiceImpl implements UserFeedbackService<UserFeedbackResponse, UserFeedbackRequest, UserFeedbackUpdateRequest> {

  private final UserFeedbackRepository userFeedbackRepository;
  private final UserFeedbackMapper userFeedbackMapper;

  @Override
  public List<UserFeedbackResponse> getAllUserFeedbacks() {
    return userFeedbackMapper.modelToDto(userFeedbackRepository.findAll());
  }

  @Override
  public Page<UserFeedbackResponse> getAllUserFeedbacks(Pageable pageable) {
    return userFeedbackMapper.modelToDto(userFeedbackRepository.findAll(pageable));
  }

  @Override
  public UserFeedbackResponse findById(Long id) {
    UserFeedback userFeedback = userFeedbackRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("UserFeedback with id: " + id + " not found")
        );
    return userFeedbackMapper.modelToDto(userFeedback);
  }

  @Override
  public void save(UserFeedbackRequest element) {
    UserFeedback userFeedback = userFeedbackMapper.dtoToModel(element);
    userFeedbackRepository.save(userFeedback);
  }

  @Override
  public void update(UserFeedbackUpdateRequest element) {
    UserFeedback userFeedback = userFeedbackMapper.dtoToModel(element);
    userFeedbackRepository.save(userFeedback);
  }

  @Override
  public void deleteById(Long id) {
    userFeedbackRepository.deleteById(id);
  }
}
