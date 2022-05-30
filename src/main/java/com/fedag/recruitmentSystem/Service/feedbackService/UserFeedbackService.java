package com.fedag.recruitmentSystem.Service.feedbackService;

import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.model.UserFeedback;

import java.util.List;

public interface UserFeedbackService {
    List<UserFeedback> findAllUserFeedback();


    UserFeedback findUserFeedbackById(Long id);


    void saveUserFeedback(UserFeedback userFeedback);


    void deleteUserFeedbackById(Long id);

}
