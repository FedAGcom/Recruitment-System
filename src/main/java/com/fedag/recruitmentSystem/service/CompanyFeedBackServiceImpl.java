package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dao.CompanyFeedBackDao;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyFeedBackServiceImpl implements CompanyFeedBackService {

    private CompanyFeedBackDao companyFeedBackDao;

    @Autowired
    public CompanyFeedBackServiceImpl(CompanyFeedBackDao companyFeedBackDao) {
        this.companyFeedBackDao = companyFeedBackDao;
    }

    @Transactional
    @Override
    public List<CompanyFeedBack> getAllCompanyFeedBack() {
        return companyFeedBackDao.findAll();
    }

    @Transactional
    @Override
    public CompanyFeedBack addCompanyFeedBack(CompanyFeedBack companyFeedBack) {
        return companyFeedBackDao.save(companyFeedBack);
    }

    @Transactional
    @Override
    public void removeCompanyFeedBack(CompanyFeedBack companyFeedBack) {
        companyFeedBackDao.delete(companyFeedBack);
    }
}
