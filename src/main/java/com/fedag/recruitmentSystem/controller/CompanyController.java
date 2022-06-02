package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(value = "/api/companies")
public class CompanyController {

  private final CompanyServiceImpl companyService;

  @GetMapping
  public Page<Company> getAllCompanies(@PageableDefault(size = 5) Pageable pageable) {
    return companyService.getAllCompanies(pageable);
  }

  @GetMapping("/{id}")
  public Company getById(@PathVariable Long id) {
    return companyService.findById(id);
  }

  @PostMapping
  public void addVacancy(@RequestBody Company company) {
    companyService.save(company);
  }

  @PutMapping
  public void updateVacancy(@RequestBody Company company) {
    companyService.save(company);
  }

  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    companyService.deleteById(id);
  }
}
