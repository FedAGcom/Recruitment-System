package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.repository.CompanyFeedBackRepository;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.repository.UserFeedbackRepository;
import com.fedag.recruitmentSystem.service.CompanyFeedBackService;
import com.fedag.recruitmentSystem.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyFeedBackServiceImpl implements CompanyFeedBackService {

    private final CompanyFeedBackRepository companyFeedBackRepository;

    @Transactional
    @Override
    public Page<CompanyFeedBack> getAllCompanyFeedBack(Pageable pageable) {
        return companyFeedBackRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public CompanyFeedBack getCompanyFeedBackById(Long id) {
        return companyFeedBackRepository.getById(id);
    }

    @Transactional
    @Override
    public CompanyFeedBack addCompanyFeedBack(CompanyFeedBack companyFeedBack) {
        return companyFeedBackRepository.save(companyFeedBack);
    }

    @Transactional
    @Override
    public void removeCompanyFeedBack(Long id) {
        companyFeedBackRepository.deleteById(id);
    }

    public static interface ExamService {

        List<Exam> findAllExams();

        Page<Exam> findAllExams(Pageable pageable);

        Exam findExamById(Long id);

        void saveExam(Exam exam);

        void deleteExamById(Long id);
    }

    @Service
    @RequiredArgsConstructor
    public static class UserFeedbackServiceImpl implements CompanyService.UserFeedbackService {

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
}
