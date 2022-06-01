package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dao.CompanyFeedBackRepository;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyFeedBackServiceImpl implements CompanyFeedBackService {

    private final CompanyFeedBackRepository companyFeedBackRepository;

    @Transactional
    @Override
    public List<CompanyFeedBack> getAllCompanyFeedBack() {
        return companyFeedBackRepository.findAll();
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
