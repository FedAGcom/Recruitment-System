package com.fedag.recruitmentSystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

  private String companyName;
  private String email;
  private String location;
}
