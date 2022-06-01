package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.service.CompanyFeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/companies/feedback")
public class CompanyFeedbackController {

  private final CompanyFeedBackService companyFeedBackService;

  @GetMapping
  public Page<CompanyFeedBack> getAllCompanyFeedBack(Pageable pageable) {
    return companyFeedBackService.getAllCompanyFeedBack(pageable);
  }

  @GetMapping("/{id}")
  public CompanyFeedBack getCompanyFeedBackById(@PathVariable Long id) {
    return companyFeedBackService.getCompanyFeedBackById(id);
  }

  //TODO метод ниже void
  @PostMapping
  public CompanyFeedBack addCompanyFeedBack(@RequestBody CompanyFeedBack companyFeedBack) {
    return companyFeedBackService.addCompanyFeedBack(companyFeedBack);
  }

  @PutMapping
  public void updateVacancy(@RequestBody CompanyFeedBack companyFeedBack) {
    companyFeedBackService.addCompanyFeedBack(companyFeedBack);
  }

  @DeleteMapping("/{id}")
  public void deleteCompanyFeedBack(@PathVariable Long id) {
    companyFeedBackService.removeCompanyFeedBack(id);
  }

}
