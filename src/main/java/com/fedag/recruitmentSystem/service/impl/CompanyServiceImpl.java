package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.domain.dto.CompanyDto;
import com.fedag.recruitmentSystem.domain.entity.Company;
import com.fedag.recruitmentSystem.domain.mapper.CompanyMapper;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.service.CompanyService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService<CompanyDto> {

  private final CompanyRepository companyRepository;
  private final CompanyMapper companyMapper;

  @Override
  public List<CompanyDto> getAllCompanies() {
    return companyRepository
        .findAll()
        .stream()
        .map(companyMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public Page<CompanyDto> getAllCompanies(Pageable pageable) {
    return companyRepository
        .findAll(pageable)
        .map(companyMapper::toDto);
  }

  @Override
  public CompanyDto findById(Long id) {
    return companyMapper.toDto(companyRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Company with id: " + id + " not found")
        ));
  }

  @Override
  public void save(CompanyDto element) {
    companyRepository.save(companyMapper.toEntity(element));
  }

  @Override
  public void deleteById(Long id) {
    companyRepository.deleteById(id);
  }
}
