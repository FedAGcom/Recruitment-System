package com.fedag.recruitmentSystem.Service.feedbackService;

import com.fedag.recruitmentSystem.model.UserFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserFeedbackService {
    List<UserFeedback> findAllUserFeedback();

    Page<UserFeedback> findAllUserFeedback(Pageable pageable);

    UserFeedback findUserFeedbackById(Long id);

    void saveUserFeedback(UserFeedback userFeedback);

    void deleteUserFeedbackById(Long id);

}
