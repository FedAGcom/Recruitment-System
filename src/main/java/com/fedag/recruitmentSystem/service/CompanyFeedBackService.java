package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;

import java.util.List;

public interface CompanyFeedBackService {
    public List<CompanyFeedBack> getAllCompanyFeedBack();

    public CompanyFeedBack addCompanyFeedBack(CompanyFeedBack companyFeedBack);

    public void removeCompanyFeedBack(Long id);
}
