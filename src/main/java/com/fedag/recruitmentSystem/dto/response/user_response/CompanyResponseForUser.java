package com.fedag.recruitmentSystem.dto.response.user_response;

import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseForUser {

  private String companyName;
  private String email;
  private String location;

}
