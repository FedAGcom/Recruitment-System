package com.fedag.recruitmentSystem.Service.feedbackService;

import com.fedag.recruitmentSystem.Dao.UserFeedbackRepository;
import com.fedag.recruitmentSystem.model.UserFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {

    @Autowired
    UserFeedbackRepository userFeedbackRepository;


    @Override
    public List<UserFeedback> findAllUserFeedback() {
        return userFeedbackRepository.findAll();
    }

    @Override
    public UserFeedback findUserFeedbackById(Long id) {
        UserFeedback userFeedback = null;
        Optional<UserFeedback> userFeedbackOptional = userFeedbackRepository.findById(id);
        if(userFeedbackOptional.isPresent()){
            userFeedback = userFeedbackOptional.get();
        }
        return userFeedback;
    }

    @Override
    public void saveUserFeedback(UserFeedback userFeedback) {
        userFeedbackRepository.save(userFeedback);
    }

    @Override
    public void deleteUserFeedbackById(Long id) {
        userFeedbackRepository.deleteById(id);
    }
}
