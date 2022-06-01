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

    @Override
    public Page<CompanyFeedBack> getAllCompanyFeedBack(Pageable pageable) {
        return companyFeedBackRepository.findAll(pageable);
    }

    @Override
    public CompanyFeedBack getCompanyFeedBackById(Long id) {
        return companyFeedBackRepository.getById(id);
    }


    @Override
    public CompanyFeedBack addCompanyFeedBack(CompanyFeedBack companyFeedBack) {
        return companyFeedBackRepository.save(companyFeedBack);
    }

    @Override
    public void removeCompanyFeedBack(Long id) {
        companyFeedBackRepository.deleteById(id);
    }
}




