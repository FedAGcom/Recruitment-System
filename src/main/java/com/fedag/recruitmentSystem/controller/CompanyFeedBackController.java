package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.service.CompanyFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyFeedBackController {

    private CompanyFeedBackService companyFeedBackService;

    @Autowired
    public CompanyFeedBackController(CompanyFeedBackService companyFeedBackService) {
        this.companyFeedBackService = companyFeedBackService;
    }

    @GetMapping("/companies/feedback")  // Нужно добавить ID компании?
    public List<CompanyFeedBack> getAllCompanyFeedBack() {
        return companyFeedBackService.getAllCompanyFeedBack();
    }

    @PostMapping("/companies/feedback/save")
    public CompanyFeedBack addCompanyFeedBack(@RequestBody CompanyFeedBack companyFeedBack) {
        return companyFeedBackService.addCompanyFeedBack(companyFeedBack);
    }

    @DeleteMapping("/companies/feedback/delete/{id}")
    public void deleteCompanyFeedBack(CompanyFeedBack companyFeedBack) {
        companyFeedBackService.removeCompanyFeedBack(companyFeedBack);
    }

}
