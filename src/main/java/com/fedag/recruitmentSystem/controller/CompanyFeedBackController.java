package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.service.CompanyFeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/companies/feedback")
public class CompanyFeedBackController {

    private final CompanyFeedBackService companyFeedBackService;

    @GetMapping
    public Page<CompanyFeedBack> getAllCompanyFeedBack(Pageable pageable) {
        return companyFeedBackService.getAllCompanyFeedBack(pageable);
    }

    @GetMapping("/{id}")
    public CompanyFeedBack getCompanyFeedBackById(@PathVariable Long id) {
        return companyFeedBackService.getCompanyFeedBackById(id);
    }

    @PostMapping("/save")
    public CompanyFeedBack addCompanyFeedBack(@RequestBody CompanyFeedBack companyFeedBack) {
        return companyFeedBackService.addCompanyFeedBack(companyFeedBack);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanyFeedBack(@PathVariable Long id) {
        companyFeedBackService.removeCompanyFeedBack(id);
    }

}
