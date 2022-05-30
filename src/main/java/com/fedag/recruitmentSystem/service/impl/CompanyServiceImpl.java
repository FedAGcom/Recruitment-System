package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.service.CompanyService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService<Company> {

  private final CompanyRepository companyRepository;

  @Override
  public Company findById(Long id) {
    Optional<Company> company = companyRepository.findById(id);
    if (company.isEmpty()) {
      throw new IllegalArgumentException("Skill is null");
    }
    return company.get();
  }

  @Override
  public List<Company> index() {
    return companyRepository.findAll();
  }

  @Override
  public void save(Company element) {
    companyRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    companyRepository.deleteById(id);
  }
}
