package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateRequest {

  private Long id;
  private String companyName;
  private String email;
  private String location;
  private String calendarId;
}
