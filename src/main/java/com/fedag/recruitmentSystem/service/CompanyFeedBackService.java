package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyFeedBackService {

    public Page<CompanyFeedBack> getAllCompanyFeedBack(Pageable pageable);

    public CompanyFeedBack getCompanyFeedBackById(Long id);

    public CompanyFeedBack addCompanyFeedBack(CompanyFeedBack companyFeedBack);

    public void removeCompanyFeedBack(Long id);
}
