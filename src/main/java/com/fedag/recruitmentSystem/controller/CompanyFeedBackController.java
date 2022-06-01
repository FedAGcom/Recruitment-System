package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.service.CompanyFeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/companies/feedback")
public class CompanyFeedBackController {

    private final CompanyFeedBackService companyFeedBackService;

    @GetMapping
    public List<CompanyFeedBack> getAllCompanyFeedBack() {
        return companyFeedBackService.getAllCompanyFeedBack();
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
