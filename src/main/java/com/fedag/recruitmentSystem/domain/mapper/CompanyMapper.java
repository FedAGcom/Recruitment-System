package com.fedag.recruitmentSystem.domain.mapper;

import com.fedag.recruitmentSystem.domain.dto.CompanyRequest;
import com.fedag.recruitmentSystem.domain.dto.CompanyResponse;
import com.fedag.recruitmentSystem.domain.entity.Company;
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
}
