package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CompanyController {

  private final CompanyServiceImpl companyService;

  @GetMapping("/companies")
  public String index() {
    return companyService.index().toString();
  }

  @PostMapping("/companies/delete/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    companyService.deleteById(id);
  }

  @PostMapping("/companies/add")
  public void addVacancy(@ModelAttribute("company") Company company) {
    companyService.save(company);
  }

  @PostMapping("/companies/update/{id}")
  public void updateVacancy(@ModelAttribute("company") Company company) {
    companyService.save(company);
  }
}
