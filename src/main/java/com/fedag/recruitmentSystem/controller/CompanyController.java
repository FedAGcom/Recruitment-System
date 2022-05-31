package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/companies")
public class CompanyController {

  private final CompanyServiceImpl companyService;

  @GetMapping
  public String getAllCompanies(
      @RequestParam(defaultValue = "0") Integer pageNum,
      @RequestParam(defaultValue = "5") Integer pageSize) {
    Pageable paging = PageRequest.of(pageNum, pageSize);
    return companyService.getAllCompanies(paging).getContent().toString();
  }

  @GetMapping("/{id}")
  public String getById(@PathVariable Long id) {
    return companyService.findById(id).toString();
  }

  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    companyService.deleteById(id);
  }

  @PutMapping("/add")
  public void addVacancy(@RequestBody Company company) {
    companyService.save(company);
  }

  @PatchMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody Company company) {
    companyService.save(company);
  }
}
