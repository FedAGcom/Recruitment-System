package com.fedag.recruitmentSystem.service;

import java.util.List;
import java.util.Optional;

import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface CompanyService<T> extends GenericServiceInterface<T> {

  List<T> getAllCompanies();

  Page<T> getAllCompanies(Pageable pageable);

    interface UserFeedbackService {
        List<UserFeedback> findAllUserFeedback();

        Page<UserFeedback> findAllUserFeedback(Pageable pageable);

        UserFeedback findUserFeedbackById(Long id);

        void saveUserFeedback(UserFeedback userFeedback);

        void deleteUserFeedbackById(Long id);

    }

    @Service
    @RequiredArgsConstructor
    class UserServiceImpl implements CompanyFeedBackService.UserService {

        private final UserRepository userRepository;

        @Override
        public List<User> findAllUsers() {
            return userRepository.findAll();
        }

        @Override
        public Page<User> findAllUsers(Pageable pageable) {
            return userRepository.findAll(pageable);
        }

        @Override
        public User findUserById(Long id) {
            User user = null;
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                user = userOptional.get();
            }
            return user;
        }

        @Override
        public void saveUser(User user) {
            userRepository.save(user);
        }

        @Override
        public void deleteUserById(Long id) {
            userRepository.deleteById(id);
        }
    }
}
