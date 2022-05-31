package com.fedag.recruitmentSystem.Service.feedbackService;

import com.fedag.recruitmentSystem.Dao.UserFeedbackRepository;
import com.fedag.recruitmentSystem.model.UserFeedback;
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

    @Override
    public List<UserFeedback> findAllUserFeedback() {
        return userFeedbackRepository.findAll();
    }

    @Override
    public Page<UserFeedback> findAllUserFeedback(Pageable pageable) {
        return userFeedbackRepository.findAll(pageable);
    }

    @Override
    public UserFeedback findUserFeedbackById(Long id) {
        UserFeedback userFeedback = null;
        Optional<UserFeedback> userFeedbackOptional = userFeedbackRepository.findById(id);
        if (userFeedbackOptional.isPresent()) {
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
