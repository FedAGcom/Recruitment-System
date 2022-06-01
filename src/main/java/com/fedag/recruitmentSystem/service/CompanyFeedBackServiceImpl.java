package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.repository.CompanyFeedBackRepository;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
