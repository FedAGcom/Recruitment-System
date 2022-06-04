package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.service.CompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService<Company> {

  private final CompanyRepository companyRepository;

  @Override
  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  @Override
  public Page<Company> getAllCompanies(Pageable pageable) {
    return companyRepository.findAll(pageable);
  }

  @Override
  public Company findById(Long id) {
    return companyRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Company with id: " + id + " not found")
        );
  }

  @Override
  public void save(Company element) {
    companyRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    companyRepository.deleteById(id);
  }

  public List<Company> getByStars(byte stars) {
    return companyRepository.findByStars(stars);
  }
}
