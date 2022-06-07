package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.CompanyRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.CompanyResponse;
import com.fedag.recruitmentSystem.mapper.CompanyMapper;
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
public class CompanyServiceImpl implements CompanyService<CompanyResponse, CompanyRequest, CompanyUpdateRequest> {

  private final CompanyRepository companyRepository;
  private final CompanyMapper companyMapper;

  @Override
  public List<CompanyResponse> getAllCompanies() {
    return companyRepository
        .findAll()
        .stream()
        .map(companyMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public Page<CompanyResponse> getAllCompanies(Pageable pageable) {
    return companyRepository
        .findAll(pageable)
        .map(companyMapper::toDto);
  }

  @Override
  public CompanyResponse findById(Long id) {
    return companyMapper.toDto(companyRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Company with id: " + id + " not found")
        ));
  }

  @Override
  public void save(CompanyRequest element) {
    companyRepository.save(companyMapper.toEntity(element));
  }

  @Override
  public void update(CompanyUpdateRequest element) {
    companyRepository.save(companyMapper.toEntity(element));
  }

  @Override
  public void deleteById(Long id) {
    companyRepository.deleteById(id);
  }
}
