package com.fedag.recruitmentSystem.service.feedbackService;

import com.fedag.recruitmentSystem.repository.UserFeedbackRepository;
import com.fedag.recruitmentSystem.model.UserFeedback;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFeedbackServiceImpl implements UserFeedbackService {

    private final UserFeedbackRepository userFeedbackRepository;

    @Operation(description = "Поиск всех отзывов о пользователе. Выводит список")
    @Override
    public List<UserFeedback> findAllUserFeedback() {
        return userFeedbackRepository.findAll();
    }

    @Operation(description = "Поиск всех отзывов о пользователе. Выводит постранично")
    @Override
    public Page<UserFeedback> findAllUserFeedback(Pageable pageable) {
        return userFeedbackRepository.findAll(pageable);
    }

    @Operation(description = "Позволяет найти пользователя по id")
    @Override
    public UserFeedback findUserFeedbackById(Long id) {
        UserFeedback userFeedback = null;
        Optional<UserFeedback> userFeedbackOptional = userFeedbackRepository.findById(id);
        if (userFeedbackOptional.isPresent()) {
            userFeedback = userFeedbackOptional.get();
        }
        return userFeedback;
    }

    @Operation(description = "Сохранить отзыв в бд")
    @Override
    public void saveUserFeedback(UserFeedback userFeedback) {
        userFeedbackRepository.save(userFeedback);
    }

    @Operation(description = "Удаляет отзыв из бд")
    @Override
    public void deleteUserFeedbackById(Long id) {
        userFeedbackRepository.deleteById(id);
    }
}
