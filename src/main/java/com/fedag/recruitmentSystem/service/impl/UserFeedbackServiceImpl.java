package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.UserFeedbackRequest;
import com.fedag.recruitmentSystem.dto.request.UserFeedbackUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.UserFeedbackResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.UserFeedbackResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.UserFeedbackMapper;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.repository.UserFeedbackRepository;
import com.fedag.recruitmentSystem.service.UserFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFeedbackServiceImpl implements UserFeedbackService<UserFeedbackResponseForAdmin,
        UserFeedbackRequest, UserFeedbackUpdateRequest> {

    private final UserFeedbackRepository userFeedbackRepository;
    private final UserFeedbackMapper userFeedbackMapper;

    @Override
    public List<UserFeedbackResponseForAdmin> getAllUserFeedbacks() {
        return userFeedbackMapper.modelToDto(userFeedbackRepository.findAll());
    }

    @Override
    public Page<UserFeedbackResponseForAdmin> getAllUserFeedbacks(Pageable pageable) {
        return userFeedbackMapper.modelToDto(userFeedbackRepository.findAll(pageable));
    }

    @Override
    public UserFeedbackResponseForAdmin findById(Long id) {
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

    @Override
    public Page<UserFeedbackResponseForUser> getAllUserFeedbacksForUser(Pageable pageable) {
        return userFeedbackMapper.modelToDtoForUser(userFeedbackRepository.findAll(pageable));
    }

    @Override
    public UserFeedbackResponseForUser findByIdForUser(Long id) {
        UserFeedback userFeedback = userFeedbackRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("UserFeedback with id: " + id + " not found")
                );
        return userFeedbackMapper.modelToDtoForUser(userFeedback);
    }
}
