package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.service.impl.CompanyFeedbackServiceImpl;
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
@RequestMapping(value = "/api/companies/feedbacks")
public class CompanyFeedbackController {

  private final CompanyFeedbackServiceImpl companyFeedBackService;

  @GetMapping
  public Page<CompanyFeedBack> getAllCompanyFeedBack(Pageable pageable) {
    return companyFeedBackService.getAllCompanyFeedbacks(pageable);
  }

  @GetMapping("/{id}")
  public CompanyFeedBack getCompanyFeedBackById(@PathVariable Long id) {
    return companyFeedBackService.findById(id);
  }

  @PostMapping
  public void addCompanyFeedBack(@RequestBody CompanyFeedBack companyFeedBack) {
    companyFeedBackService.save(companyFeedBack);
  }

  @PutMapping
  public void updateVacancy(@RequestBody CompanyFeedBack companyFeedBack) {
    companyFeedBackService.save(companyFeedBack);
  }

  @DeleteMapping("/{id}")
  public void deleteCompanyFeedBack(@PathVariable Long id) {
    companyFeedBackService.deleteById(id);
  }

}
