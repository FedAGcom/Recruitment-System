package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.CompanyRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.CompanyResponse;
import com.fedag.recruitmentSystem.model.Company;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

  private final ModelMapper mapper;

  public CompanyResponse toDto(Company company) {
    return mapper.map(company, CompanyResponse.class);
  }

  public Company toEntity(CompanyRequest companyRequest) {
    return mapper.map(companyRequest, Company.class);
  }

  public Company toEntity(CompanyUpdateRequest companyUpdateRequest) {
    return mapper.map(companyUpdateRequest, Company.class);
  }
}
