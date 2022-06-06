package com.fedag.recruitmentSystem.domain.mapper;

import com.fedag.recruitmentSystem.domain.dto.CompanyDto;
import com.fedag.recruitmentSystem.domain.entity.Company;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

  private final ModelMapper mapper;

  public CompanyDto toDto(Company company) {
    return mapper.map(company, CompanyDto.class);
  }

  public Company toEntity(CompanyDto companyDto) {
    return mapper.map(companyDto, Company.class);
  }
}
